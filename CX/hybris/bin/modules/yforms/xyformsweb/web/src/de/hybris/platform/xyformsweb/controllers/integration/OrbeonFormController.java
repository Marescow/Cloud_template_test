/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.xyformsweb.controllers.integration;

import de.hybris.platform.xyformsfacades.data.YFormDataData;
import de.hybris.platform.xyformsfacades.data.YFormDefinitionData;
import de.hybris.platform.xyformsfacades.form.YFormFacade;
import de.hybris.platform.xyformsservices.enums.YFormDataTypeEnum;
import de.hybris.platform.xyformsservices.exception.YFormServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

/**
 * OrbeonFormController Integration for Orbeon Persistence API Use for providing Create, Read, Update and delete
 * services for integrated Orbeon functions. This will also handle Search and other Orbeon provided functions.
 */
@Controller
@Scope("tenant")
public class OrbeonFormController extends AbstractController
{

	private static final String CONTENT_TYPE = "Content-Type";
	private static final String TEXT_XML = "text/xml";
	private static final Pattern REPLACE_REGEX = Pattern.compile("[\n\r\t]");
	private static final String FORM_ID_TEXT = " | formId=";
	private static final String FORM_DATA_ID_TEXT = " | formDataId=";
	protected static final String ORBEON_LAST_MODIFIED = "Orbeon-Last-Modified";

	protected static final Logger LOG = Logger.getLogger(OrbeonFormController.class);
	protected static final String FR_SERVICE_RESOURCE_PREFIX = "/fr/service/hybris";
	protected static final String ORBEON_FORM_DEFINITION_VERSION = "Orbeon-Form-Definition-Version";
	protected static final String FORM_BUILDER_NEXT_VERSION = "next";
	protected static final String SEARCH_FORMDATA_EMPTY_SET = "<?xml version='1.0' encoding='utf-8'?><documents total='0' search-total='0' page-size='10' page-number='1' query=''></documents>";
	protected static final String SEARCH_FORMDEFINITIONS_RESPONSE = "<?xml version='1.0' encoding='utf-8'?><forms xmlns:oxf='http://www.orbeon.com/oxf/processors' xmlns:f='http//www.orbeon.com/function' xmlns:xsl='http://www.w3.org/1999/XSL/Transform' xmlns:xi='http://www.w3.org/2001/XInclude' xmlns:ev='http://www.w3.org/2001/xml-events' xmlns:sql='http://orbeon.org/oxf/xml/sql' xmlns:p='http://www.orbeon.com/oxf/pipeline' xmlns:xs='http://www.w3.org/2001/XMLSchema' xmlns:fr='http://orbeon.org/oxf/xml/form-runner' xmlns:xf='http://www.w3.org/2002/xforms' xmlns:odt='http://orbeon.org/oxf/xml/datatypes' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><form operations=\"*\"><application-name>%s</application-name><form-name>%s</form-name><form-version>%s</form-version></form></forms>";

	@Resource(name = "yFormFacade")
	private YFormFacade yFormFacade;

