/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.webservicescommons.oauth2.token.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.oauth2.constants.OAuth2Constants;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.test.RunnerCreator;
import de.hybris.platform.test.TestThreadsHolder;
import de.hybris.platform.util.Config;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.google.common.collect.Sets;

@IntegrationTest
public class AuthorizationServerTokenServicesIntegrationTest extends ServicelayerTest
{

	private static final String ADMIN_USER = "admin";
	private static final String TEST_SCOPE = "test_scope";
	private static final String CLIENT_ID = "some_test_client_1";
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 100;
	private static final int REFRESH_TOKEN_THREADS_COUNT = 10;
	private static final int CONCURRENT_REFRESH_TOKEN_TERMINATION_WAIT_TIME_SECONDS = 60;
	private static final int THREADS_HOLDER_WAITING_FOR_PREPARED_TIMEOUT_SECONDS = 60;
	private static final TokenRequest TEST_TOKEN_REQUEST = createTokenRequest();
	private static final OAuth2Authentication TEST_OAUTH2_AUTHENTICATION = createAuthentication();
	private static boolean reuseRefreshTokenFlagInitialValue;

	@Resource
	private HybrisOAuthTokenServices oauthTokenServices;

	@Resource
	private ModelService modelService;

	@BeforeClass
	public static void beforeAll()
	{
		// ensure refreshWithLock flag is set to true
		assumeTrue(Config.getBoolean(OAuth2Constants.REFRESH_WITH_LOCK_CONFIGURATION_PROPERTY_NAME,
				OAuth2Constants.REFRESH_WITH_LOCK_CONFIGURATION_PROPERTY_DEFAULT_VALUE));
		reuseRefreshTokenFlagInitialValue = Config.getBoolean(OAuth2Constants.REUSE_REFRESH_TOKEN_PROPERTY_NAME,
				OAuth2Constants.REUSE_REFRESH_TOKEN_PROPERTY_DEFAULT_VALUE);
	}

	@Before
	public void setup()
	{
		final OAuthClientDetailsModel client = new OAuthClientDetailsModel();
		client.setClientId(CLIENT_ID);
		client.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
		client.setAuthorizedGrantTypes(Set.of(OAuth2AccessToken.REFRESH_TOKEN));
		modelService.save(client);
	}

	@After
	public void cleanUp()
	{
		oauthTokenServices.setReuseRefreshToken(reuseRefreshTokenFlagInitialValue);
	}

	@Test
	public void shouldAllowRefreshTokenToBeConsumedOnlyOnceWhenReuseRefreshTokenFlagIsSetToFalseAndRefreshingConcurrently()
	{
		// given
		oauthTokenServices.setReuseRefreshToken(false);
		final OAuth2AccessToken initialAccessToken = oauthTokenServices.createAccessToken(TEST_OAUTH2_AUTHENTICATION);
		final String refreshToken = initialAccessToken.getRefreshToken().getValue();

		// when
		final TestResult testResult = refreshAccessTokenConcurrently(refreshToken);

		// then
		assertThat(testResult.refreshedAccessTokens())
				.hasSize(1);
		final OAuth2AccessToken refreshedAccessToken = testResult.refreshedAccessTokens().get(0);
		assertThat(refreshedAccessToken.getValue()).isNotEqualTo(initialAccessToken.getValue());
		assertThat(refreshedAccessToken.getRefreshToken())
				.satisfies(rt -> assertThat(rt.getValue()).isNotEqualTo(
						refreshToken));
		assertThat(testResult.errors())
				.filteredOn(error -> error instanceof InvalidGrantException)
				.hasSize(REFRESH_TOKEN_THREADS_COUNT - 1);

	}

