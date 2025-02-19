/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.webservicescommons.oauth2.token.provider;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;
import de.hybris.platform.webservicescommons.oauth2.token.OAuthRevokeTokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Sets;

@IntegrationTest
public class OAuthRefreshTokenThreadLockTest extends ServicelayerBaseTest
{

	private static final Logger LOG = LoggerFactory.getLogger(OAuthRefreshTokenThreadLockTest.class);
	private static final String ADMIN_USER = "admin";
	private static final String TEST_SCOPE = "test_scope";
	private static final String CLIENT_ID = "some_test_client_";
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 100;
	private static final int ACCESS_TOKEN_QUANTITY = 30;
	@Resource
	ModelService modelService;
	@Resource
	private HybrisOAuthTokenServices oauthTokenServices;
	@Resource
	private OAuthRevokeTokenService oauthRevokeTokenService;

	private static OAuth2Authentication createAuthentication(final String clientId)
	{
		final Authentication authentication = new HybrisOAuthTokenStoreTest.MockAuthentication(ADMIN_USER, false);
		return new OAuth2Authentication(
				new OAuth2Request(null, clientId, null, true, Sets.newHashSet(TEST_SCOPE),
						null, null, null, null), authentication);
	}

	private static TokenRequest createTokenRequest(final String clientId)
	{
		return new TokenRequest(null, clientId, List.of(TEST_SCOPE), null);
	}

	@Before
	public void setup()
	{
		createDifferentClients();
	}

	private void createDifferentClients()
	{
		for (int i = 0; i < ACCESS_TOKEN_QUANTITY; i++)
		{
			final OAuthClientDetailsModel client = new OAuthClientDetailsModel();
			client.setClientId(CLIENT_ID + i);
			client.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
			client.setAuthorizedGrantTypes(Set.of(OAuth2AccessToken.REFRESH_TOKEN));
			modelService.save(client);
		}
	}

	@Test
	public void oauthRevokeAndRefreshActionsShouldNotCauseDeadlock() throws Exception
	{
		final Tenant tenant = Registry.getCurrentTenant();
		final List<Pair<OAuth2AccessToken, String>> tokenList = createAccessTokenList(ACCESS_TOKEN_QUANTITY);
		final ExecutorService executor1 = Executors.newSingleThreadExecutor();
		final ExecutorService executor2 = Executors.newSingleThreadExecutor();
		try
		{
			for (final Pair pair : tokenList)
			{
				executor1.execute(() -> {
					try
					{
						Registry.setCurrentTenant(tenant);
						revokeRefreshToken(pair);
					}
					catch (final Exception e)
					{
						LOG.info("Error while revoking refresh token", e);
					}
				});
				executor2.execute(() -> {
					try
					{
						Registry.setCurrentTenant(tenant);
						refreshAccessToken(pair);
					}
					catch (final Exception e)
					{
						LOG.info("Error while refreshing access token", e);
					}
				});
			}
		}
		finally
		{
			shutdownExecutorAndAssertIfIsTerminated(executor1);
			shutdownExecutorAndAssertIfIsTerminated(executor2);
		}
	}

	private void shutdownExecutorAndAssertIfIsTerminated(final ExecutorService executorService) throws Exception
	{
		executorService.shutdown();
		final boolean terminated = executorService.awaitTermination(60, TimeUnit.SECONDS);
		assertThat(terminated).isTrue();
	}

	private List<Pair<OAuth2AccessToken, String>> createAccessTokenList(final int accessTokenQuantity)
	{
		final List<Pair<OAuth2AccessToken, String>> tokenList = new ArrayList<>();
		for (int i = 0; i < accessTokenQuantity; i++)
		{
			final OAuth2Authentication TEST_OAUTH2_AUTHENTICATION = createAuthentication(CLIENT_ID + i);
			final OAuth2AccessToken accessToken = oauthTokenServices.createAccessToken(TEST_OAUTH2_AUTHENTICATION);
			tokenList.add(Pair.of(accessToken, CLIENT_ID + i));
		}
		return tokenList;
	}

	private void revokeRefreshToken(final Pair<OAuth2AccessToken, String> accessToken)
	{
		LOG.info("Trying to revoke token client: {}", accessToken.getRight());
		oauthRevokeTokenService.revokeRefreshToken(accessToken.getLeft().getRefreshToken().getValue());
		LOG.info("Stopping to revoke token client: {}", accessToken.getRight());
	}

	private void refreshAccessToken(final Pair<OAuth2AccessToken, String> accessToken)
	{
		LOG.info("Trying to refresh token client: {}", accessToken.getRight());
		oauthTokenServices.refreshAccessToken(accessToken.getLeft().getRefreshToken().getValue(),
				createTokenRequest(accessToken.getRight()));
		LOG.info("Stopping to refresh token client: {}", accessToken.getRight());
	}

}