	/**
	 * Get yForm Definition. <br/>
	 * On passing in the applicationId and the formId will return a form.xhtml for the Persistence service call. If
	 * documentId is provided then it will return the form definition associated to it. This is valid when dealing with a
	 * form definition that has multiple versions.
	 *
	 * @param applicationId
	 * @param formId
	 * @param documentId
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.GET, value = FR_SERVICE_RESOURCE_PREFIX
			+ "/crud/{applicationId}/{formId}/form/form.xhtml", produces = "application/xhtml+xml; charset=utf-8")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String getFormDefinition(@PathVariable final String applicationId, @PathVariable final String formId,
									@RequestParam(value = "document", required = false) final String documentId, final HttpServletResponse response)
			throws ServletException, IOException, YFormServiceException
	{
		if (LOG.isDebugEnabled())
		{
			String logMessage = String.format("getFormDefinition: applicationId=%s | formId=%s", applicationId, formId);
			if (documentId != null) {
				logMessage = logMessage + String.format(" | documentId=%s", documentId);
			}
			final String formatLogMessage = REPLACE_REGEX.matcher(logMessage).replaceAll("_");
			LOG.debug(formatLogMessage);

		}

		YFormDefinitionData yformDefinition = null;

		//The source of the xhtml should be retrieved from the DB when that's available.
		try
		{
			if (documentId == null)
			{
				yformDefinition = yFormFacade.getYFormDefinition(applicationId, formId);
			}
			else
			{
				yformDefinition = yFormFacade.getYFormDefinition(documentId);
			}
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "";
		}

		String r = "";

		if (yformDefinition != null)
		{
			if (yformDefinition.getContent() != null)
			{
				r = yformDefinition.getContent();
			}
			response.setHeader(ORBEON_FORM_DEFINITION_VERSION, "" + yformDefinition.getVersion());
		}
		return r;
	}

	/**
	 * Put Form Definition. <br/>
	 * Publish at the FormBuilder will save the new form definition by calling this service function.
	 *
	 * @param applicationId
	 * @param formId
	 * @param documentId
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.PUT, value = FR_SERVICE_RESOURCE_PREFIX
			+ "/crud/{applicationId}/{formId}/form/form.xhtml")
	@PreAuthorize("isAuthenticated()")
	public void putFormDefinition(@PathVariable final String applicationId, @PathVariable final String formId,
								  @RequestParam(value = "document", required = false) final String documentId, final HttpServletRequest request,
								  final HttpServletResponse response) throws ServletException, IOException
	{
		if (LOG.isDebugEnabled())
		{
			String logMessage = String.format("putFormDefinition: applicationId=%s | formId=%s", applicationId, formId);
			if (documentId != null) {
				logMessage = logMessage + String.format(" | documentId=%s", documentId);
			}
			final String formatLogMessage = REPLACE_REGEX.matcher(logMessage).replaceAll("_");
			LOG.debug(formatLogMessage);
		}

		final InputStream input = request.getInputStream();
		if (input != null)
		{
			final String content = IOUtils.toString(input, StandardCharsets.UTF_8);
			try
			{
				// Checks if user has selected a new version, or update the existing one.
				// (presumably the latest one, the way to know this is through the documentId)

				// Since orbeon-2023.1, the version header is a positive integer and required for form definition
				// If close versioning, the version header will be always "1"
				final String versionHeader = request.getHeader(ORBEON_FORM_DEFINITION_VERSION);
				if (!isInteger(versionHeader)) {
					throw new YFormServiceException("The Orbeon-Form-Definition-Version header is not a positive integer.");
				}

				LOG.debug("Header [" + URLEncoder.encode(versionHeader, StandardCharsets.UTF_8) + "]");

				final YFormDefinitionData yformDefinition = yFormFacade.createOrUpdateYFormDefinition(applicationId, formId, content, documentId, versionHeader);

				response.setStatus(HttpServletResponse.SC_OK);
				LOG.debug("Header [" + versionHeader + "] - Created Version [" + yformDefinition.getVersion() + "]");
				this.setResponseHeaderOfPut(response, yformDefinition.getVersion(), yformDefinition.getLastModifiedDate());
			}
			catch (final YFormServiceException e)
			{
				LOG.error(e.getMessage(), e);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getLocalizedMessage());
			}
		}
		else
		{
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "there is no form definition received.");
		}
	}

	/**
	 * Retrieves the DRAFT version of a form data.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataId
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.GET, value = FR_SERVICE_RESOURCE_PREFIX
			+ "/crud/{applicationId}/{formId}/draft/{formDataId}/data.xml", produces = "application/xml;charset=UTF-8")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String getFormDataDraft(@PathVariable final String applicationId, @PathVariable final String formId,
								   @PathVariable final String formDataId, final HttpServletResponse response)
			throws ServletException, IOException, YFormServiceException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getFormData: applicationId=" + applicationId + FORM_ID_TEXT + formId + FORM_DATA_ID_TEXT + formDataId
					+ " | formDataType=DRAFT");
		}

		final YFormDataTypeEnum type = YFormDataTypeEnum.DRAFT;
		YFormDataData yformData = null;
		try
		{
			yformData = yFormFacade.getYFormData(formDataId, type);
		}
		catch (final Exception e)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "";
		}

		if (yformData != null && yformData.getContent() != null)
		{
			return yformData.getContent();
		}

		return "";
	}

	/**
	 * Retrieves the DATA version of a form data.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataId
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.GET, value = FR_SERVICE_RESOURCE_PREFIX
			+ "/crud/{applicationId}/{formId}/data/{formDataId}/data.xml", produces = "application/xml;charset=UTF-8")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String getFormDataData(@PathVariable final String applicationId, @PathVariable final String formId,
								  @PathVariable final String formDataId, final HttpServletResponse response)
			throws ServletException, IOException, YFormServiceException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getFormData: applicationId=" + applicationId + FORM_ID_TEXT + formId + FORM_DATA_ID_TEXT + formDataId
					+ " | formDataType=DATA");
		}

		YFormDataData yformData = null;
		// Let's check first if there is a DRAFT record
		try
		{
			yformData = yFormFacade.getYFormData(formDataId, YFormDataTypeEnum.DRAFT);
			return yformData.getContent();
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage());
		}

		// let's get the DATA version of it
		try
		{
			yformData = yFormFacade.getYFormData(formDataId, YFormDataTypeEnum.DATA);
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "";
		}

		return yformData.getContent();
	}

	/**
	 * Saves the amend form data back to the database. Creates a new YFormData if it does not exist previously.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataType
	 * @param formDataId
	 * @param request
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.PUT, value = FR_SERVICE_RESOURCE_PREFIX
			+ "/crud/{applicationId}/{formId}/{formDataType:data|draft}/{formDataId}/data.xml")
	@PreAuthorize("isAuthenticated()")
	public void putFormData(@PathVariable final String applicationId, @PathVariable final String formId,
							@PathVariable final String formDataType, @PathVariable final String formDataId, final HttpServletRequest request,
							final HttpServletResponse response) throws ServletException, IOException, YFormServiceException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("putFormData: applicationId=" + applicationId + FORM_ID_TEXT + formId + FORM_DATA_ID_TEXT + formDataId
					+ " | formDataType=" + formDataType);
		}

		final YFormDataTypeEnum type = YFormDataTypeEnum.valueOf(formDataType.toUpperCase());

		final InputStream input = request.getInputStream();
		final String formDataContent = IOUtils.toString(input, StandardCharsets.UTF_8);
		try
		{
			YFormDataData yFormDataData = yFormFacade.createOrUpdateYFormData(applicationId, formId, formDataId, type, formDataContent);
			response.setStatus(HttpServletResponse.SC_OK);
			this.setResponseHeaderOfPut(response, yFormDataData.getFormDefinition().getVersion(), yFormDataData.getLastModifiedDate());
		}
		catch (final YFormServiceException e)
		{
			LOG.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getLocalizedMessage());
		}
	}


	/**
	 * Search for form data, no actual implementation yet, only returns an empty xml, this to minimize error logs on the
	 * orbeon side.
	 * <p>
	 * This method is also called when versioning is enabled and the query is as follows:
	 * <p>
	 * <code>
	 *    <?xml version="1.0" encoding="UTF-8"?>
	 *    <search>
	 *       <drafts for-document-id="b7e555ee37b66eac4fb4d1de74870e98b17467f9">only</drafts>
	 *       <page-size>10</page-size>
	 *       <page-number>1</page-number>
	 *       <lang>en</lang>
	 *    </search>
	 * </code>
	 * <p>
	 * When a draft record is found for this document a dialog is shown to the user to make a choice, whether to use the
	 * DRAFT record or the DATA one.
	 * <p>
	 * In this implementation, an empty set is returned and the responsibility to deal with this scenario is for
	 * <code>getFormDataData()</code>
	 *
	 * @param applicationId
	 * @param formId
	 * @param request
	 * @param response
	 */

