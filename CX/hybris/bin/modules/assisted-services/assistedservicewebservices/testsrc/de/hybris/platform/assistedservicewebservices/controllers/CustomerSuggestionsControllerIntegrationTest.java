/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedservicewebservices.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.assistedservicewebservices.constants.AssistedservicewebservicesConstants;

import de.hybris.platform.assistedservicewebservices.dto.CustomerSuggestionsResponseWsDTO;
import de.hybris.platform.oauth2.constants.OAuth2Constants;

import de.hybris.platform.webservicescommons.testsupport.client.WebservicesAssert;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@NeedsEmbeddedServer(webExtensions = { AssistedservicewebservicesConstants.EXTENSIONNAME, OAuth2Constants.EXTENSIONNAME })
@IntegrationTest
public class CustomerSuggestionsControllerIntegrationTest extends AbstractControllerIntegrationIntegrationTest
{
	public static final String IN_VALID_CUSTOMER_ID = "test";
	public static final String VALID_CUSTOMER_ID = "user1";

	protected Response performPostCustomerAutoComplete(final String customerQuery, final String baseSite)
	{
		Map<String, Object> requestBody = new HashMap<>();

		if (StringUtils.isNotBlank(customerQuery))
		{
			requestBody.put("customerQuery", customerQuery);
		}

		return getWsSecuredRequestBuilder(OAUTH_USERNAME, OAUTH_PASSWORD).path("/customers/suggestions/search")
				.queryParam("baseSite", baseSite).build().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
	}

	@Test
	public void shouldGetAutoCompleteSuggestionsWithValidQuery()
	{
		// When
		final Response response = performPostCustomerAutoComplete("user", BASE_SITE_ID);

		// Then
		WebservicesAssert.assertResponse(Status.OK, response);
		final CustomerSuggestionsResponseWsDTO suggestionsResponse = response.readEntity(CustomerSuggestionsResponseWsDTO.class);

		assertThat(suggestionsResponse.getSuggestions()).isNotEmpty();
	}

	@Test
	public void shouldReturnBadRequestForShortQuery()
	{
		// When
		final Response response = performPostCustomerAutoComplete("t", BASE_SITE_ID);

		// Then
		WebservicesAssert.assertResponse(Status.BAD_REQUEST, response);
		final String errorMessage = response.readEntity(String.class);

		assertThat(errorMessage).contains("Customer query length must not be less than 3");
	}

	@Test
	public void shouldReturnBadRequestForMissingQuery() throws JsonProcessingException
	{
		// Given
		final String emptyCustomerQuery = "";

		// When
		final Response response = performPostCustomerAutoComplete(emptyCustomerQuery, BASE_SITE_ID);
		WebservicesAssert.assertResponse(Status.BAD_REQUEST, response);
		final String errorMessage = response.readEntity(String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(errorMessage);

		assertThat(jsonNode.has("errors")).isTrue();
		JsonNode errorNode = jsonNode.get("errors").get(0);

		assertThat(errorNode.get("reason").asText()).isEqualTo("missing");
		assertThat(errorNode.get("subject").asText()).isEqualTo("customerQuery");
	}
}
