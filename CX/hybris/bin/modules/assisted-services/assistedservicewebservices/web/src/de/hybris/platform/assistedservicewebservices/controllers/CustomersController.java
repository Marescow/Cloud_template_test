/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedservicewebservices.controllers;

import static de.hybris.platform.assistedservicewebservices.constants.AssistedservicewebservicesConstants.DEFAULT_CURRENT_PAGE;
import static de.hybris.platform.assistedservicewebservices.constants.AssistedservicewebservicesConstants.DEFAULT_PAGE_SIZE;
import static de.hybris.platform.assistedservicewebservices.constants.AssistedservicewebservicesConstants.QUERY;

import de.hybris.platform.assistedservicefacades.AssistedServiceFacade;
import de.hybris.platform.assistedservicefacades.user.data.AutoSuggestionCustomerData;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceCartBindException;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceException;
import de.hybris.platform.assistedservicewebservices.dto.CustomerSuggestionsInputWsDTO;
import de.hybris.platform.assistedservicewebservices.dto.CustomerSearchPageWsDTO;
import de.hybris.platform.assistedservicewebservices.dto.CustomerSearchInputWsDTO;
import de.hybris.platform.assistedservicewebservices.dto.CustomerSuggestionWsDto;
import de.hybris.platform.assistedservicewebservices.dto.CustomerSuggestionsResponseWsDTO;
import de.hybris.platform.assistedservicewebservices.helper.CustomerHelper;
import de.hybris.platform.assistedservicewebservices.utils.PaginationUtils;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commercewebservicescommons.errors.exceptions.RequestParameterException;
import de.hybris.platform.util.Config;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Controller
public class CustomersController extends AbstractAssistedServiceWebServiceController
{
	@Resource(name = "assistedServiceFacade")
	private AssistedServiceFacade assistedServiceFacade;

	@Resource(name = "customerHelper")
	private CustomerHelper customerHelper;

	@Resource(name = "customerSuggestionsInputWsDTOValidator")
	private Validator customerSuggestionsInputWsDTOValidator;

	/**
	 * @deprecated since 2205.31. Please use {@link de.hybris.platform.assistedservicewebservices.controllers.CustomersController#searchPageableCustomers(CustomerSearchInputWsDTO, int, int, String, String)} instead.
	 */
	@Deprecated(since = "2205.31", forRemoval = true)
	@ApiOperation(nickname = "getPageableCustomers", value = "Retrieves customers", notes = "Retrieves a paginated list of customers based on the provided query parameters. If a query term is present it will return customers based on that provided value. If orderId parameter is present, it will ignore the previous parameters and it will retrieve the customer associated to the given order. This can only be done by a user who is logged in ASM. This endpoint is deprecated in the 2205.31 update and its deletion is planned. Please use the POST /customers/search instead.")
	@RequestMapping(value = "/customers/search", method = RequestMethod.GET)
	@ResponseBody
	public CustomerSearchPageWsDTO getPageableCustomers(
			@ApiParam(value = "Customer uid search term.", required = false) @RequestParam(required = false) final String query,
			@ApiParam(value = "Current page.", required = false) @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@ApiParam(value = "Page size.", required = false) @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@ApiParam(value = "ID of the customer list.", required = false) @RequestParam(required = false) final String customerListId,
			@ApiParam(value = "ID of the order.", required = false) @RequestParam(required = false) final String orderId,
			@ApiParam(value = "Id of the BaseSite.", required = true) @RequestParam(required = true) final String baseSite,
			@ApiParam(value = "Sort parameter. Possible values include: byUidAsc, byUidDesc, byNameAsc, byNameDesc, byOrderDateAsc, byOrderDateDesc.", required = false) @RequestParam(required = false) final String sort)
	{
		SearchPageData<CustomerData> customerSearchPageData;
		validatePageableData(currentPage, pageSize);

		final PageableData pageableData = PaginationUtils.createPageableData(currentPage, pageSize, sort);

		if (StringUtils.isNotBlank(orderId))
		{
			customerSearchPageData = searchCustomerByOrder(orderId, pageableData);
		}
		else
		{
			if (StringUtils.isNotBlank(customerListId))
			{
				customerSearchPageData = getCustomersFromCustomerList(customerListId, query, pageableData);
			}
			else
			{
				customerSearchPageData = searchCustomersByQuery(query, pageableData);
			}
		}

		return getCustomerHelper().getCustomerSearchPageDto(customerSearchPageData);
	}

