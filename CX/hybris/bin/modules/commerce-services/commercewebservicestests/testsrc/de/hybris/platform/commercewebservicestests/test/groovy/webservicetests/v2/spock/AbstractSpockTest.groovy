/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicestests.test.groovy.webservicetests.v2.spock

import de.hybris.platform.core.Registry
import de.hybris.platform.servicelayer.config.ConfigurationService
import de.hybris.platform.testframework.JUnitPlatformSpecification
import de.hybris.platform.util.Config
import de.hybris.platform.commercewebservicestests.setup.TestSetupUtils
import de.hybris.platform.commercewebservicestests.test.groovy.webservicetests.SSLIssuesIgnoringHttpClientFactory
import de.hybris.platform.commercewebservicestests.test.groovy.webservicetests.config.TestConfigFactory
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import groovyx.net.http.ResponseParseException
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import java.io.StringReader
import java.io.StringWriter
import java.net.URISyntaxException
import org.apache.commons.logging.LogFactory
import org.apache.http.client.HttpClient
import org.apache.log4j.PropertyConfigurator
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import static org.apache.http.HttpStatus.SC_OK
import static org.junit.Assume.assumeTrue

@Ignore
abstract class AbstractSpockTest extends JUnitPlatformSpecification {
	private static String LOG4J_PROPERTIES_CLASS_PATH = "commercewebservicestests/log4j.properties";
	private static String COMMONS_LOGGING_LOGGER_ATTRIBUTE_NAME = "org.apache.commons.logging.Log";
	private static String COMMONS_LOGGING_LOGGER_ATTRIBUTE_VALUE = "org.apache.commons.logging.impl.Log4JLogger";

	protected RESTClient restClient
	protected static ConfigObject config = TestConfigFactory.createConfig("v2", "/commercewebservicestests/groovytests-property-file.groovy");
	private static final ThreadLocal<Boolean> SERVER_NEEDS_SHUTDOWN = new ThreadLocal<Boolean>();

	private final ConfigurationService configurationService = Registry.getApplicationContext()
			.getBean("configurationService", ConfigurationService.class)

	protected String getConfigurationProperty(final String propertyName) {
		return configurationService.getConfiguration().getString(propertyName)
	}

	@Test
	public void testing() {
		//dummy test class necessary for the test class to be considered
	}

	@BeforeClass
	public static void startServerIfNeeded() {
		if (!TestSetupUtils.isServerStarted()) {
			SERVER_NEEDS_SHUTDOWN.set(true);
			TestSetupUtils.startServer();
		}
	}

	@AfterClass
	public static void stopServerIfNeeded() {
		if (SERVER_NEEDS_SHUTDOWN.get()) {
			TestSetupUtils.stopServer();
			SERVER_NEEDS_SHUTDOWN.set(false);
		}
	}

	/**
	 * Method is used to configure commons-logging and log4j.
	 * Commons-logging is used by RestClient, thus it should be called before RestClient or any other logger is created.
	 * It overrides default configuration in platform/ext/core/resources/commons-logging.properties,
	 * where CommonsHybrisLog4jWrapper is declared as Logger
	 */
	def synchronized static initializeLogging() {
		// To fix bug: https://jira.tools.sap/browse/CXEC-26479, remove the logger initializer in static block
		// Other test case failures can be related to some race condition in one of the many static block initializers
	}

	def setup() {
		restClient = createRestClient()
	}

	def cleanup() {
		restClient.shutdown()
	}

	protected static final String getDefaultHttpUri() {
		return config.DEFAULT_HTTP_URI
	}

	protected static final String getDefaultHttpsUri() {
		return config.DEFAULT_HTTPS_URI
	}

	protected static final String getBasePath() {
		return config.BASE_PATH
	}

	protected static final String getBasePathWithSite() {
		return config.BASE_PATH_WITH_SITE
	}

	protected static final String getBasePathWithIntegrationSite() {
		def enableWsIntegrationTest = Config.getBoolean("commercewebservicestests.enableWsIntegrationTest", false);
		return enableWsIntegrationTest ? config.BASE_PATH_WITH_INTEGRATION_SITE : config.BASE_PATH_WITH_SITE
	}

	protected static final String getBasePathWithB2BSite() {
		return config.BASE_PATH_WITH_B2B_SITE
	}
	protected static final String getOAuth2TokenUri() {
		return config.OAUTH2_TOKEN_URI
	}

	protected static final String getOAuth2TokenPath() {
		return config.OAUTH2_TOKEN_ENDPOINT_PATH
	}

	protected static final String getClientId() {
		return config.CLIENT_ID
	}

	protected static final String getClientSecret() {
		return config.CLIENT_SECRET
	}

	protected static final String getClientRedirectUri() {
		return config.OAUTH2_CALLBACK_URI
	}

	protected static final String getTrustedClientId() {
		return config.TRUSTED_CLIENT_ID
	}

	protected static final String getTrustedClientSecret() {
		return config.TRUSTED_CLIENT_SECRET
	}

	protected boolean isUUID(String name) {
		try {
			UUID.fromString(name);
			true
		}
		catch (IllegalArgumentException ignored) {
			false
		}
	}

	protected String getUserId(boolean useCustomerId, Map customer) {
		assumeTrue(!useCustomerId || isUUID(customer.customerId.toString()))
		useCustomerId ? customer.customerId : customer.id
	}

