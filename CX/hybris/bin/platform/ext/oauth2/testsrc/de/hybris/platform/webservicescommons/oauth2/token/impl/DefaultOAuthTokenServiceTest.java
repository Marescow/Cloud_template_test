/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.webservicescommons.oauth2.token.impl;

import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.oauth2.constants.OAuth2Constants;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.testframework.PropertyConfigSwitcher;
import de.hybris.platform.webservicescommons.model.OAuthAccessTokenModel;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;
import de.hybris.platform.webservicescommons.model.OAuthRefreshTokenModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;


@IntegrationTest
public class DefaultOAuthTokenServiceTest extends ServicelayerTransactionalTest
{
	private static final String ACCESS_TOKEN_ID = "1";
	private static final String ACCESS_TOKEN_OBJECT = "accessTokenObject";
	private static final String REFRESH_TOKEN_ID = "2";
	private static final String REFRESH_TOKEN_OBJECT = "refreshTokenObject";
	private static final String AUTHENTICATION_ID = "userAuthId";
	private static final String AUTHENTICATION_OBJECT = "userAuthObject";
	private static final String CLIENT_ID = "clientId";
	private static final String USER_NAME = "userName";


	private static final String ACCESS_TOKEN_ID_2 = "3";
	private static final String ACCESS_TOKEN_OBJECT_2 = "accessTokenObject2";
	private static final String REFRESH_TOKEN_ID_2 = "4";
	private static final String REFRESH_TOKEN_OBJECT_2 = "refreshTokenObject2";
	private static final String AUTHENTICATION_ID_2 = "userAuthId2";
	private static final String AUTHENTICATION_OBJECT_2 = "userAuthObject2";
	private static final String CLIENT_ID_2 = "clientId2";
	private static final String USER_NAME_2 = "userName2";

	private static final String NOT_EXISTING_ID = "notExistingId";

	private final PropertyConfigSwitcher optimizeSaveForAccessToken = new PropertyConfigSwitcher("oauth2.optimize.accesstoken.save.enabled");

	private final PropertyConfigSwitcher refreshWithLock = new PropertyConfigSwitcher("oauthauthorizationserver.tokenServices.refreshWithLock");
	@Resource
	private DefaultOAuthTokenService oauthTokenService;
	@Resource
	private ModelService modelService;
	private UserModel user;
	private UserModel user2;
	private OAuthClientDetailsModel client;
	private OAuthClientDetailsModel client2;

	@Before
	public void setUp()
	{
		client = new OAuthClientDetailsModel();
		client.setClientId(CLIENT_ID);
		client2 = new OAuthClientDetailsModel();
		client2.setClientId(CLIENT_ID_2);
		user = new UserModel();
		user.setUid(USER_NAME);
		user2 = new UserModel();
		user2.setUid(USER_NAME_2);
		modelService.saveAll(user, user2, client, client2);
		final OAuthAccessTokenModel accessToken = new OAuthAccessTokenModel();
		accessToken.setTokenId(ACCESS_TOKEN_ID);
		accessToken.setToken(ACCESS_TOKEN_OBJECT);
		accessToken.setAuthenticationId(AUTHENTICATION_ID);
		accessToken.setAuthentication(AUTHENTICATION_OBJECT);
		accessToken.setClient(client);
		accessToken.setUser(user);
		final OAuthRefreshTokenModel refreshToken = new OAuthRefreshTokenModel();
		refreshToken.setTokenId(REFRESH_TOKEN_ID);
		refreshToken.setToken(REFRESH_TOKEN_OBJECT);
		refreshToken.setAuthentication(AUTHENTICATION_OBJECT);
		accessToken.setRefreshToken(refreshToken);

		oauthTokenService.saveAccessToken(accessToken);
	}

	@After
	public void tearDown()
	{
		optimizeSaveForAccessToken.switchBackToDefault();
		refreshWithLock.switchBackToDefault();
	}

