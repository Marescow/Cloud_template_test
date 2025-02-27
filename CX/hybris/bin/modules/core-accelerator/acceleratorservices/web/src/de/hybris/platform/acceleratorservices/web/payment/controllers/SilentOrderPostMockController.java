/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.web.payment.controllers;

import de.hybris.platform.acceleratorservices.payment.utils.AcceleratorDigestUtils;
import de.hybris.platform.acceleratorservices.web.payment.forms.SopPaymentDetailsForm;
import de.hybris.platform.acceleratorservices.web.payment.validation.SopMockUrlValidator;
import de.hybris.platform.acceleratorservices.web.payment.validation.SopPaymentDetailsValidator;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static de.hybris.platform.acceleratorservices.web.payment.constants.PaymentMockConstants.PROCESS;
import static de.hybris.platform.acceleratorservices.web.payment.constants.PaymentMockConstants.SOP_MOCK;

@Controller
@RequestMapping(SOP_MOCK)
public class SilentOrderPostMockController
{
	protected static final Logger LOG = Logger.getLogger(SilentOrderPostMockController.class);

	protected static final String SOP_REDIRECT_POST_PAGE = "sopMock/redirectPost";

	private static final String SHARED_SECRET = "your_shared_secret";

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static final HostnameVerifier dummyHostnameVerifier = (arg0, arg1) -> true;

	@Resource(name = "sopPaymentDetailsValidator")
	private SopPaymentDetailsValidator sopPaymentDetailsValidator;

	@Resource(name = "acceleratorDigestUtils")
	private AcceleratorDigestUtils digestUtils;

	@Resource(name = "sopMockUrlValidator")
	private SopMockUrlValidator sopMockUrlValidator;

	private final X509TrustManager dummyTrustManager = new X509TrustManager()
	{
		@Override
		public void checkClientTrusted(final X509Certificate[] chain, final String authType)
		{
			//mock
		}

		@Override
		public void checkServerTrusted(final X509Certificate[] chain, final String authType)
		{
			//mock
		}

		@Override
		public X509Certificate[] getAcceptedIssuers()
		{
			// Null is returned as a result of not initialized list - caller methods may treat null
			// differently than an empty collection thus fix could break existing functionality
			return null;
		}
	};

	public SopPaymentDetailsValidator getSopPaymentDetailsValidator()
	{
		return sopPaymentDetailsValidator;
	}

	@RequestMapping(value = PROCESS, method = RequestMethod.POST)
	public String doValidateAndPost(@Valid final SopPaymentDetailsForm form, final BindingResult bindingResult,
			final HttpServletRequest request, final Model model)
	{
		final Map<String, String> params = cloneRequestParameters(request);

		// Remove values that we must not post back to the caller
		params.remove("card_cvNumber");

		getSopPaymentDetailsValidator().validate(form, bindingResult);

		if (bindingResult.hasErrors())
		{
			// Validation errors
			params.put("decision", "REJECT");
			params.put("reasonCode", "102");
			params.put("decision_publicSignature", getMockedPublicDigest("REJECT" + "102"));

			// Remove card number on validation error
			params.remove("card_accountNumber");

			final List<String> missingFields = new ArrayList<>();
			final List<String> invalidFields = new ArrayList<>();

			processMissingFields(bindingResult, missingFields, invalidFields);

			for (int i = 0; i < missingFields.size(); i++)
			{
				params.put("MissingField" + i, missingFields.get(i));
			}
			for (int i = 0; i < invalidFields.size(); i++)
			{
				params.put("InvalidField" + i, invalidFields.get(i));
			}
		}
		else
		{
			// No validation errors create subscription ID

			final String subscriptionId = UUID.randomUUID().toString();
			params.put("ccAuthReply_cvCode", "M");
			params.put("paySubscriptionCreateReply_subscriptionID", subscriptionId);
			params.put("paySubscriptionCreateReply_subscriptionIDPublicSignature", getMockedPublicDigest(subscriptionId));

			processTransactionDecision(request, null, params, false);

			//Mask the card number in the response
			final String endPortion = form.getCard_accountNumber().trim().substring(form.getCard_accountNumber().length() - 4);
			final String maskedCardNumber = "************" + endPortion;

			params.put("card_accountNumber", maskedCardNumber);
		}

		model.addAttribute("postParams", params);

		final String postUrl = params.get("orderPage_receiptResponseURL");
		if (getSopMockUrlValidator().validatePaymentResponseUrl(postUrl))
		{
			model.addAttribute("postUrl", postUrl);
		}
		else
		{
			model.addAttribute("postUrl", "javascript:false;");
		}

		final String merchantCallbackUrl = params.get("orderPage_merchantURLPostAddress");
		if (getSopMockUrlValidator().validateMerchantCallBackUrl(merchantCallbackUrl))
		{
			sendMerchantCallback(merchantCallbackUrl, params);
		}

		return SOP_REDIRECT_POST_PAGE;
	}