	@ApiOperation(nickname = "searchPageableCustomers", value = "Retrieves customers.", notes = "Retrieves a paginated list of customers based on the provided query parameters. If a query term is present, it will return customers based on that provided value. If orderId parameter is present, it will ignore the previous parameters and retrieve the customer associated to the given order. This can only be done by a user who is logged in ASM. This endpoint is added in the 2205.31 update.")
	@PostMapping(value = "/customers/search")
	@ResponseBody
	public CustomerSearchPageWsDTO searchPageableCustomers(
			@RequestBody(required = false) final CustomerSearchInputWsDTO customerSearchInputWsDTO,
			@ApiParam(value = "Current page number.", required = false) @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int page,
			@ApiParam(value = "Number of results per page.", required = false) @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@ApiParam(value = "Sort parameter. Possible values include: byUidAsc, byUidDesc, byNameAsc, byNameDesc, byOrderDateAsc, byOrderDateDesc.", required = false) @RequestParam(required = false) final String sort,
			@ApiParam(value = "Base site identifier.", required = true) @RequestParam(required = true) final String baseSite)
	{
		validatePaginationInputs(page, pageSize);

		final PageableData pageableData = PaginationUtils.createPageableData(page, pageSize, sort);

		final Optional<String> orderIdOpt = Optional.ofNullable(customerSearchInputWsDTO.getOrderId());
		final Optional<String> customerListIdOpt = Optional.ofNullable(customerSearchInputWsDTO.getCustomerListId());
		final Optional<String> queryOpt = Optional.ofNullable(customerSearchInputWsDTO.getQuery());

		SearchPageData<CustomerData> customerSearchPageData;
		if (orderIdOpt.isPresent() && StringUtils.isNotBlank(orderIdOpt.get()))
		{
			customerSearchPageData = searchCustomerByOrder(orderIdOpt.get(), pageableData);
		}
		else if (customerListIdOpt.isPresent() && StringUtils.isNotBlank(customerListIdOpt.get()))
		{
			customerSearchPageData = getCustomersFromCustomerList(customerListIdOpt.get(), queryOpt.orElse(null), pageableData);
		}
		else
		{
			customerSearchPageData = searchCustomersByQuery(queryOpt.orElse(null), pageableData);
		}

		return getCustomerHelper().getCustomerSearchPageDto(customerSearchPageData);
	}

	/**
	 * @deprecated since 2205.31. Please use {@link de.hybris.platform.assistedservicewebservices.controllers.CustomersController#doCustomerAutoComplete(CustomerSuggestionsInputWsDTO, String)} instead.
	 */
	@Deprecated(since = "2205.31", forRemoval = true)
	@ApiOperation(nickname = "autoComplete", value = "Retrieve customer suggestions for autocomplete.", notes = "Retrieves autocomplete information on customers based on the customer query. This endpoint is deprecated in the 2205.31 update and its deletion is planned. Please use the POST /customers/suggestions/search instead.")
	@RequestMapping(value = "/customers/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CustomerSuggestionWsDto> autoComplete(
			@ApiParam(value = "Customer query: first characters of customer email or part of name. A BadRequestException is thrown when the query is too short. Minimum query length can be updated via `assistedserviceswebservices.customer.suggestions.minimum.query.length` property.", required = true) @RequestParam(required = true) final String customerQuery,
			@ApiParam(value = "Id of the BaseSite.", required = true) @RequestParam(required = true) final String baseSite)
	{
		final int minQueryLength = Config.getInt("assistedserviceswebservices.customer.suggestions.minimum.query.length", 3);

		if (minQueryLength > customerQuery.length())
		{
			throw new BadRequestException("Customer query length must not be less than " + minQueryLength);
		}

		final List<AutoSuggestionCustomerData> customerSuggestions = getAssistedServiceFacade().getCustomerSuggestions(
				customerQuery);

		return getCustomerHelper().getCustomerSuggestions(customerSuggestions);
	}

