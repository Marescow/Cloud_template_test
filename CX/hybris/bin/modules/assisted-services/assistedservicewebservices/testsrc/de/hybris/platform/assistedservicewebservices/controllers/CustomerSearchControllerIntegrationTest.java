/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedservicewebservices.controllers;

import static de.hybris.platform.webservicescommons.testsupport.client.WebservicesAssert.assertResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.assistedserviceservices.constants.AssistedserviceservicesConstants;
import de.hybris.platform.assistedservicewebservices.constants.AssistedservicewebservicesConstants;

import de.hybris.platform.assistedservicewebservices.dto.CustomerSearchPageWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.PrincipalWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.UserWsDTO;
import de.hybris.platform.oauth2.constants.OAuth2Constants;

import de.hybris.platform.webservicescommons.testsupport.client.WebservicesAssert;
import de.hybris.platform.webservicescommons.testsupport.client.WsSecuredRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;


@NeedsEmbeddedServer(webExtensions = { AssistedservicewebservicesConstants.EXTENSIONNAME, OAuth2Constants.EXTENSIONNAME })
@IntegrationTest
public class CustomerSearchControllerIntegrationTest extends AbstractControllerIntegrationIntegrationTest
{
	public static final String CUSTOMER_SEARCH_URI = "/customers/search";
	public static final String DUMMY_USER_UID = "dummyuser";
	public static final String DUMMY_USER_UID_WITHOUT_PREFIX = "ummyuser";
	public static final String DUMMY_USER_UID_WITHOUT_SUFFIX = "dummyuse";
	public static final String TEMP_CUSTOMER_LIST_ID = "bopisCustomers";
	public static final String[] DUMMY_USERS = { DUMMY_USER_UID + 1, DUMMY_USER_UID + 2, DUMMY_USER_UID + 3 };
	public static final String NOT_AGENT = "notasagent";
	public static final String VALID_CUSTOMER_ID = "user1";
	public static final String PAGE = "page";

