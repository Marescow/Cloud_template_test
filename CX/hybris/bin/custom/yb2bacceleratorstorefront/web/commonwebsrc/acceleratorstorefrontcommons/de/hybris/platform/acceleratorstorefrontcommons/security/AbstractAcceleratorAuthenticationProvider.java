/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorstorefrontcommons.security;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.servicelayer.user.UserVerificationTokenAndUserData;
import de.hybris.platform.servicelayer.user.UserVerificationTokenService;
import de.hybris.platform.util.Config;



import de.hybris.platform.core.Constants;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.spring.security.CoreAuthenticationProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Derived authentication provider supporting additional authentication checks. See
 * {@link de.hybris.platform.spring.security.RejectUserPreAuthenticationChecks}.
 *
 * <ul>
 * <li>prevent login without password for users created via CSCockpit</li>
 * <li>prevent login as user in group admingroup</li>
 * </ul>
 *
 * any login as admin disables SearchRestrictions and therefore no page can be viewed correctly
 */
public abstract class AbstractAcceleratorAuthenticationProvider extends CoreAuthenticationProvider
{
	private static final Logger LOG = Logger.getLogger(AbstractAcceleratorAuthenticationProvider.class);

	public static final String CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS = "CoreAuthenticationProvider.badCredentials";
	public static final String BAD_CREDENTIALS = "Bad credentials";

	private BruteForceAttackCounter bruteForceAttackCounter;
	private UserService userService;
	private ModelService modelService;
	protected UserVerificationTokenService userVerificationTokenService;



	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
		boolean isBruteForceAttack = false;
		UserModel userModel;

		try
		{
			if (Config.getBoolean(WebConstants.OTP_CUSTOMER_LOGIN_ENABLED, false))
			{
				UserVerificationTokenAndUserData userVerificationTokenAndUserData = userVerificationTokenService.lookupTokenWithUser(
						SAPUserVerificationPurpose.LOGIN, username);
				userModel = userVerificationTokenAndUserData.getUser();
			}
			else
			{
				isBruteForceAttack = bruteForceAttackCounter.isAttack(username);
				userModel = userService.getUserForUID(StringUtils.lowerCase(username));
			}	
		}
		catch (final UnknownIdentifierException e)
		{
			if (isBruteForceAttack)
			{
				LOG.warn("Brute force attack attempt for non existing user name " + username);
			}
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS), e);
		}

		if (userModel.isLoginDisabled())
 		{
			LOG.info("Skipping authentication. User's login is disabled");
			bruteForceAttackCounter.resetUserCounter(userModel.getUid());
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
		}

		if (isBruteForceAttack)
		{
			userModel.setLoginDisabled(true);
			getModelService().save(userModel);
			bruteForceAttackCounter.resetUserCounter(userModel.getUid());
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
		}

		if (!userService.isMemberOfGroup(userModel, getUserService().getUserGroupForUID(Constants.USER.CUSTOMER_USERGROUP)))
		{
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
		}

		return super.authenticate(authentication);
	}

	/**
	 * @see de.hybris.platform.spring.security.CoreAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails,
	 *      org.springframework.security.authentication.AbstractAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(final UserDetails details, final AbstractAuthenticationToken authentication)
			throws AuthenticationException
	{
		super.additionalAuthenticationChecks(details, authentication);
		// Check if user has supplied no password
		if (StringUtils.isEmpty((String) authentication.getCredentials()))
		{
			throw new BadCredentialsException("Login without password");
		}
	}

	protected BruteForceAttackCounter getBruteForceAttackCounter()
	{
		return bruteForceAttackCounter;
	}

	@Required
	public void setBruteForceAttackCounter(final BruteForceAttackCounter bruteForceAttackCounter)
	{
		this.bruteForceAttackCounter = bruteForceAttackCounter;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	public void setUserVerificationTokenService(final UserVerificationTokenService userVerificationTokenService)
	{
		this.userVerificationTokenService = userVerificationTokenService;
	}
}