	@Test
	public void getAccessTokenTest()
	{
		final OAuthAccessTokenModel accessToken = oauthTokenService.getAccessToken(ACCESS_TOKEN_ID);
		Assert.assertEquals(ACCESS_TOKEN_ID, accessToken.getTokenId());
		Assert.assertEquals(ACCESS_TOKEN_OBJECT, accessToken.getToken());
		Assert.assertEquals(AUTHENTICATION_ID, accessToken.getAuthenticationId());
		Assert.assertEquals(AUTHENTICATION_OBJECT, accessToken.getAuthentication());
		Assert.assertEquals(CLIENT_ID, accessToken.getClient().getClientId());
		Assert.assertEquals(USER_NAME, accessToken.getUser().getUid());
		Assert.assertNotNull(accessToken.getRefreshToken());
		Assert.assertEquals(REFRESH_TOKEN_ID, accessToken.getRefreshToken().getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT, accessToken.getRefreshToken().getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT, accessToken.getRefreshToken().getAuthentication());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccessTokenWithNullParameterTest()
	{
		oauthTokenService.getAccessToken(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void getAccessTokenWithIncorrectParameterTest()
	{
		oauthTokenService.getAccessToken(NOT_EXISTING_ID);
	}

	@Test
	public void getRefreshTokenTest()
	{
		final OAuthRefreshTokenModel refreshToken = oauthTokenService.getRefreshToken(REFRESH_TOKEN_ID);
		Assert.assertEquals(REFRESH_TOKEN_ID, refreshToken.getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT, refreshToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT, refreshToken.getAuthentication());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getRefreshTokenWithNullParameterTest()
	{
		oauthTokenService.getRefreshToken(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void getRefreshTokenWithIncorrectParameterTest()
	{
		oauthTokenService.getRefreshToken(NOT_EXISTING_ID);
	}

	@Test
	public void getAccessTokenForAuthenticationTest()
	{
		final OAuthAccessTokenModel accessToken = oauthTokenService.getAccessTokenForAuthentication(AUTHENTICATION_ID);
		Assert.assertEquals(ACCESS_TOKEN_ID, accessToken.getTokenId());
		Assert.assertEquals(AUTHENTICATION_ID, accessToken.getAuthenticationId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccessTokenForAuthenticationWithNullParamTest()
	{
		oauthTokenService.getAccessTokenForAuthentication(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void getAccessTokenForAuthenticationWithIncorrectParamTest()
	{
		oauthTokenService.getAccessTokenForAuthentication(NOT_EXISTING_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccessTokenForClientWithNullParamTest()
	{
		oauthTokenService.getAccessTokensForClient(null);
	}

	@Test
	public void getAccessTokenForClientTest()
	{
		final List<OAuthAccessTokenModel> resultList = oauthTokenService.getAccessTokensForClient(CLIENT_ID);
		Assert.assertNotNull(resultList);
		Assert.assertEquals(1, resultList.size());
		final OAuthAccessTokenModel accessToken = resultList.get(0);
		Assert.assertEquals(ACCESS_TOKEN_ID, accessToken.getTokenId());
		Assert.assertEquals(CLIENT_ID, accessToken.getClient().getClientId());
	}

	@Test
	public void getAccessTokenForUserTest()
	{
		final List<OAuthAccessTokenModel> resultList = oauthTokenService.getAccessTokensForUser(USER_NAME);
		Assert.assertNotNull(resultList);
		Assert.assertEquals(1, resultList.size());
		final OAuthAccessTokenModel accessToken = resultList.get(0);
		Assert.assertEquals(ACCESS_TOKEN_ID, accessToken.getTokenId());
		Assert.assertEquals(USER_NAME, accessToken.getUser().getUid());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccessTokenForUserWithNullParamTest()
	{
		oauthTokenService.getAccessTokensForUser(null);
	}

	@Test
	public void getAccessTokenForClientAndUserTest()
	{
		final List<OAuthAccessTokenModel> resultList = oauthTokenService.getAccessTokensForClientAndUser(CLIENT_ID, USER_NAME);
		Assert.assertNotNull(resultList);
		Assert.assertEquals(1, resultList.size());
		final OAuthAccessTokenModel accessToken = resultList.get(0);
		Assert.assertEquals(ACCESS_TOKEN_ID, accessToken.getTokenId());
		Assert.assertEquals(USER_NAME, accessToken.getUser().getUid());
		Assert.assertEquals(CLIENT_ID, accessToken.getClient().getClientId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccessTokenForNullClientAndUserTest()
	{
		oauthTokenService.getAccessTokensForClientAndUser(null, USER_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccessTokenForClientAndNullUserTest()
	{
		oauthTokenService.getAccessTokensForClientAndUser(CLIENT_ID, null);
	}

	@Test
	public void saveAccessTokenModelTest()
	{
		OAuthAccessTokenModel accessToken = new OAuthAccessTokenModel();
		accessToken.setTokenId(ACCESS_TOKEN_ID_2);
		accessToken.setToken(ACCESS_TOKEN_OBJECT_2);
		accessToken.setAuthenticationId(AUTHENTICATION_ID_2);
		accessToken.setAuthentication(AUTHENTICATION_OBJECT_2);
		accessToken.setClient(client2);
		accessToken.setUser(user2);
		final OAuthRefreshTokenModel refreshToken = new OAuthRefreshTokenModel();
		refreshToken.setTokenId(REFRESH_TOKEN_ID_2);
		refreshToken.setToken(REFRESH_TOKEN_OBJECT_2);
		refreshToken.setAuthentication(AUTHENTICATION_OBJECT_2);
		accessToken.setRefreshToken(refreshToken);

		oauthTokenService.saveAccessToken(accessToken);

		accessToken = oauthTokenService.getAccessToken(ACCESS_TOKEN_ID_2);
		Assert.assertEquals(ACCESS_TOKEN_ID_2, accessToken.getTokenId());
		Assert.assertEquals(ACCESS_TOKEN_OBJECT_2, accessToken.getToken());
		Assert.assertEquals(AUTHENTICATION_ID_2, accessToken.getAuthenticationId());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, accessToken.getAuthentication());
		Assert.assertEquals(CLIENT_ID_2, accessToken.getClient().getClientId());
		Assert.assertEquals(USER_NAME_2, accessToken.getUser().getUid());
		Assert.assertNotNull(accessToken.getRefreshToken());
		Assert.assertEquals(REFRESH_TOKEN_ID_2, accessToken.getRefreshToken().getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT_2, accessToken.getRefreshToken().getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, accessToken.getRefreshToken().getAuthentication());
	}

	@Test
	public void saveDuplicatedAccessTokenModelTest()
	{
		final OAuthAccessTokenModel accessToken = new OAuthAccessTokenModel();
		accessToken.setTokenId(ACCESS_TOKEN_ID);
		accessToken.setToken(ACCESS_TOKEN_OBJECT_2);
		accessToken.setAuthenticationId(AUTHENTICATION_ID_2);
		accessToken.setAuthentication(AUTHENTICATION_OBJECT_2);
		accessToken.setClient(client);
		accessToken.setUser(user2);

		try
		{
			oauthTokenService.saveAccessToken(accessToken);
		}
		catch (final ModelSavingException e)
		{
			Assert.assertTrue(e.getMessage().contains("ambiguous unique keys {tokenId=1} for model OAuthAccessTokenModel"));
			return;
		}
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void saveAccessTokenModelWithNullParamTest()
	{
		oauthTokenService.saveAccessToken(null);
	}

	@Test
	public void createAccessTokenTest()
	{
		final OAuthRefreshTokenModel refreshToken = new OAuthRefreshTokenModel();
		refreshToken.setTokenId(REFRESH_TOKEN_ID_2);
		refreshToken.setToken(REFRESH_TOKEN_OBJECT_2);
		refreshToken.setAuthentication(AUTHENTICATION_OBJECT_2);

		final OAuthAccessTokenModel newAccessToken = oauthTokenService.saveAccessToken(ACCESS_TOKEN_ID_2, ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2,
				AUTHENTICATION_OBJECT_2, USER_NAME_2,
				CLIENT_ID_2, refreshToken);

		Assert.assertEquals(ACCESS_TOKEN_ID_2, newAccessToken.getTokenId());
		Assert.assertEquals(ACCESS_TOKEN_OBJECT_2, newAccessToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, newAccessToken.getAuthentication());
		Assert.assertEquals(AUTHENTICATION_ID_2, newAccessToken.getAuthenticationId());
		Assert.assertEquals(CLIENT_ID_2, newAccessToken.getClient().getClientId());
		Assert.assertEquals(USER_NAME_2, newAccessToken.getUser().getUid());
		Assert.assertEquals(REFRESH_TOKEN_ID_2, newAccessToken.getRefreshToken().getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT_2, newAccessToken.getRefreshToken().getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, newAccessToken.getRefreshToken().getAuthentication());
	}

	@Test
	public void saveAccessTokenTest()
	{
		final OAuthAccessTokenModel accessToken = oauthTokenService.saveAccessToken(ACCESS_TOKEN_ID, ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2, AUTHENTICATION_OBJECT_2,
				USER_NAME_2, CLIENT_ID_2, null);

		Assert.assertEquals(ACCESS_TOKEN_ID, accessToken.getTokenId());
		Assert.assertEquals(ACCESS_TOKEN_OBJECT, accessToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, accessToken.getAuthentication());
		Assert.assertEquals(AUTHENTICATION_ID, accessToken.getAuthenticationId());
		Assert.assertEquals(CLIENT_ID, accessToken.getClient().getClientId());
		Assert.assertEquals(USER_NAME, accessToken.getUser().getUid());
		Assert.assertNotNull(accessToken.getRefreshToken());
	}

	@Test
	public void shouldSkipSaveForAccessTokenWhenAuthenticationObjectHasNotChanged()
	{
		optimizeSaveForAccessToken.switchToValue("true");
		final OAuthAccessTokenModel accessToken = oauthTokenService.saveAccessToken(ACCESS_TOKEN_ID, ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2, createSerializedAuthentication("clientId", new HashSet<>(Arrays.asList("scope1","scope2"))),
				USER_NAME_2, CLIENT_ID_2, null);

		final Date modifiedTime = accessToken.getModifiedtime();
		final PK accessTokenPK = accessToken.getPk();

		final OAuthAccessTokenModel accessTokenSavedSecondTime = oauthTokenService.saveAccessToken(ACCESS_TOKEN_ID, ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2, createSerializedAuthentication("clientId",new HashSet<>(Arrays.asList("scope2","scope1"))),
				USER_NAME_2, CLIENT_ID_2, null);


		final Date secondModifiedTime = accessTokenSavedSecondTime.getModifiedtime();
		final PK accessTokenSavedSecondTimePK = accessTokenSavedSecondTime.getPk();

		Assert.assertEquals(accessTokenPK, accessTokenSavedSecondTimePK);
		Assert.assertEquals(modifiedTime, secondModifiedTime);

	}

	@Test
	public void shouldSaveAccessTokenWhenAuthenticationObjectChanged()
	{

		final OAuthAccessTokenModel accessToken = oauthTokenService.saveAccessToken(ACCESS_TOKEN_ID, ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2, createSerializedAuthentication("clientId",Collections.emptySet()),
				USER_NAME_2, CLIENT_ID_2, null);

		final Date accessTokenModifiedTime = accessToken.getModifiedtime();
		final PK accessTokenPK = accessToken.getPk();

		final OAuthAccessTokenModel accessTokenSavedSecondTime = oauthTokenService.saveAccessToken(ACCESS_TOKEN_ID, ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2, createSerializedAuthentication("clientId2",Collections.emptySet()),
				USER_NAME_2, CLIENT_ID_2, null);

		final Date accessTokenSavedSecondTimeModifiedTime = accessTokenSavedSecondTime.getModifiedtime();
		final PK accessTokenSavedSecondTimePK = accessToken.getPk();

		Assert.assertEquals(accessTokenPK, accessTokenSavedSecondTimePK);
		Assert.assertNotEquals(accessTokenModifiedTime, accessTokenSavedSecondTimeModifiedTime);
	}

	private byte[] createSerializedAuthentication(final String clientId, final Set<String> scopes)
	{
		final OAuth2Request oAuth2Request = new OAuth2Request(null, clientId, null, true, scopes, null, null, null, null);
		final OAuth2Authentication auth2Authentication = new OAuth2Authentication(oAuth2Request, null);
		return SerializationUtils.serialize(auth2Authentication);
	}

	@Test
	public void saveAccessTokenWithoutUserTest()
	{
		final OAuthAccessTokenModel accessToken = oauthTokenService.saveAccessToken("99", ACCESS_TOKEN_OBJECT_2,
				AUTHENTICATION_ID_2, AUTHENTICATION_OBJECT_2,
				null, CLIENT_ID_2, null);

		Assert.assertEquals("99", accessToken.getTokenId());
		Assert.assertEquals(ACCESS_TOKEN_OBJECT_2, accessToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, accessToken.getAuthentication());
		Assert.assertEquals(AUTHENTICATION_ID_2, accessToken.getAuthenticationId());
		Assert.assertEquals(CLIENT_ID_2, accessToken.getClient().getClientId());
		Assert.assertNull(accessToken.getUser());
		Assert.assertNull(accessToken.getRefreshToken());
	}

	@Test(expected = IllegalArgumentException.class)
	public void saveAccessTokenWithNullParamTest()
	{
		oauthTokenService.saveAccessToken(null, null, AUTHENTICATION_ID, null, USER_NAME, CLIENT_ID, null);
	}

	@Test
	public void saveRefreshTokenModelTest()
	{
		OAuthRefreshTokenModel refreshToken = new OAuthRefreshTokenModel();
		refreshToken.setTokenId(REFRESH_TOKEN_ID_2);
		refreshToken.setToken(REFRESH_TOKEN_OBJECT_2);
		refreshToken.setAuthentication(AUTHENTICATION_OBJECT_2);

		oauthTokenService.saveRefreshToken(refreshToken);

		refreshToken = oauthTokenService.getRefreshToken(REFRESH_TOKEN_ID_2);
		Assert.assertEquals(REFRESH_TOKEN_ID_2, refreshToken.getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT_2, refreshToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, refreshToken.getAuthentication());
	}

	@Test(expected = ModelSavingException.class)
	public void saveDuplicateRefreshTokenModelTest()
	{
		final OAuthRefreshTokenModel refreshToken = new OAuthRefreshTokenModel();
		refreshToken.setTokenId(REFRESH_TOKEN_ID);
		refreshToken.setToken(REFRESH_TOKEN_OBJECT_2);
		refreshToken.setAuthentication(AUTHENTICATION_OBJECT_2);
		oauthTokenService.saveRefreshToken(refreshToken);
	}

	@Test(expected = IllegalArgumentException.class)
	public void saveRefreshTokenModelWithNullParamTest()
	{
		oauthTokenService.saveRefreshToken(null);
	}

	@Test
	public void createRefreshTokenTest()
	{
		final OAuthRefreshTokenModel newRefreshToken = oauthTokenService.saveRefreshToken(REFRESH_TOKEN_ID, REFRESH_TOKEN_OBJECT,
				AUTHENTICATION_OBJECT);

		Assert.assertEquals(REFRESH_TOKEN_ID, newRefreshToken.getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT, newRefreshToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT, newRefreshToken.getAuthentication());
	}

	@Test
	public void saveRefreshTokenTest()
	{
		final OAuthRefreshTokenModel refreshToken = oauthTokenService.saveRefreshToken(REFRESH_TOKEN_ID, REFRESH_TOKEN_OBJECT_2,
				AUTHENTICATION_OBJECT_2);

		Assert.assertEquals(REFRESH_TOKEN_ID, refreshToken.getTokenId());
		Assert.assertEquals(REFRESH_TOKEN_OBJECT_2, refreshToken.getToken());
		Assert.assertEquals(AUTHENTICATION_OBJECT_2, refreshToken.getAuthentication());
	}

	@Test(expected = IllegalArgumentException.class)
	public void saveRefreshTokenWithNullParamTest()
	{
		oauthTokenService.saveRefreshToken(null, REFRESH_TOKEN_OBJECT, AUTHENTICATION_OBJECT);
	}

	@Test
	public void removeAccessTokenTest()
	{
		oauthTokenService.removeAccessToken(ACCESS_TOKEN_ID);

		try
		{
			oauthTokenService.getAccessToken(ACCESS_TOKEN_ID);
		}
		catch (final UnknownIdentifierException e)
		{
			return;
		}
		fail();
	}

	@Test(expected = UnknownIdentifierException.class)
	public void removeAccessTokenWithIncorrectIdTest()
	{
		oauthTokenService.removeAccessToken(NOT_EXISTING_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeAccessTokenWithNullParamTest()
	{
		oauthTokenService.removeAccessToken(null);
	}

	@Test
	public void removeRefreshTokenWithLockTest()
	{
		refreshWithLock.switchToValue("true");
		removeRefreshToken();
	}
	@Test
	public void removeRefreshTokenWithoutLockTest()
	{
		refreshWithLock.switchToValue("false");
		removeRefreshToken();
	}

	private void removeRefreshToken()
	{
		oauthTokenService.removeRefreshToken(REFRESH_TOKEN_ID);
		try
		{
			oauthTokenService.getRefreshToken(REFRESH_TOKEN_ID);
		}
		catch (final UnknownIdentifierException e)
		{
			return;
		}
		fail();
	}

	@Test(expected = UnknownIdentifierException.class)
	public void removeRefreshTokenWithIncorrectIdTest()
	{
		oauthTokenService.removeRefreshToken(NOT_EXISTING_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeRefreshTokenWithNullParamTest()
	{
		oauthTokenService.removeRefreshToken(null);
	}

	@Test
	public void removeAccessTokenUsingRefreshTokenTest()
	{
		oauthTokenService.removeAccessTokenUsingRefreshToken(REFRESH_TOKEN_ID);

		try
		{
			oauthTokenService.getAccessToken(ACCESS_TOKEN_ID);
		}
		catch (final UnknownIdentifierException e)
		{
			return;
		}
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeAccessTokenUsingRefreshTokenWithNullParamTest()
	{
		oauthTokenService.removeAccessTokenUsingRefreshToken(null);
	}

	@Test
	public void removeAccessTokenForAuthenticationTest()
	{
		oauthTokenService.removeAccessTokenForAuthentication(AUTHENTICATION_ID);

		try
		{
			oauthTokenService.getAccessTokenForAuthentication(AUTHENTICATION_ID);
		}
		catch (final UnknownIdentifierException e)
		{
			return;
		}
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeAccessTokenForAuthenticationWithNullParamTest()
	{
		oauthTokenService.removeAccessTokenForAuthentication(null);
	}
}
