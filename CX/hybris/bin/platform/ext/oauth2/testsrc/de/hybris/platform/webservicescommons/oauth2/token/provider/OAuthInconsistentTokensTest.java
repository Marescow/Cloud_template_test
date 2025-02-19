/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.webservicescommons.oauth2.token.provider;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.webservicescommons.model.OAuthAccessTokenModel;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;
import de.hybris.platform.webservicescommons.model.OAuthRefreshTokenModel;
import de.hybris.platform.webservicescommons.oauth2.token.OAuthRevokeTokenService;
import de.hybris.platform.webservicescommons.oauth2.token.OAuthTokenService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@IntegrationTest
public class OAuthInconsistentTokensTest extends ServicelayerBaseTest
{
	private static final String CLIENT_ID = "client_" + UUID.randomUUID();
	private static final String USER_ID = "user_" + UUID.randomUUID();
	private static final String ANOTHER_USER_ID = "user_" + UUID.randomUUID();
	private static final String REUSE_REFRESH_TOKEN_PROPERTY = "oauthauthorizationserver.tokenServices.reuseRefreshToken";
	@Resource
	private OAuthRevokeTokenService oauthRevokeTokenService;
	@Resource
	private DefaultTokenServices oauthTokenServices;
	@Resource
	private TokenStore oauthTokenStore;
	@Resource
	private OAuthTokenService oauthTokenService;
	@Resource
	private ModelService modelService;
	@Resource
	private ConfigurationService configurationService;

	private OAuth2Authentication userAuthentication;
	private OAuth2RefreshToken refreshToken;
	private OAuth2AccessToken accessToken;

	private OAuthAccessTokenModel accessTokenModel;
	private OAuthRefreshTokenModel refreshTokenModel;
	private String accessTokenId;
	private String refreshTokenId;
	private OAuthAccessTokenModel anotherAccessTokenModel;

	@Before
	public void setUp()
	{
		saveNewOAuthClient();
		saveNewUser(USER_ID);
		saveNewUser(ANOTHER_USER_ID);

		userAuthentication = createUserAuthentication(USER_ID);
		accessToken = createAccessTokenWithRefreshToken();
		refreshToken = accessToken.getRefreshToken();
		oauthTokenStore.storeAccessToken(accessToken, userAuthentication);

		accessTokenModel = findAccessToken(USER_ID);
		accessTokenId = accessTokenModel.getTokenId();
		refreshTokenModel = accessTokenModel.getRefreshToken();
		assertThat(refreshTokenModel).isNotNull();
		refreshTokenId = refreshTokenModel.getTokenId();

		final var anotherAuthentication = createUserAuthentication(ANOTHER_USER_ID);
		final var anotherAccessToken = createAccessTokenWithRefreshToken();
		oauthTokenStore.storeAccessToken(anotherAccessToken, anotherAuthentication);
		anotherAccessTokenModel = findAccessToken(ANOTHER_USER_ID);
		assertThat(anotherAccessTokenModel.getRefreshToken()).isNotNull();
	}

	@After
	public void tearDown()
	{
		oauthTokenServices.setReuseRefreshToken(isRefreshTokenReuse());
	}

	private OAuthAccessTokenModel findAccessToken(final String userId)
	{
		final List<OAuthAccessTokenModel> accessTokens = oauthTokenService.getAccessTokensForClientAndUser(CLIENT_ID, userId);
		assertThat(accessTokens).hasSize(1);
		return accessTokens.get(0);
	}

	private void saveNewOAuthClient()
	{
		final OAuthClientDetailsModel clientModel = modelService.create(OAuthClientDetailsModel.class);
		clientModel.setClientId(CLIENT_ID);
		clientModel.setAuthorizedGrantTypes(Set.of("password", "refresh_token"));
		modelService.save(clientModel);
	}

	private void saveNewUser(final String userId)
	{
		final UserModel userModel = modelService.create(UserModel.class);
		userModel.setUid(userId);
		modelService.save(userModel);
	}

	private boolean isRefreshTokenReuse()
	{
		return configurationService.getConfiguration().getBoolean(REUSE_REFRESH_TOKEN_PROPERTY, false);
	}