	@ApiOperation(nickname = "doCustomerAutoComplete", value = "Retrieves an autocomplete suggestion list of customers.", notes = "Retrieves an autocomplete suggestion list of customers based on query parameters. This endpoint is added in the 2205.31 update.")
	@PostMapping(value = "/customers/suggestions/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomerSuggestionsResponseWsDTO doCustomerAutoComplete(
			@RequestBody final CustomerSuggestionsInputWsDTO customerSuggestionsInputWsDTO,
			@ApiParam(value = "Base site identifier.", required = true) @RequestParam(required = true) final String baseSite)
	{
		validate(customerSuggestionsInputWsDTO, "customerSuggestionsInputWsDTO", customerSuggestionsInputWsDTOValidator);
		final int minQueryLength = Config.getInt("assistedserviceswebservices.customer.suggestions.minimum.query.length", 3);

		final String customerQuery = customerSuggestionsInputWsDTO.getCustomerQuery();

		if (minQueryLength > customerQuery.length())
		{
			throw new BadRequestException("Customer query length must not be less than " + minQueryLength);
		}

		final List<AutoSuggestionCustomerData> customerSuggestions = getAssistedServiceFacade().getCustomerSuggestions(
				customerQuery);

		return getCustomerHelper().getCustomerSuggestionsResponse(customerSuggestions);
	}

	@ApiOperation(nickname = "bindCart", value = "Binds customer with provided id to cart if it's anonymous cart")
	@RequestMapping(value = "/bind-cart", method = RequestMethod.POST)
	public ResponseEntity<String> bindCart(
			@ApiParam(value = "Id of the Customer", required = true) @RequestParam(value = "customerId") final String customerId,
			@ApiParam(value = "Id of the anonymous Cart", required = true) @RequestParam(value = "cartId") final String cartId,
			@ApiParam(value = "Id of the BaseSite", required = true) @RequestParam final String baseSite)
	{
		try
		{
			getAssistedServiceFacade().bindCustomerToCartWithoutEmulating(customerId, cartId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch (final AssistedServiceCartBindException e)
		{
			throw new BadRequestException(e.getMessage());
		}
		catch (final AssistedServiceException e)
		{
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	protected void validate(final Object object, final String objectName, final Validator validator)
	{
		final Errors errors = new BeanPropertyBindingResult(object, objectName);
		validator.validate(object, errors);
		if (errors.hasErrors())
		{
			throw new WebserviceValidationException(errors);
		}
	}

	protected SearchPageData<CustomerData> getCustomersFromCustomerList(final String customerListId, final String query,
			final PageableData pageableData)
	{
		final Map<String, Object> parametersMap = new HashMap<>();

		if (StringUtils.isNotBlank(query))
		{
			parametersMap.put(QUERY, query);
		}
		return getCustomerListFacade().getPagedCustomersForCustomerListUID(customerListId,
				getCustomerFacade().getCurrentCustomerUid(), pageableData, parametersMap);
	}

	protected SearchPageData<CustomerData> searchCustomersByQuery(final String query, final PageableData pageableData)
	{
		return assistedServiceFacade.getPagedCustomers(query, pageableData);
	}

	protected SearchPageData<CustomerData> searchCustomerByOrder(final String orderId, final PageableData pageableData)
	{
		final List<CustomerData> customers = Arrays.asList(assistedServiceFacade.getCustomerByOrder(orderId));
		return createSearchPageData(customers, PaginationUtils.buildPaginationData(pageableData, customers));
	}

	public AssistedServiceFacade getAssistedServiceFacade()
	{
		return assistedServiceFacade;
	}

	public CustomerHelper getCustomerHelper()
	{
		return customerHelper;
	}

	private void validatePageableData(final int currentPage, final int pageSize)
	{
		if (pageSize <= 0)
		{
			throw new RequestParameterException("Request parameter pageSize must be greater than 0",
					RequestParameterException.INVALID, "pageSize");
		}
		if (currentPage < 0)
		{
			throw new RequestParameterException("Request parameter currentPage must be equal or greater than 0",
					RequestParameterException.INVALID, "currentPage");
		}
	}

	private void validatePaginationInputs(final int page, final int pageSize)
	{
		if (pageSize <= 0)
		{
			throw new RequestParameterException("Request parameter pageSize must be greater than 0",
					RequestParameterException.INVALID, "pageSize");
		}
		if (page < 0)
		{
			throw new RequestParameterException("Request parameter page must be equal or greater than 0",
					RequestParameterException.INVALID, "page");
		}
	}
}