	protected void processMissingFields(final BindingResult bindingResult, final List<String> missingFields,
			final List<String> invalidFields) {
		for (final ObjectError objectError : bindingResult.getAllErrors())
		{
			if (objectError instanceof FieldError)
			{
				final FieldError fieldError = (FieldError) objectError;

				if (fieldError.getRejectedValue() == null || "".equals(fieldError.getRejectedValue()))
				{
					missingFields.add(fieldError.getField());
				}
				else
				{
					invalidFields.add(fieldError.getField());
				}
			}
		}
	}

	protected Map<String, String> cloneRequestParameters(final HttpServletRequest request)
	{
		final Enumeration<String> paramNames = request.getParameterNames();
		final Map<String, String> params = new HashMap<>();
		while (paramNames.hasMoreElements())
		{
			final String paramName = paramNames.nextElement();
			params.put(paramName, request.getParameter(paramName));
		}
		return params;
	}

	protected void processTransactionDecision(final HttpServletRequest request, final String reasonCode,
			final Map<String, String> params, final boolean error)
	{
		if (params == null || request == null)
		{
			return;
		}

		final String decision = error ? "ERROR" : "ACCEPT";

		final String modReasonCode;
		if (StringUtils.isBlank(reasonCode))
		{
			if ("ACCEPT".equalsIgnoreCase(decision))
			{
				modReasonCode = "100";
			}
			else
			{
				// General error
				modReasonCode = "150";
			}
		}
		else
		{
			modReasonCode = reasonCode;
		}

		// Default decision to ACCEPT 100
		params.put("decision", decision);
		params.put("reasonCode", modReasonCode);
		params.put("decision_publicSignature", getMockedPublicDigest(decision + modReasonCode));
	}

	protected String getMockedPublicDigest(final String customValues)
	{
		String result;
		try
		{
			result = digestUtils.getPublicDigest(customValues, SHARED_SECRET);
		}
		catch (final Exception e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(e);
			}
			result = "BzW+Xn0ZgZHeQRcFB6ri";
		}

		return result;
	}

	protected void sendMerchantCallback(final String merchantCallbackUrl, final Map<String, String> params)
	{
		HttpURLConnection conn = null;
		try
		{
			final URL url = new URL(merchantCallbackUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (conn instanceof HttpsURLConnection)
			{
				final SSLContext ctx = SSLContext.getInstance("TLSv1.2");
				ctx.init(null, new TrustManager[]
				{ dummyTrustManager }, new SecureRandom());

				final HttpsURLConnection sslConn = (HttpsURLConnection) conn;
				sslConn.setSSLSocketFactory(ctx.getSocketFactory());
				sslConn.setHostnameVerifier(dummyHostnameVerifier);
			}

			final byte[] postDataBytes = createPostData(params);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
			conn.setDoOutput(true);
			conn.getOutputStream().write(postDataBytes);

			LOG.debug("Merchant callback send : Response code=" + conn.getResponseCode());
		}
		catch (final Exception e)
		{
			LOG.error("Sending merchant callback failed", e);
		}
		finally
		{
			if (conn != null)
			{
				conn.disconnect();
			}
		}
	}

	protected byte[] createPostData(final Map<String, String> params) throws UnsupportedEncodingException
	{
		final StringBuilder postData = new StringBuilder();
		for (final Map.Entry<String, String> param : params.entrySet())
		{
			if (postData.length() != 0)
			{
				postData.append('&');
			}
			postData.append(URLEncoder.encode(param.getKey(), DEFAULT_ENCODING));
			postData.append('=');
			postData.append(URLEncoder.encode(param.getValue(), DEFAULT_ENCODING));
		}
		return postData.toString().getBytes(DEFAULT_ENCODING);
	}

	protected SopMockUrlValidator getSopMockUrlValidator()
	{
		return sopMockUrlValidator;
	}

	public void setSopMockUrlValidator(final SopMockUrlValidator sopMockUrlValidator)
	{
		this.sopMockUrlValidator = sopMockUrlValidator;
	}

}