	@Test
	public void getCustomersByUid()
	{
		//given
		//when
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), DUMMY_USER_UID, null,
				null);

		//then
		WebservicesAssert.assertResponse(Status.OK, response);
		final List<String> results = getCustomerNamesFromResponse(response);
		Assert.assertEquals(DUMMY_USERS.length, results.size());
	}

	@Test
	public void getFirstTwoCustomersInDescendingOrderSortedByName()
	{
		//given
		final int pageSize = 2;
		//when
		final Response response = performPostCustomersSearch(
				ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID, PAGE_SIZE, String.valueOf(pageSize), SORT,
						AssistedserviceservicesConstants.SORT_BY_NAME_DESC), DUMMY_USER_UID, null, null);

		//then
		WebservicesAssert.assertResponse(Status.OK, response);
		final List<String> results = getCustomerNamesFromResponse(response);
		Assert.assertEquals(pageSize, results.size());
		Assert.assertThat(results, contains(DUMMY_USERS[2], DUMMY_USERS[1]));
	}

	@Test
	public void getCustomerFromThirdPageSortedByUidInAscendingOrder()
	{
		//given
		final int pageSize = 1;
		final int page = 2;
		//when
		final Response response = performPostCustomersSearch(
				ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID, PAGE_SIZE, String.valueOf(pageSize), PAGE, String.valueOf(page), SORT,
						AssistedserviceservicesConstants.SORT_BY_UID_ASC), DUMMY_USER_UID, null, null);

		//then
		WebservicesAssert.assertResponse(Status.OK, response);
		final List<String> results = getCustomerNamesFromResponse(response);
		Assert.assertEquals(pageSize, results.size());
		Assert.assertThat(results, contains(DUMMY_USERS[page]));
	}

	@Test
	public void getCustomersByNotExistingUid()
	{
		//when
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID),
				"notExistingCustomer@example.com", null, null);

		//then
		WebservicesAssert.assertResponse(Status.OK, response);
		final CustomerSearchPageWsDTO results = response.readEntity(CustomerSearchPageWsDTO.class);
		assertTrue(CollectionUtils.isEmpty(results.getEntries()));
	}

	@Test
	public void shouldGetAllCustomerByEmptyCriteria()
	{
		//given
		//when
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), null, null, null);

		//then
		WebservicesAssert.assertResponse(Status.OK, response);
		final CustomerSearchPageWsDTO results = response.readEntity(CustomerSearchPageWsDTO.class);
		assertTrue(CollectionUtils.isNotEmpty(results.getEntries()));
	}

	@Test
	public void shouldGetCustomerWithOrder()
	{
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), null, "order1", null);


		assertResponse(Response.Status.OK, response);
		final CustomerSearchPageWsDTO entity = response.readEntity(CustomerSearchPageWsDTO.class);
		final List<String> uids = entity.getEntries().stream().map(UserWsDTO::getUid).collect(Collectors.toList());
		assertThat(uids).contains(VALID_CUSTOMER_ID);
	}

	@Test
	public void shouldGetCustomersWithCustomerUidMissedPrefix()
	{
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID),
				DUMMY_USER_UID_WITHOUT_PREFIX, null, null);

		assertResponse(Response.Status.OK, response);
		final List<String> results = getCustomerNamesFromResponse(response);
		Assert.assertEquals(DUMMY_USERS.length, results.size());
	}

	@Test
	public void shouldGetCustomersWithCustomerUidMissedSuffix()
	{
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID),
				DUMMY_USER_UID_WITHOUT_SUFFIX, null, null);
		assertResponse(Response.Status.OK, response);
		final List<String> results = getCustomerNamesFromResponse(response);
		Assert.assertEquals(DUMMY_USERS.length, results.size());
	}

	@Test
	public void shouldGetB2BCustomerEnrichedData()
	{
		try
		{
			Class.forName("de.hybris.platform.b2bwebservicescommons.dto.company.B2BUnitWsDTO");
		}
		catch (ClassNotFoundException e)
		{
			return;
		}

		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), null, "order1", null);

		assertResponse(Status.OK, response);
		final CustomerSearchPageWsDTO entity = response.readEntity(CustomerSearchPageWsDTO.class);
		assertThat(entity.getEntries().get(0)).isNotNull().hasFieldOrProperty("orgUnit");
	}

	@Test
	public void shouldNotGetCustomerWithWrongOrder()
	{
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), null, "wrong_order",
				null);

		assertResponse(Status.BAD_REQUEST, response);
	}

	@Test
	public void shouldNotGetCustomersByUnknownCustomerListId()
	{
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), null, null, "someID");

		assertResponse(Status.BAD_REQUEST, response);
	}

	@Test
	public void shouldGetCustomersByCustomerListId()
	{
		// default call
		final Response response = performPostCustomersSearch(ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID), null, null,
				TEMP_CUSTOMER_LIST_ID);
		assertResponse(Status.OK, response);
		final CustomerSearchPageWsDTO entity = response.readEntity(CustomerSearchPageWsDTO.class);
		final List<String> uids = entity.getEntries().stream().map(UserWsDTO::getUid).collect(Collectors.toList());
		assertThat(uids).contains(VALID_CUSTOMER_ID);
	}

	@Test
	public void shouldGetCustomersByCustomerListIdWithPaging()
	{
		final Map<String, String> params = ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID, PAGE_SIZE, "1", PAGE, "0", SORT,
				"byOrderDateDesc");
		final Response response = performPostCustomersSearch(params, null, null, TEMP_CUSTOMER_LIST_ID);
		assertResponse(Status.OK, response);
		final CustomerSearchPageWsDTO entity = response.readEntity(CustomerSearchPageWsDTO.class);
		final List<String> uids = entity.getEntries().stream().map(UserWsDTO::getUid).collect(Collectors.toList());
		assertThat(uids).contains("user2");
		assertThat(entity.getPagination().getPageSize()).isSameAs(NumberUtils.INTEGER_ONE);
		assertThat(entity.getPagination().getSort()).isEqualToIgnoringCase("byOrderDateDesc");

	}

	@Test
	public void shouldGetCustomersByCustomerListIdWithPagingAndSort()
	{
		final Map<String, String> params = ImmutableMap.of(BASE_SITE_PARAM, BASE_SITE_ID, PAGE_SIZE, "1", PAGE, "0", SORT,
				"byOrderDateAsc");
		final Response response = performPostCustomersSearch(params, null, null, TEMP_CUSTOMER_LIST_ID);

		assertResponse(Status.OK, response);
		final CustomerSearchPageWsDTO entity = response.readEntity(CustomerSearchPageWsDTO.class);
		final List<String> uids = entity.getEntries().stream().map(UserWsDTO::getUid).collect(Collectors.toList());
		assertThat(uids).contains(VALID_CUSTOMER_ID);
		assertThat(entity.getPagination().getPageSize()).isSameAs(NumberUtils.INTEGER_ONE);
		assertThat(entity.getPagination().getSort()).isEqualToIgnoringCase("byOrderDateAsc");

	}

	@Test
	public void getCustomersByNotAsAgent()
	{
		// Given
		final String query = "test";

		// When
		final Response result = getWsSecuredRequestBuilder(NOT_AGENT, OAUTH_PASSWORD).path(CUSTOMER_SEARCH_URI)
				.queryParam(BASE_SITE_PARAM, BASE_SITE_ID).build().accept(MediaType.APPLICATION_JSON)
				.post(Entity.json("{\"query\": \"" + query + "\"}"));

		// Then
		assertResponse(Status.FORBIDDEN, result);
	}

	protected Response performPostCustomersSearch(final Map<String, String> params, final String query, final String orderId,
			final String customerListId)
	{
		// Prepare the request body for the POST request
		Map<String, Object> postBody = new HashMap<>();
		if (StringUtils.isNotBlank(query))
		{
			postBody.put("query", query);
		}
		if (StringUtils.isNotBlank(orderId))
		{
			postBody.put("orderId", orderId);
		}
		if (StringUtils.isNotBlank(customerListId))
		{
			postBody.put("customerListId", customerListId);
		}

		// Build the secured request with query parameters
		WsSecuredRequestBuilder builder = getWsSecuredRequestBuilder(OAUTH_USERNAME, OAUTH_PASSWORD).path(CUSTOMER_SEARCH_URI);

		// Append URL parameters (e.g., baseSite, pageSize, currentPage, sort)
		final Set<Map.Entry<String, String>> entries = params.entrySet();
		for (final Map.Entry<String, String> entry : entries)
		{
			if (StringUtils.isNotBlank(entry.getValue()))
			{
				builder = builder.queryParam(entry.getKey(), entry.getValue());
			}
		}

		// Perform the POST request with the body and query params
		final Response response = builder.build().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(postBody, MediaType.APPLICATION_JSON));
		response.bufferEntity();
		return response;
	}

	protected List<String> getCustomerNamesFromResponse(final Response response)
	{
		final CustomerSearchPageWsDTO results = response.readEntity(CustomerSearchPageWsDTO.class);
		return results.getEntries().stream().map(PrincipalWsDTO::getName).collect(Collectors.toList());
	}
}
