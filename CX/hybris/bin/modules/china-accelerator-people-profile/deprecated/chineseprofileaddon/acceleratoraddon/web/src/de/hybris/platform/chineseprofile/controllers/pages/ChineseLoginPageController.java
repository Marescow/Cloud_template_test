/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.chineseprofile.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.chineseprofile.controllers.pages.imported.LoginPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.util.Config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Chinese Login Controller. Handles login and register for the account flow.
 */
@Scope("tenant")
@RequestMapping(value = "/login")
public class ChineseLoginPageController extends LoginPageController
{
	private static final String MOBILE_BIND_URL = "/mobile/register/bind";
	private static final String MOBILE_REBIND_URL = "/mobile/rebind";

	@Resource(name="httpSessionRequestCache")
	private HttpSessionRequestCache httpSessionRequestCache;


	/**
	 * This method takes data from the registration form and create a new customer account and attempts to log in using
	 * the credentials of this new user.
	 *
	 * @return true if there are no binding errors or the account does not already exists.
	 * @throws CMSItemNotFoundException
	 */
	@Override
	protected String processRegisterUserRequest(final String referer, final RegisterForm form, final BindingResult bindingResult,
			final Model model, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		final String urlFromParent = super.processRegisterUserRequest(referer, form, bindingResult, model, request, response,
				redirectModel);
		final String currentCustomerUid = getCustomerFacade().getCurrentCustomerUid();
		if (currentCustomerUid != null && currentCustomerUid.startsWith(form.getEmail().toLowerCase(Locale.getDefault())))
		{
			// auto login, redirect to bind page
			return REDIRECT_PREFIX + MOBILE_BIND_URL;
		}
		// follow parent logic, may return error
		return urlFromParent;
	}

	@Override
	protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
	{
		if (StringUtils.isNotBlank(referer) && validatateRefererAvalible(referer)
				&& StringUtils.contains(referer, request.getServerName()))
		{
			httpSessionRequestCache.saveRequest(request, response);
		}
	}

	protected boolean validatateRefererAvalible(final String referer)
	{
		return !StringUtils.endsWith(referer, "/login") && !StringUtils.endsWith(referer, MOBILE_REBIND_URL)
				&& !StringUtils.endsWith(referer, MOBILE_BIND_URL);
	}
}