	private static OAuth2Authentication createUserAuthentication(final String userId)
	{
		final var oAuth2Request = new OAuth2Request(null, CLIENT_ID, null, true, Set.of(),
				null, null, null, null);

		return new OAuth2Authentication(oAuth2Request, new TestingAuthenticationToken(userId, null));
	}

	private OAuth2AccessToken createAccessTokenWithRefreshToken()
	{
		final var refreshToken = new DefaultOAuth2RefreshToken(RandomStringUtils.random(10));
		final var accessToken = new DefaultOAuth2AccessToken(RandomStringUtils.random(10));
		accessToken.setRefreshToken(refreshToken);
		return accessToken;
	}

	private TokenRequest createTokenRequest(final String clientId, Set<String> scopes)
	{
		return new TokenRequest(null, clientId, scopes, null);
	}

	private void simulateTokensInconsistency()
	{
		// when remove refresh token
		oauthTokenStore.removeRefreshToken(refreshToken);

		// then
		// refresh token should be removed
		assertThat(modelService.isRemoved(refreshTokenModel)).isTrue();
		final OAuthRefreshTokenModel currentRefreshTokenModel = getRefreshTokenByTokenId(refreshTokenId);
		assertThat(currentRefreshTokenModel).as("Refresh token with tokenId: %s should not exist", refreshTokenId).isNull();

		// when store the same access token
		oauthTokenStore.storeAccessToken(accessToken, userAuthentication);

		// then
		// access token should not change
		final OAuthAccessTokenModel currentAccessTokenModel = getAccessTokenByTokenId(accessTokenId);
		assertThat(currentAccessTokenModel).as("Access token with tokenId: %s should not change", accessTokenId)
		                                   .isNotNull().isEqualTo(accessTokenModel);

		// refresh token is being recreated with existing refresh token value
		final OAuthRefreshTokenModel restoredRefreshTokenModel = getRefreshTokenByTokenId(refreshTokenId);
		// recreated refresh token is inconsistent with the refresh token reference in existing access token
		assertThat(restoredRefreshTokenModel).as("Refresh token is expected to be recreated with the same token value")
		                                     .isNotNull().isNotEqualTo(refreshTokenModel)
		                                     .matches(tokenModel -> tokenModel.getTokenId().equals(refreshTokenId));
	}

	private OAuthRefreshTokenModel getRefreshTokenByTokenId(final String refreshTokenId)
	{
		try
		{
			return oauthTokenService.getRefreshToken(refreshTokenId);
		}
		catch (final UnknownIdentifierException e)
		{
			// not found
			return null;
		}
	}

	private OAuthAccessTokenModel getAccessTokenByTokenId(final String accessTokenId)
	{
		try
		{
			return oauthTokenService.getAccessToken(accessTokenId);
		}
		catch (final UnknownIdentifierException e)
		{
			// not found
			return null;
		}
	}

	private boolean checkBothTokensChanged(final OAuth2AccessToken token, final String originalAccessTokenValue,
	                                       final String originalRefreshTokenValue)
	{
		return isAccessTokenChanged(token, originalAccessTokenValue) && isRefreshTokenChanged(token, originalRefreshTokenValue);
	}

	private boolean checkAccessTokenOnlyChanged(final OAuth2AccessToken token, final String originalAccessTokenValue,
	                                            final String originalRefreshTokenValue)
	{
		return isAccessTokenChanged(token, originalAccessTokenValue) && !isRefreshTokenChanged(token, originalRefreshTokenValue);
	}

	private boolean isAccessTokenChanged(final OAuth2AccessToken token, final String originalAccessTokenValue)
	{
		return token != null && token.getValue() != null && !token.getValue().equals(originalAccessTokenValue);
	}

	private boolean isRefreshTokenChanged(final OAuth2AccessToken token, final String originalRefreshTokenValue)
	{
		return token != null && token.getRefreshToken() != null && token.getRefreshToken().getValue() != null &&
				!token.getRefreshToken().getValue().equals(originalRefreshTokenValue);
	}

	@Test
	public void shouldFailWhenStoreNewAccessTokenIfPreviousExistsForTheSameAuthentication()
	{
		// given
		final var newAccessToken = createAccessTokenWithRefreshToken();

		// when
		final Throwable throwable = Assertions.catchThrowable(
				() -> oauthTokenStore.storeAccessToken(newAccessToken, userAuthentication));
		assertThat(throwable).as("Should not be allowed to save a new access token for the same authentication")
		                     .isInstanceOf(ModelSavingException.class);
	}

