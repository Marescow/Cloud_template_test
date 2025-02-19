/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.customercouponocc.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Customer coupon operation request.
 */
@ApiModel(value="SAPCustomerCouponOperationRequest", description="Customer coupon operation request.")
public  class SAPCustomerCouponOperationRequestWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Coupon code. A valid coupon code can be used to get a discount.<br/><br/><i>Generated property</i> for <code>SAPCustomerCouponOperationRequestWsDTO.couponCode</code> property defined at extension <code>customercouponocc</code>. */
@ApiModelProperty(name="couponCode", value="Coupon code. A valid coupon code can be used to get a discount.", required=true, example="VCHR-H8BC-Y3D5-34AL") 	
	private String couponCode;
	
	public SAPCustomerCouponOperationRequestWsDTO()
	{
		// default constructor
	}
	
	public void setCouponCode(final String couponCode)
	{
		this.couponCode = couponCode;
	}

	public String getCouponCode() 
	{
		return couponCode;
	}
	

}