	protected RESTClient createRestClient(uri = config.DEFAULT_HTTPS_URI) {
		def restClient = new CustomRESTClient(uri);

		// makes sure we can access the services even without a valid SSL certificate
		HttpClient httpClient = SSLIssuesIgnoringHttpClientFactory.createHttpClient();
		restClient.setClient(httpClient);

		// makes sure an exception is not thrown and that the response is parsed
		restClient.handler.failure = restClient.handler.success

		// used to record the requests in jmeter
		//client.setProxy('localhost', 8080, null)

		return restClient;
	}

	protected RESTClient createRestClientWithoutParsing(uri = config.DEFAULT_HTTPS_URI) {
		def restClient = createRestClient(uri);

		// replace the response handler to prenvent all parsing and therefore the use of xerces for parsing html
		restClient.handler.success = { resp -> return resp }
		restClient.handler.failure = { resp -> return resp }

		return restClient;
	}

	protected void addAuthorization(RESTClient client, token) {
		client.getHeaders().put('Authorization', ' Bearer ' + token.access_token)
	}

	protected void removeAuthorization(RESTClient client) {
		client.getHeaders().remove('Authorization')
	}

	protected getOAuth2TokenUsingClientCredentials(RESTClient client, clientId, clientSecret) {
		HttpResponseDecorator response = client.post(
				uri: getOAuth2TokenUri(),
				path: getOAuth2TokenPath(),
				body: [
						'grant_type'   : 'client_credentials',
						'client_id'    : clientId,
						'client_secret': clientSecret
				],
				contentType: JSON,
				requestContentType: URLENC)

		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.error)) println(data)
			assert status == SC_OK
			assert data.token_type == 'bearer'
			assert data.access_token
			assert data.expires_in
		}

		return response.data
	}

	protected getOAuth2TokenUsingPassword(RESTClient client, clientId, clientSecret, username, password, boolean doAssert = true) {
		HttpResponseDecorator response = client.post(
				uri: getOAuth2TokenUri(),
				path: getOAuth2TokenPath(),
				body: [
						'grant_type'   : 'password',
						'client_id'    : clientId,
						'client_secret': clientSecret,
						'username'     : username,
						'password'     : password
				],
				contentType: JSON,
				requestContentType: URLENC)

		if (doAssert) {
			with(response) {
				if (isNotEmpty(data) && isNotEmpty(data.error)) println(data)
				assert status == SC_OK
				assert data.token_type == 'bearer'
				assert data.access_token
				assert data.expires_in
				assert data.refresh_token
			}
		}

		return response.data
	}

	protected refreshOAuth2Token(RESTClient client, refreshToken, clientId, clientSecret, redirectUri) {
		def bodyParams = [
				'grant_type'   : 'refresh_token',
				'refresh_token': refreshToken
		]

		if (clientId) {
			bodyParams['client_id'] = clientId
		}

		if (clientSecret) {
			bodyParams['client_secret'] = clientSecret
		}

		if (redirectUri) {
			bodyParams['redirect_uri'] = URLEncoder.encode(redirectUri, 'UTF-8')
		}

		HttpResponseDecorator response = client.post(
				uri: getOAuth2TokenUri(),
				path: getOAuth2TokenPath(),
				body: bodyParams,
				contentType: JSON,
				requestContentType: URLENC)

		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			assert status == SC_OK
			assert data.token_type == 'bearer'
			assert data.access_token
			assert data.expires_in
			assert data.refresh_token
		}

		return response.data
	}

	protected void authorizeClient(RESTClient client) {
		def token = getOAuth2TokenUsingClientCredentials(client, getClientId(), getClientSecret())
		addAuthorization(client, token)
	}

	protected void authorizeTrustedClient(RESTClient client) {
		def token = getOAuth2TokenUsingClientCredentials(client, getTrustedClientId(), getTrustedClientSecret())
		addAuthorization(client, token)
	}

	/**
	 * Checks if a node exists and is not empty. Works for JSON and XML formats.
	 *
	 * @param the node to check
	 * @return {@code true} if the node is not empty, {@code false} otherwise
	 */
	protected isNotEmpty(node) {
		(node != null) && (node.size() > 0)
	}

	/**
	 * Checks if a node doesn't exist or is empty. Works for JSON and XML formats.
	 *
	 * @param the node to check
	 * @return {@code true} if the node is not empty, {@code false} otherwise
	 */
	protected isEmpty(node) {
		(node == null) || (node.size() == 0)
	}


	/**
	 * Same as {@link spock.lang.Specification#with(Object, groovy.lang.Closure)}, the only difference is that it returns the target object.
	 *
	 * @param target an implicit target for conditions and/or interactions
	 * @param closure a code block containing top-level conditions and/or interactions
	 * @return the target object
	 */
	def returningWith(target, closure) {
		with(target, closure)
		return target
	}

	protected static class CustomRESTClient extends RESTClient {
		public CustomRESTClient(Object defaultURI) throws URISyntaxException {
			super(defaultURI)
		}

		@Override
		protected HttpResponseDecorator defaultSuccessHandler(HttpResponseDecorator resp, Object data) throws ResponseParseException {
			try {
				switch (data) {
					case InputStream:
						def buffer = new ByteArrayOutputStream()
						buffer << (InputStream) data
						resp.data = new ByteArrayInputStream(buffer.toByteArray())
						break
					case Reader:
						def buffer = new StringWriter()
						buffer << (Reader) data
						resp.data = new StringReader(buffer.toString())
						break
					default:
						resp.data = data
						break
				}
				return resp
			}
			catch (IOException ex) {
				throw new ResponseParseException(resp, ex)
			}
		}
	}
}
