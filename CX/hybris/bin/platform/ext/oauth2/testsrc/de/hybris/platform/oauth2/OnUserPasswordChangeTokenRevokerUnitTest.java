/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.oauth2;

import static de.hybris.platform.core.Constants.WEB.INVALIDATE_CURRENT_AUTHENTICATION;
import static de.hybris.platform.oauth2.OnUserPasswordChangeTokenRevoker.ENABLED_CONFIG_KEY;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.user.listener.PasswordChangeEvent;
import de.hybris.platform.webservicescommons.oauth2.token.OAuthRevokeTokenService;

import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class OnUserPasswordChangeTokenRevokerUnitTest
{
	private static final String USER_ID = "userId";
	private static final String CURRENT_TOKEN = "currentToken";

	@Mock
	private ConfigurationService configurationService;
	@Mock
	private OAuthRevokeTokenService oauthRevokeTokenService;
	@Mock
	private Configuration config;
	@Mock
	private PasswordChangeEvent event;
	@Mock
	private SecurityContext securityContext;
	@Mock
	private Authentication authentication;
	@Mock
	private OAuth2AuthenticationDetails details;
	private OnUserPasswordChangeTokenRevoker tokenRevoker;

	@Before
	public void prepare()
	{
		tokenRevoker = new OnUserPasswordChangeTokenRevoker(oauthRevokeTokenService, configurationService);
		when(configurationService.getConfiguration()).thenReturn(config);
		when(event.getUserId()).thenReturn(USER_ID);
	}

	@Test
	public void shouldRevokeTokensExceptCurrentTokenWhenPasswordChanged()
	{
		//given
		when(config.getBoolean(ENABLED_CONFIG_KEY, true)).thenReturn(Boolean.TRUE);
		when(config.getBoolean(INVALIDATE_CURRENT_AUTHENTICATION, false)).thenReturn(Boolean.FALSE);
		mockAuthentication();

		try (final MockedStatic<SecurityContextHolder> securityContextHolderMock = Mockito.mockStatic(
				SecurityContextHolder.class))
		{
			securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

			//when
			tokenRevoker.passwordChanged(event);
		}

		//then
		verify(oauthRevokeTokenService).revokeUserAccessTokens(USER_ID, Set.of(CURRENT_TOKEN));
	}

	@Test
	public void shouldRevokeAllTokensWhenInvalidateCurrentTokenIsTrue()
	{
		//given
		when(config.getBoolean(ENABLED_CONFIG_KEY, true)).thenReturn(Boolean.TRUE);
		when(config.getBoolean(INVALIDATE_CURRENT_AUTHENTICATION, false)).thenReturn(Boolean.TRUE);

		//when
		tokenRevoker.passwordChanged(event);

		//then
		verify(oauthRevokeTokenService).revokeUserAccessTokens(USER_ID, null);
	}

	@Test
	public void shouldNotRevokeTokensWhenRevokeFlagIsDisabled()
	{
		//given
		when(config.getBoolean(ENABLED_CONFIG_KEY, true)).thenReturn(Boolean.FALSE);

		//when
		tokenRevoker.passwordChanged(event);

		//then
		verifyNoInteractions(oauthRevokeTokenService);
	}

	@Test
	public void shouldNotRevokeTokensWhenEventIsNull()
	{
		//when
		tokenRevoker.passwordChanged(null);

		//then
		verifyNoInteractions(oauthRevokeTokenService);
	}

	private void mockAuthentication()
	{
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getDetails()).thenReturn(details);
		when(details.getTokenValue()).thenReturn(CURRENT_TOKEN);
	}
}
