/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.v2.controller;

import de.hybris.platform.commercefacades.product.data.PromotionResultData;
import de.hybris.platform.commercefacades.promotion.CommercePromotionRestrictionFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.commerceservices.promotion.CommercePromotionRestrictionException;
import de.hybris.platform.commercewebservicescommons.dto.order.SAPVoucherRequestWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.product.PromotionResultListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.voucher.VoucherListWsDTO;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdUserIdAndCartIdParam;
import de.hybris.platform.webservicescommons.swagger.ApiFieldsParam;
import de.hybris.platform.commercewebservices.core.exceptions.NoCheckoutCartException;
import de.hybris.platform.commercewebservices.core.product.data.PromotionResultDataList;
import de.hybris.platform.commercewebservices.core.voucher.data.VoucherDataList;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Controller
@RequestMapping(value = "/{baseSiteId}/users/{userId}/carts")
@CacheControl(directive = CacheControlDirective.NO_CACHE)
@Api(tags = "Cart Promotions")
public class CartPromotionsController extends BaseCommerceController
{
	private static final Logger LOG = LoggerFactory.getLogger(CartPromotionsController.class);

	@Resource(name = "commercePromotionRestrictionFacade")
	private CommercePromotionRestrictionFacade commercePromotionRestrictionFacade;