	@RequestMapping(method = RequestMethod.POST, value = FR_SERVICE_RESOURCE_PREFIX + "/search/{applicationId}/{formId}")
	@PreAuthorize("isAuthenticated()")
	public void searchFormData(@PathVariable final String applicationId, @PathVariable final String formId,
							   final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException, YFormServiceException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("searchFormData: applicationId=" + applicationId + FORM_ID_TEXT + formId);
			LOG.debug(request.getHeader(ORBEON_FORM_DEFINITION_VERSION));
		}

		try
		{
			response.setStatus(HttpServletResponse.SC_OK);
			response.addHeader(CONTENT_TYPE, TEXT_XML);
			IOUtils.write(SEARCH_FORMDATA_EMPTY_SET, response.getOutputStream(), Charset.defaultCharset());
		}
		catch (final IOException e)
		{
			LOG.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getLocalizedMessage());
		}
	}

	/**
	 * Search for form definitions, no actual implementation yet, only returns an empty xml, this to minimize error logs
	 * on the orbeon side.
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.GET, value =
			{ FR_SERVICE_RESOURCE_PREFIX + "/form", FR_SERVICE_RESOURCE_PREFIX + "/form/{applicationId}",
					FR_SERVICE_RESOURCE_PREFIX + "/form/{applicationId}/{formId}" })
	@PreAuthorize("isAuthenticated()")
	public void searchFormDefinitions(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException
	{
		final Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		final String applicationId = pathVariables.get("applicationId");
		final String formId = pathVariables.get("formId");

		if (LOG.isDebugEnabled())
		{
			LOG.debug("searchFormDefinitions [" + applicationId + "][" + formId + "]");
			LOG.debug(request.getHeader(ORBEON_FORM_DEFINITION_VERSION));
		}

		try
		{
			response.addHeader(CONTENT_TYPE, TEXT_XML);
			response.setStatus(HttpServletResponse.SC_OK);

			final String responseBody = getSearchFormDefinitionBody(applicationId, formId);
			IOUtils.write(responseBody, response.getOutputStream(), Charset.defaultCharset());
		}
		catch (final IOException e)
		{
			LOG.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getLocalizedMessage());
		}
	}

	protected String getSearchFormDefinitionBody(String applicationId, String formId)
	{
		try {
			final int version = yFormFacade.getYFormDefinition(applicationId, formId).getVersion();
			return String.format(SEARCH_FORMDEFINITIONS_RESPONSE, applicationId, formId, version);
		}
		catch (final YFormServiceException e)
		{
			return String.format(SEARCH_FORMDEFINITIONS_RESPONSE, applicationId, formId, 1);
		}
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	@ExceptionHandler({ AuthenticationCredentialsNotFoundException.class })
	public void handleAuthenticationCredentialsNotFoundException(final Exception ex)
	{
		LOG.debug("AuthenticationCredentialsNotFoundException", ex);
	}

	private void setResponseHeaderOfPut(final HttpServletResponse response, final int version, final Date lastModifiedDate)
	{
		response.setHeader(ORBEON_FORM_DEFINITION_VERSION, Integer.toString(version));
		response.setHeader(ORBEON_LAST_MODIFIED, this.convertDateToString(lastModifiedDate));
	}

	private String convertDateToString(final Date date)
	{
		if (Objects.isNull(date))
		{
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssZ");
		return formatter.format(date);
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