	@Test
	public void shouldStoreNewAccessTokenAfterRemovingPreviousTokenForTheSameAuthentication()
	{
		// given
		simulateTokensInconsistency();
		final OAuth2RefreshToken existingRefreshToken = oauthTokenStore.readRefreshToken(refreshToken.getValue());
		assertThat(existingRefreshToken).isNotNull();

		// when remove access token by refresh token
		oauthTokenStore.removeAccessTokenUsingRefreshToken(existingRefreshToken);

		// then
		assertThat(accessTokenModel).as("Existing access token should be removed").matches(modelService::isRemoved);
		assertThat(anotherAccessTokenModel).as("Another access token model should still exist")
		                                   .matches(not(modelService::isRemoved));
		assertThat(anotherAccessTokenModel.getRefreshToken()).as("Another refresh token model should still exist")
		                                                     .matches(not(modelService::isRemoved));
	}

	@Test
	public void shouldRevokeBothTokensWhenTokensAreInconsistent()
	{
		simulateTokensInconsistency();

		// when revoke refresh token by token value
		oauthRevokeTokenService.revokeRefreshToken(refreshToken.getValue());

		// then both tokens should be removed
		final OAuthAccessTokenModel removedAccessTokenModel = getAccessTokenByTokenId(accessTokenId);
		assertThat(removedAccessTokenModel).as("Access token model should not exist").isNull();
		final OAuthRefreshTokenModel removedRefreshTokenModel = getRefreshTokenByTokenId(refreshTokenId);
		assertThat(removedRefreshTokenModel).as("Refresh token model should not exist").isNull();
		assertThat(anotherAccessTokenModel).as("Another access token model should still exist")
		                                   .matches(not(modelService::isRemoved));
		assertThat(anotherAccessTokenModel.getRefreshToken()).as("Another refresh token model should still exist")
		                                                     .matches(not(modelService::isRemoved));
	}

	@Test
	public void shouldRefreshTokensWhenTokensAreInconsistentAndReuseRefreshTokenDisabled()
	{
		// given
		oauthTokenServices.setReuseRefreshToken(false);
		simulateTokensInconsistency();
		final TokenRequest tokenRequest = createTokenRequest(CLIENT_ID, Set.of());

		//when
		final OAuth2AccessToken newOAuth2AccessToken = oauthTokenServices.refreshAccessToken(refreshToken.getValue(),
				tokenRequest);

		// then
		// new access token and refresh token should be created
		assertThat(newOAuth2AccessToken).as("Both access token and refresh token should change")
		                                .matches(token -> checkBothTokensChanged(token, accessToken.getValue(),
				                                refreshToken.getValue()));
		assertThat(anotherAccessTokenModel).as("Another access token model should still exist")
		                                   .matches(not(modelService::isRemoved));
		assertThat(anotherAccessTokenModel.getRefreshToken()).as("Another refresh token model should still exist")
		                                                     .matches(not(modelService::isRemoved));

	}

	@Test
	public void shouldRefreshTokensWhenTokensAreInconsistentAndReuseRefreshTokenEnabled()
	{
		// given
		oauthTokenServices.setReuseRefreshToken(true);
		simulateTokensInconsistency();
		final TokenRequest tokenRequest = createTokenRequest(CLIENT_ID, Set.of());

		//when
		final OAuth2AccessToken newOAuth2AccessToken = oauthTokenServices.refreshAccessToken(refreshToken.getValue(),
				tokenRequest);

		// then
		// new access token and refresh token should be created
		assertThat(newOAuth2AccessToken).as("Only access token should change")
		                                .matches(token -> checkAccessTokenOnlyChanged(token, accessToken.getValue(),
				                                refreshToken.getValue()));
		assertThat(anotherAccessTokenModel).as("Another access token model should still exist")
		                                   .matches(not(modelService::isRemoved));
		assertThat(anotherAccessTokenModel.getRefreshToken()).as("Another refresh token model should still exist")
		                                                     .matches(not(modelService::isRemoved));
	}
}