	@Test
	public void shouldAllowRefreshTokenToBeConsumedManyTimesWithoutErrorsWhenReuseRefreshTokenFlagIsSetToTrueAndRefreshingConcurrently()
	{
		// given
		oauthTokenServices.setReuseRefreshToken(true);
		final OAuth2AccessToken initialAccessToken = oauthTokenServices.createAccessToken(TEST_OAUTH2_AUTHENTICATION);
		final String refreshToken = initialAccessToken.getRefreshToken().getValue();

		// when
		final TestResult testResult = refreshAccessTokenConcurrently(refreshToken);

		// then
		final Set<String> distinctAccessTokensValues = testResult.refreshedAccessTokens()
		                                                         .stream()
		                                                         .map(OAuth2AccessToken::getValue)
		                                                         .collect(
				                                                         Collectors.toSet());
		assertThat(distinctAccessTokensValues)
				.doesNotContain(initialAccessToken.getValue())
				.size()
				.isGreaterThan(1);

		final Set<String> distinctRefreshTokensValues = testResult.refreshedAccessTokens()
		                                                          .stream()
		                                                          .map(at -> at.getRefreshToken().getValue())
		                                                          .collect(Collectors.toSet());
		assertThat(distinctRefreshTokensValues)
				.hasSize(1)
				.containsOnly(initialAccessToken.getRefreshToken().getValue());
		assertThat(testResult.errors())
				.filteredOn(error -> error instanceof InvalidGrantException)
				.hasSize(REFRESH_TOKEN_THREADS_COUNT - distinctAccessTokensValues.size());
	}

	@Test
	public void shouldAllowRefreshTokenToBeConsumedOnlyOnceWhenReuseRefreshTokenFlagIsSetToFalseAndRefreshingSerially()
	{
		// given
		oauthTokenServices.setReuseRefreshToken(false);
		final OAuth2AccessToken initialAccessToken = oauthTokenServices.createAccessToken(TEST_OAUTH2_AUTHENTICATION);
		final String refreshToken = initialAccessToken.getRefreshToken().getValue();

		// when
		final TestResult testResult = refreshAccessTokenSerially(refreshToken);

		// then
		assertThat(testResult.refreshedAccessTokens())
				.hasSize(1);
		final OAuth2AccessToken refreshedAccessToken = testResult.refreshedAccessTokens().get(0);
		assertThat(refreshedAccessToken.getValue()).isNotEqualTo(initialAccessToken.getValue());
		assertThat(refreshedAccessToken.getRefreshToken())
				.satisfies(rt -> assertThat(rt.getValue()).isNotEqualTo(
						initialAccessToken.getRefreshToken().getValue()));
		assertThat(testResult.errors())
				.filteredOn(error -> error instanceof InvalidGrantException)
				.hasSize(REFRESH_TOKEN_THREADS_COUNT - 1);
	}

	@Test
	public void shouldAllowRefreshTokenToBeConsumedManyTimesWithoutErrorsWhenReuseRefreshTokenFlagIsSetToTrueAndRefreshingSerially()
	{
		// given
		oauthTokenServices.setReuseRefreshToken(true);
		final OAuth2AccessToken initialAccessToken = oauthTokenServices.createAccessToken(TEST_OAUTH2_AUTHENTICATION);
		final String refreshToken = initialAccessToken.getRefreshToken().getValue();

		// when
		final TestResult testResult = refreshAccessTokenSerially(refreshToken);

		// then
		final Set<String> distinctAccessTokensValues = testResult.refreshedAccessTokens()
		                                                         .stream()
		                                                         .map(OAuth2AccessToken::getValue)
		                                                         .collect(
				                                                         Collectors.toSet());
		assertThat(distinctAccessTokensValues)
				.doesNotContain(initialAccessToken.getValue())
				.size()
				.isGreaterThan(1);

		final Set<String> distinctRefreshTokensValues = testResult.refreshedAccessTokens()
		                                                          .stream()
		                                                          .map(at -> at.getRefreshToken().getValue())
		                                                          .collect(Collectors.toSet());
		assertThat(distinctRefreshTokensValues)
				.hasSize(1)
				.containsOnly(initialAccessToken.getRefreshToken().getValue());
		assertThat(testResult.errors())
				.filteredOn(error -> error instanceof InvalidGrantException)
				.hasSize(REFRESH_TOKEN_THREADS_COUNT - distinctAccessTokensValues.size());
	}

