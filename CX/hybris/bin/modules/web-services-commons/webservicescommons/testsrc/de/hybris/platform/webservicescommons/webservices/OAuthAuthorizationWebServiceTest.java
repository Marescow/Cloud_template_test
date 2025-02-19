/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.webservicescommons.webservices;

import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.impex.impl.ClasspathImpExResource;
import de.hybris.platform.webservicescommons.testsupport.client.WebservicesAssert;
import de.hybris.platform.webservicescommons.testsupport.client.WsRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.util.Optional;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.oauth2.common.OAuth2AccessToken;


@NeedsEmbeddedServer(webExtensions = "oauth2")
@IntegrationTest
public class OAuthAuthorizationWebServiceTest extends ServicelayerTest
{
	private static final String URI = "oauth/token";

	private static final String GRANT_TYPE_CLIENT_CRIDENTIALS = "client_credentials";
	private static final String GRANT_TYPE_CLIENT_REFRESH_TOKEN = "refresh_token";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String CLIENT_ID = "mobile_android";
	private static final String CLIENT_SECRET = "secret";
	private static final String CUSTOMER_USERNAME = "testoauthcustomer";
	private static final String CUSTOMER_PASSWORD = "1234";
	private static final String REFRESH_TOKEN = "refreshToken";

	private WsRequestBuilder wsRequestBuilder;

	@Before
	public void setUp() throws Exception
	{
		wsRequestBuilder = new WsRequestBuilder().extensionName("oauth2").path(URI);

		createCoreData();
		createDefaultUsers();
		importData(new ClasspathImpExResource("/webservicescommons/test/democustomer-data.impex", "UTF-8"));
	}

	@Test
	public void testGetTokenUsingClientCredentials()
	{
		//given
		Form formData = new Form();
		formData.param("client_id", CLIENT_ID);
		formData.param("client_secret", CLIENT_SECRET);
		formData.param("grant_type", GRANT_TYPE_CLIENT_CRIDENTIALS);
		final Response result = wsRequestBuilder.build()
				//when
				.post(Entity.form(formData));
		result.bufferEntity();

		//then
		WebservicesAssert.assertResponse(Status.OK, Optional.empty(), result);
	}

	@Test
	public void testGetTokenUsingPassword()
	{
		//given
		Form formData = new Form();
		formData.param("username", CUSTOMER_USERNAME);
		formData.param("password", CUSTOMER_PASSWORD);
		formData.param("client_id", CLIENT_ID);
		formData.param("client_secret", CLIENT_SECRET);
		formData.param("grant_type", GRANT_TYPE_PASSWORD);
		final Response result = wsRequestBuilder.build()
				//when
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));
		result.bufferEntity();

		//then
		WebservicesAssert.assertResponse(Status.OK, Optional.empty(), result);
	}

	@Test
	public void testGetTokenUsingWrongClientID()
	{
		//given
		Form formData = new Form();
		formData.param("client_id", "WRONG");
		formData.param("client_secret", CLIENT_SECRET);
		formData.param("grant_type", GRANT_TYPE_CLIENT_CRIDENTIALS);
		final Response result = wsRequestBuilder.build()
				//when
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));
		result.bufferEntity();

		//then
		WebservicesAssert.assertResponse(Status.UNAUTHORIZED, Optional.empty(), result);
		assertTrue(result.hasEntity());
		assertTrue(StringUtils.contains(result.readEntity(String.class), "invalid_client"));
	}

	@Test
	public void testGetTokenUsingInvalidRefreshToken()
	{
		//given
		final Form formData = new Form();
		formData.param("client_id", CLIENT_ID);
		formData.param("client_secret", CLIENT_SECRET);
		formData.param("grant_type", GRANT_TYPE_CLIENT_REFRESH_TOKEN);
		formData.param("refresh_token", REFRESH_TOKEN);

		final Response result = wsRequestBuilder.build()
				//when
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));
		result.bufferEntity();

		//then
		WebservicesAssert.assertResponse(Status.BAD_REQUEST, Optional.empty(), result);
		assertTrue(result.hasEntity());
		assertTrue(StringUtils.contains(result.readEntity(String.class), "invalid_grant"));
	}

	@Test
	public void testGetTokenUsingValidRefreshToken()
	{
		//given
		final Form formData = new Form();
		formData.param("username", CUSTOMER_USERNAME);
		formData.param("password", CUSTOMER_PASSWORD);
		formData.param("client_id", CLIENT_ID);
		formData.param("client_secret", CLIENT_SECRET);
		formData.param("grant_type", GRANT_TYPE_PASSWORD);
		final OAuth2AccessToken result = wsRequestBuilder.build()
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData)).readEntity(OAuth2AccessToken.class);

		formData.param("grant_type", GRANT_TYPE_CLIENT_REFRESH_TOKEN);
		formData.param("refresh_token", result.getRefreshToken().getValue());

		final Response refreshTokenResult = wsRequestBuilder.build()
				//when
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));
		refreshTokenResult.bufferEntity();

		//then
		WebservicesAssert.assertResponse(Status.OK, Optional.empty(), refreshTokenResult);
	}

	@Test
	public void testGetTokenUsingWrongClientSecret()
	{
		//given
		Form formData = new Form();
		formData.param("client_id", CLIENT_ID);
		formData.param("client_secret", "WRONG");
		formData.param("grant_type", GRANT_TYPE_CLIENT_CRIDENTIALS);

		final Response result = wsRequestBuilder.build()
				//when
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));
		result.bufferEntity();
		//then
		WebservicesAssert.assertResponse(Status.UNAUTHORIZED, Optional.empty(), result);
		assertTrue(result.hasEntity());
		assertTrue(StringUtils.contains(result.readEntity(String.class), "invalid_client"));
	}

	@Test
	public void testGetTokenUsingWrongGrantType()
	{
		//given
		Form formData = new Form();
		formData.param("client_id", CLIENT_ID);
		formData.param("client_secret", CLIENT_SECRET);
		formData.param("grant_type", "WRONG");
		final Response result = wsRequestBuilder.build()
				//when
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));
		result.bufferEntity();
		//then
		WebservicesAssert.assertResponse(Status.BAD_REQUEST, Optional.empty(), result);
		assertTrue(result.hasEntity());
		assertTrue(StringUtils.contains(result.readEntity(String.class), "unsupported_grant_type"));
	}
}
