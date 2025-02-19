/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.v2.controller;

import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CommerceSaveCartParameterData;
import de.hybris.platform.commercefacades.order.data.CommerceSaveCartResultData;
import de.hybris.platform.commerceservices.order.CommerceSaveCartException;
import de.hybris.platform.commercewebservicescommons.dto.order.SAPSavedCartRequestWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.SaveCartResultWsDTO;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdAndUserIdParam;
import de.hybris.platform.webservicescommons.swagger.ApiFieldsParam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller for saved cart related requests such as saving a cart or retrieving/restoring/... a saved cart
 */
@Controller
@RequestMapping(value = "/{baseSiteId}/users/{userId}/carts")
@Api(tags = "Save Cart")
public class SaveCartController extends BaseCommerceController
{
	@Resource(name = "saveCartFacade")
	private SaveCartFacade saveCartFacade;

	/**
	 * @deprecated since 2205.27, please use {@link #doCartSave(String, SAPSavedCartRequestWsDTO, String)} instead
	 */
	@Deprecated(since = "2205.27", forRemoval = true)
	@RequestMapping(value = "/{cartId}/save", method = RequestMethod.PATCH)
	@ResponseBody
	@ApiOperation(nickname = "doSaveCart", value = "Explicitly saves a cart.", notes = "Explicitly saves a cart. This endpoint is deprecated in the 2205.27 and its deletion is planned. Please use '/savedCart' instead.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO doSaveCart(
			@ApiParam(value = "Cart identifier: cart code for logged in user, cart guid for anonymous user, 'current' for the last modified cart", required = true) @PathVariable final String cartId,
			@ApiParam(value = "The name that should be applied to the saved cart.") @RequestParam(value = "saveCartName", required = false) final String saveCartName,
			@ApiParam(value = "The description that should be applied to the saved cart.") @RequestParam(value = "saveCartDescription", required = false) final String saveCartDescription,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartParameterData parameters = new CommerceSaveCartParameterData();
		parameters.setCartId(cartId);
		parameters.setName(saveCartName);
		parameters.setDescription(saveCartDescription);

		final CommerceSaveCartResultData result = saveCartFacade.saveCart(parameters);
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}

	@PatchMapping(value = "/{cartId}/savedCart", consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(nickname = "doCartSave", value = "Updates a cart to save it.", notes =  "Updates a cart to explicitly save it. Adds the name and description of the saved cart if specified. This endpoint is introduced in the 2205.27.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO doCartSave(
			@ApiParam(value = "Cart identifier: cart code for logged-in user, cart GUID for anonymous user, or 'current' for the last modified cart.", required = true) @PathVariable final String cartId,
			@RequestBody SAPSavedCartRequestWsDTO savedCart,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartParameterData parameters = new CommerceSaveCartParameterData();
		parameters.setCartId(cartId);
		parameters.setName(savedCart.getName());
		parameters.setDescription(savedCart.getDescription());

		final CommerceSaveCartResultData result = saveCartFacade.saveCart(parameters);
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}

	@PatchMapping(value = "/{cartId}/restoresavedcart")
	@ResponseBody
	@ApiOperation(nickname = "doUpdateSavedCart", value = "Restore a saved cart.", notes = "Restore a saved cart.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO doUpdateSavedCart(
			@ApiParam(value = "Cart identifier: cart code for logged in user, cart guid for anonymous user, 'current' for the last modified cart", required = true) @PathVariable final String cartId,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartParameterData parameters = new CommerceSaveCartParameterData();
		parameters.setCartId(cartId);
		parameters.setEnableHooks(true);
		saveCartFacade.restoreSavedCart(parameters);

		final CommerceSaveCartResultData result = new CommerceSaveCartResultData();
		result.setSavedCartData(getSessionCart());
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}

	@RequestMapping(value = "/{cartId}/flagForDeletion", method = RequestMethod.PATCH)
	@ResponseBody
	@ApiOperation(nickname = "doUpdateFlagForDeletion", value = "Flag a cart for deletion.", notes =
			"Flags a cart for deletion (the cart doesn't have corresponding save cart attributes anymore). The cart is not "
					+ "actually deleted from the database. But with the removal of the saved cart attributes, this cart will be taken care of by the cart removal job just like any other cart.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO doUpdateFlagForDeletion(
			@ApiParam(value = "Cart identifier: cart code for logged in user, cart guid for anonymous user, 'current' for the last modified cart", required = true) @PathVariable final String cartId,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartResultData result = saveCartFacade.flagForDeletion(cartId);
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}

	@RequestMapping(value = "/{cartId}/savedcart", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(nickname = "getSavedCart", value = "Get a saved cart.", notes = "Returns a saved cart for an authenticated user. The cart is identified using the \"cartId\" parameter.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO getSavedCart(
			@ApiParam(value = "Cart identifier: cart code for logged in user, cart guid for anonymous user, 'current' for the last modified cart", required = true) @PathVariable final String cartId,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartParameterData parameters = new CommerceSaveCartParameterData();
		parameters.setCartId(cartId);

		final CommerceSaveCartResultData result = saveCartFacade.getCartForCodeAndCurrentUser(parameters);
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}

	/**
	 * @deprecated since 2205.27, please use {@link #copySavedCart(String, SAPSavedCartRequestWsDTO, String)} instead
	 */
	@Deprecated(since = "2205.27", forRemoval = true)
	@PostMapping(value = "/{cartId}/clonesavedcart")
	@ResponseBody
	@ApiOperation(nickname = "doCartClone", value = "Explicitly clones a cart.", notes = "Explicitly clones a cart. This endpoint is deprecated in the 2205.27 and its deletion is planned. Please use '/copySavedCart' instead.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO doCartClone(
			@ApiParam(value = "Cart identifier: cart code for logged in user, cart guid for anonymous user, 'current' for the last modified cart", required = true) @PathVariable final String cartId,
			@ApiParam(value = "The name that should be applied to the cloned cart.") @RequestParam(value = "name", required = false) final String name,
			@ApiParam(value = "The description that should be applied to the cloned cart.") @RequestParam(value = "description", required = false) final String description,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartParameterData parameters = new CommerceSaveCartParameterData();
		parameters.setCartId(cartId);
		parameters.setName(name);
		parameters.setDescription(description);

		final CommerceSaveCartResultData result = saveCartFacade.cloneSavedCart(parameters);
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}

	@PostMapping(value = "/{cartId}/copySavedCart", consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(nickname = "copySavedCart", value = "Creates a clone of a saved cart.", notes =  "Creates a clone of a saved cart. Customers can provide a name and a description for the cloned cart even though they aren't mandatory parameters. This endpoint is introduced in the 2205.27.")
	@ApiBaseSiteIdAndUserIdParam
	public SaveCartResultWsDTO copySavedCart(
			@ApiParam(value = "Cart identifier: cart code for logged-in user, cart GUID for anonymous user, or 'current' for the last modified cart.", required = true) @PathVariable final String cartId,
			@RequestBody SAPSavedCartRequestWsDTO savedCart,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws CommerceSaveCartException
	{
		final CommerceSaveCartParameterData parameters = new CommerceSaveCartParameterData();
		parameters.setCartId(cartId);
		parameters.setName(savedCart.getName());
		parameters.setDescription(savedCart.getDescription());

		final CommerceSaveCartResultData result = saveCartFacade.cloneSavedCart(parameters);
		return getDataMapper().map(result, SaveCartResultWsDTO.class, fields);
	}
}