	private TestResult refreshAccessTokenConcurrently(final String refreshToken)
	{
		final TestThreadsHolder<TokenRefreshRunner> threadsHolder = createTestThreadsHolder(refreshToken);
		threadsHolder.waitForPrepared(THREADS_HOLDER_WAITING_FOR_PREPARED_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		threadsHolder.startAll();
		threadsHolder.waitAndDestroy(CONCURRENT_REFRESH_TOKEN_TERMINATION_WAIT_TIME_SECONDS);

		final List<OAuth2AccessToken> refreshedAccessTokens = threadsHolder.getRunners()
		                                                                   .stream()
		                                                                   .map(TokenRefreshRunner::getRefreshedAccessToken)
		                                                                   .filter(Objects::nonNull)
		                                                                   .toList();
		final List<Throwable> errors = threadsHolder.getErrors()
		                                            .values()
		                                            .stream()
		                                            .toList();

		return new TestResult(refreshedAccessTokens, errors);
	}

	private TestThreadsHolder<TokenRefreshRunner> createTestThreadsHolder(final String refreshToken)
	{
		return new TestThreadsHolder<>(REFRESH_TOKEN_THREADS_COUNT,
				new RunnerCreator<>()
				{
					@Override
					public TokenRefreshRunner newRunner(final int threadNumber)
					{
						return new TokenRefreshRunner(oauthTokenServices, refreshToken,
								TEST_TOKEN_REQUEST);
					}
				}, true);
	}

	private TestResult refreshAccessTokenSerially(final String refreshToken)
	{
		final List<Pair<OAuth2AccessToken, Throwable>> results = IntStream.range(0, REFRESH_TOKEN_THREADS_COUNT)
		                                                                  .mapToObj(i -> {
			                                                                  try
			                                                                  {
				                                                                  return Pair.<OAuth2AccessToken, Throwable>of(
						                                                                  oauthTokenServices.refreshAccessToken(
								                                                                  refreshToken,
								                                                                  TEST_TOKEN_REQUEST), null);
			                                                                  }
			                                                                  catch (final Throwable e)
			                                                                  {
				                                                                  return Pair.<OAuth2AccessToken, Throwable>of(
						                                                                  null, e);
			                                                                  }
		                                                                  })
		                                                                  .toList();
		final List<OAuth2AccessToken> refreshedAccessTokens = results.stream().map(Pair::getLeft)
		                                                             .filter(Objects::nonNull)
		                                                             .toList();
		final List<Throwable> errors = results.stream().map(Pair::getRight)
		                                      .filter(Objects::nonNull)
		                                      .toList();
		return new TestResult(refreshedAccessTokens, errors);
	}

	private record TestResult(List<OAuth2AccessToken> refreshedAccessTokens, List<Throwable> errors)
	{
	}

	private static class TokenRefreshRunner implements Runnable
	{

		private final AuthorizationServerTokenServices authorizationServerTokenServices;
		private final String refreshTokenValue;
		private final TokenRequest tokenRequest;

		private volatile OAuth2AccessToken refreshedAccessToken;

		public TokenRefreshRunner(final AuthorizationServerTokenServices authorizationServerTokenServices,
		                          final String refreshTokenValue, final TokenRequest tokenRequest)
		{
			this.authorizationServerTokenServices = authorizationServerTokenServices;
			this.refreshTokenValue = refreshTokenValue;
			this.tokenRequest = tokenRequest;
		}

		@Override
		public void run()
		{
			this.refreshedAccessToken = authorizationServerTokenServices.refreshAccessToken(refreshTokenValue,
					tokenRequest);
		}

		public OAuth2AccessToken getRefreshedAccessToken()
		{
			return refreshedAccessToken;
		}
	}

	private static TokenRequest createTokenRequest()
	{
		return new TokenRequest(null, CLIENT_ID, List.of(TEST_SCOPE), null);
	}

	private static OAuth2Authentication createAuthentication()
	{
		final Authentication authentication = new HybrisOAuthTokenStoreTest.MockAuthentication(ADMIN_USER, false);

		return new OAuth2Authentication(
				new OAuth2Request(null, CLIENT_ID, null, true, Sets.newHashSet(TEST_SCOPE), null, null, null, null),
				authentication);
	}
}
