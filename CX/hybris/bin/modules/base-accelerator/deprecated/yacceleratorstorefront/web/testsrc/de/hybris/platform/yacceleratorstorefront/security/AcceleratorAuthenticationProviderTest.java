/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.yacceleratorstorefront.security;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorstorefrontcommons.security.BruteForceAttackCounter;
import de.hybris.platform.core.Constants;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Calendar;

import org.mockito.MockedStatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.servicelayer.user.UserVerificationTokenAndUserData;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserVerificationTokenService;
import de.hybris.platform.util.Config;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AcceleratorAuthenticationProviderTest
{
	private AcceleratorAuthenticationProvider acceleratorAuthenticationProvider;

	private Authentication authentication;

	@Mock
	protected UserVerificationTokenService userVerificationTokenService;


	@Mock
	private BruteForceAttackCounter bruteForceAttackCounter;

	@Mock
	private UserModel userModel;

	@Mock
	UserService userService;

	@Before
	public void setUp()
	{
		acceleratorAuthenticationProvider = new AcceleratorAuthenticationProvider();
		acceleratorAuthenticationProvider.setBruteForceAttackCounter(bruteForceAttackCounter);
		acceleratorAuthenticationProvider.setUserService(userService);
		acceleratorAuthenticationProvider.setUserVerificationTokenService(userVerificationTokenService);
		authentication = new UsernamePasswordAuthenticationToken("username", "password");
	}

	@Test(expected = BadCredentialsException.class)
	public void testLoginForUserNotPartOfCustomerGroup()
	{
		try (MockedStatic<Config> config = Mockito.mockStatic(Config.class))
		{
			config.when(() -> Config.getBoolean(WebConstants.OTP_CUSTOMER_LOGIN_ENABLED, false)).thenReturn(false);
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 2);
			userModel.setDeactivationDate(calendar.getTime());
			final UserGroupModel userGroupModel = mock(UserGroupModel.class);
			given(userService.getUserForUID(Mockito.anyString())).willReturn(userModel);
			given(userService.getUserGroupForUID(Constants.USER.CUSTOMER_USERGROUP)).willReturn(userGroupModel);
			given(Boolean.valueOf(userService.isMemberOfGroup(userModel, userGroupModel))).willReturn(Boolean.FALSE);

			acceleratorAuthenticationProvider.authenticate(authentication);
		}
	}

	@Test(expected = BadCredentialsException.class)
	public void testLoginForUserUsingUnknownToken()
	{
		try (MockedStatic<Config> config = Mockito.mockStatic(Config.class))
		{
			config.when(() -> Config.getBoolean(WebConstants.OTP_CUSTOMER_LOGIN_ENABLED, false)).thenReturn(true);
			final UserVerificationTokenAndUserData userVerificationTokenAndUserData = mock(UserVerificationTokenAndUserData.class);
			given(userVerificationTokenService.lookupTokenWithUser(SAPUserVerificationPurpose.LOGIN, "username")).willReturn(userVerificationTokenAndUserData);
			given(userVerificationTokenAndUserData.getUser()).willThrow(UnknownIdentifierException.class);

			acceleratorAuthenticationProvider.authenticate(authentication);
		}
	}

	@Test(expected = BadCredentialsException.class)
	public void testDisabledUserShouldNotBeConsideredABruteForceAttack()
	{
		try (MockedStatic<Config> config = Mockito.mockStatic(Config.class))
		{
			config.when(() -> Config.getBoolean(WebConstants.OTP_CUSTOMER_LOGIN_ENABLED, false)).thenReturn(false);
			final String uid = "testuser@hybris.com";
			userModel.setUid(uid);
			userModel.setLoginDisabled(true);
			when(userService.getUserForUID(anyString())).thenReturn(userModel);

			acceleratorAuthenticationProvider.authenticate(authentication);
			verify(bruteForceAttackCounter).resetUserCounter(uid);
		}
	}
}
