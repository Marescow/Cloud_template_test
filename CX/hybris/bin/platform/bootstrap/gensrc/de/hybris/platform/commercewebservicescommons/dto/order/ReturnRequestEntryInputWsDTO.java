/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.order;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Representation of a return request entry input for an order
 */
@ApiModel(value="ReturnRequestEntryInput", description="Representation of a return request entry input for an order")
public  class ReturnRequestEntryInputWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Order entry number of the returned product<br/><br/><i>Generated property</i> for <code>ReturnRequestEntryInputWsDTO.orderEntryNumber</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="orderEntryNumber", value="Order entry number of the returned product", required=true, example="1") 	
	private Integer orderEntryNumber;

	/** Quantity of the product which belongs to the order entry and is requested to be returned<br/><br/><i>Generated property</i> for <code>ReturnRequestEntryInputWsDTO.quantity</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="quantity", value="Quantity of the product which belongs to the order entry and is requested to be returned", required=true, example="5") 	
	private Long quantity;
	
	public ReturnRequestEntryInputWsDTO()
	{
		// default constructor
	}
	
	public void setOrderEntryNumber(final Integer orderEntryNumber)
	{
		this.orderEntryNumber = orderEntryNumber;
	}

	public Integer getOrderEntryNumber() 
	{
		return orderEntryNumber;
	}
	
	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

	public Long getQuantity() 
	{
		return quantity;
	}
	

}