	@Secured({ "ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@GetMapping(value = "/{cartId}/promotions")
	@ResponseBody
	@ApiOperation(nickname = "getCartPromotions", value = "Get information about promotions applied on cart.", notes =
			"Returns information about the promotions applied on the cart. "
					+ "Requests pertaining to promotions have been developed for the previous version of promotions and vouchers, and as a result, some of them "
					+ "are currently not compatible with the new promotions engine.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public PromotionResultListWsDTO getCartPromotions(
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		LOG.debug("getCartPromotions");
		final List<PromotionResultData> appliedPromotions = new ArrayList<>();
		final List<PromotionResultData> orderPromotions = getSessionCart().getAppliedOrderPromotions();
		final List<PromotionResultData> productPromotions = getSessionCart().getAppliedProductPromotions();
		appliedPromotions.addAll(orderPromotions);
		appliedPromotions.addAll(productPromotions);

		final PromotionResultDataList dataList = new PromotionResultDataList();
		dataList.setPromotions(appliedPromotions);
		return getDataMapper().map(dataList, PromotionResultListWsDTO.class, fields);
	}

	@Secured({ "ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@GetMapping(value = "/{cartId}/promotions/{promotionId}")
	@ResponseBody
	@ApiOperation(nickname = "getCartPromotion", value = "Get information about promotion applied on cart.", notes =
			"Returns information about a promotion (with a specific promotionId), that has "
					+ "been applied on the cart. Requests pertaining to promotions have been developed for the previous version of promotions and vouchers, and as a result, some "
					+ "of them are currently not compatible with the new promotions engine.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public PromotionResultListWsDTO getCartPromotion(
			@ApiParam(value = "Promotion identifier (code)", required = true) @PathVariable final String promotionId,
			@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getCartPromotion: promotionId = {}", sanitize(promotionId));
		}
		final List<PromotionResultData> appliedPromotions = new ArrayList<>();
		final List<PromotionResultData> orderPromotions = getSessionCart().getAppliedOrderPromotions();
		final List<PromotionResultData> productPromotions = getSessionCart().getAppliedProductPromotions();
		for (final PromotionResultData prd : orderPromotions)
		{
			if (prd.getPromotionData().getCode().equals(promotionId))
			{
				appliedPromotions.add(prd);
			}
		}
		for (final PromotionResultData prd : productPromotions)
		{
			if (prd.getPromotionData().getCode().equals(promotionId))
			{
				appliedPromotions.add(prd);
			}
		}

		final PromotionResultDataList dataList = new PromotionResultDataList();
		dataList.setPromotions(appliedPromotions);
		return getDataMapper().map(dataList, PromotionResultListWsDTO.class, fields);
	}

	@Secured({ "ROLE_TRUSTED_CLIENT" })
	@PostMapping(value = "/{cartId}/promotions")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(nickname = "doApplyCartPromotion", value = "Enables promotions based on the promotionsId of the cart.", notes =
			"Enables a promotion for the order based on the promotionId defined for the cart. "
					+ "Requests pertaining to promotions have been developed for the previous version of promotions and vouchers, and as a result, some of them are currently not compatible "
					+ "with the new promotions engine.", authorizations = { @Authorization(value = "oauth2_client_credentials") })
	@ApiBaseSiteIdUserIdAndCartIdParam
	public void doApplyCartPromotion(
			@ApiParam(value = "Promotion identifier (code)", required = true) @RequestParam(required = true) final String promotionId)
			throws CommercePromotionRestrictionException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("doApplyCartPromotion: promotionId = {}", sanitize(promotionId));
		}
		commercePromotionRestrictionFacade.enablePromotionForCurrentCart(promotionId);
	}

	@Secured({ "ROLE_TRUSTED_CLIENT" })
	@DeleteMapping(value = "/{cartId}/promotions/{promotionId}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(nickname = "removeCartPromotion", value = "Disables the promotion based on the promotionsId of the cart.", notes =
			"Disables the promotion for the order based on the promotionId defined for the cart. "
					+ "Requests pertaining to promotions have been developed for the previous version of promotions and vouchers, and as a result, some of them are currently not compatible with "
					+ "the new promotions engine.", authorizations = { @Authorization(value = "oauth2_client_credentials") })
	@ApiBaseSiteIdUserIdAndCartIdParam
	public void removeCartPromotion(
			@ApiParam(value = "Promotion identifier (code)", required = true) @PathVariable final String promotionId)
			throws CommercePromotionRestrictionException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("removeCartPromotion: promotionId = {}", sanitize(promotionId));
		}
		commercePromotionRestrictionFacade.disablePromotionForCurrentCart(promotionId);
	}

	@Secured({ "ROLE_CLIENT", "ROLE_CUSTOMERGROUP", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_GUEST" })
	@GetMapping(value = "/{cartId}/vouchers")
	@ResponseBody
	@ApiOperation(nickname = "getCartVouchers", value = "Get a list of vouchers applied to the cart.", notes = "Returns a list of vouchers applied to the cart.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public VoucherListWsDTO getCartVouchers(@ApiFieldsParam @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		LOG.debug("getVouchers");
		final VoucherDataList dataList = new VoucherDataList();
		dataList.setVouchers(getVoucherFacade().getVouchersForCart());
		return getDataMapper().map(dataList, VoucherListWsDTO.class, fields);
	}

	/**
	 * @deprecated since 2205.27, please use {@link #applyCartVoucher(SAPVoucherRequestWsDTO)} instead
	 */
	@Deprecated(since = "2205.27", forRemoval = true)
	@Secured({ "ROLE_CLIENT", "ROLE_CUSTOMERGROUP", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_GUEST" })
	@PostMapping(value = "/{cartId}/vouchers")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(nickname = "doApplyCartVoucher", value = "Applies a voucher based on the voucherId defined for the cart.", notes = "Applies a voucher based on the voucherId defined for the cart. This endpoint is deprecated in the 2205.27 and its deletion is planned. Please use '/applyVoucher' instead.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public void doApplyCartVoucher(
			@ApiParam(value = "Voucher identifier (code)", required = true) @RequestParam final String voucherId)
			throws NoCheckoutCartException, VoucherOperationException
	{
		applyVoucherForCartInternal(voucherId);
	}

	/**
	 * @deprecated since 2205.27, please use {@link #doCartVoucherRemoval(SAPVoucherRequestWsDTO)} instead
	 */
	@Deprecated(since = "2205.27", forRemoval = true)
	@Secured({ "ROLE_CLIENT", "ROLE_CUSTOMERGROUP", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_GUEST" })
	@DeleteMapping(value = "/{cartId}/vouchers/{voucherId}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(nickname = "removeCartVoucher", value = "Deletes a voucher defined for the current cart.", notes = "Deletes a voucher based on the voucherId defined for the current cart. This endpoint is deprecated in the 2205.27 and its deletion is planned. Please use '/removeVoucher' instead.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public void removeCartVoucher(
			@ApiParam(value = "Voucher identifier (code)", required = true) @PathVariable final String voucherId)
			throws NoCheckoutCartException, VoucherOperationException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("release voucher : voucherCode = {}", sanitize(voucherId));
		}
		if (!getCheckoutFacade().hasCheckoutCart())
		{
			throw new NoCheckoutCartException("Cannot realese voucher. There was no checkout cart created yet!");
		}
		getVoucherFacade().releaseVoucher(voucherId);
	}

	@Secured({ "ROLE_CLIENT", "ROLE_CUSTOMERGROUP", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_GUEST" })
	@PostMapping(value = "/{cartId}/applyVoucher", consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(nickname = "applyCartVoucher", value = "Assigns a voucher to the cart.", notes = "Assigns a voucher to the cart using the voucher identifier. This endpoint is introduced in the 2205.27.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public void applyCartVoucher(@RequestBody SAPVoucherRequestWsDTO voucher)
			throws NoCheckoutCartException, VoucherOperationException
	{
		applyVoucherForCartInternal(voucher.getVoucherId());
	}

	@Secured({ "ROLE_CLIENT", "ROLE_CUSTOMERGROUP", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_GUEST" })
	@PostMapping(value = "/{cartId}/removeVoucher", consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(nickname = "doCartVoucherRemoval", value = "Removes a voucher defined for the current cart.", notes = "Removes a voucher associated with the current cart. This endpoint is introduced in the 2205.27.")
	@ApiBaseSiteIdUserIdAndCartIdParam
	public void doCartVoucherRemoval(@RequestBody SAPVoucherRequestWsDTO voucher)
			throws NoCheckoutCartException, VoucherOperationException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("release voucher : voucherCode = {}", sanitize(voucher.getVoucherId()));
		}
		if (!getCheckoutFacade().hasCheckoutCart())
		{
			throw new NoCheckoutCartException("Cannot realese voucher. There was no checkout cart created yet!");
		}
		getVoucherFacade().releaseVoucher(voucher.getVoucherId());
	}
}
