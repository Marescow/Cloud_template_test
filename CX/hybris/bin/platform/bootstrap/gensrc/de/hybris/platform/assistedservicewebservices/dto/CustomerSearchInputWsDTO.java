/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedservicewebservices.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Customer search query.
 */
@ApiModel(value="CustomerSearchInput", description="Customer search query.")
public  class CustomerSearchInputWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Parts of a customer email or name.<br/><br/><i>Generated property</i> for <code>CustomerSearchInputWsDTO.query</code> property defined at extension <code>assistedservicewebservices</code>. */
@ApiModelProperty(name="query", value="Parts of a customer email or name.", required=false, example="johndoe@example.com") 	
	private String query;

	/** ID of the order.<br/><br/><i>Generated property</i> for <code>CustomerSearchInputWsDTO.orderId</code> property defined at extension <code>assistedservicewebservices</code>. */
@ApiModelProperty(name="orderId", value="ID of the order.", required=false, example="00000001") 	
	private String orderId;

	/** ID of the customer list.<br/><br/><i>Generated property</i> for <code>CustomerSearchInputWsDTO.customerListId</code> property defined at extension <code>assistedservicewebservices</code>. */
@ApiModelProperty(name="customerListId", value="ID of the customer list.", required=false, example="customerList1") 	
	private String customerListId;
	
	public CustomerSearchInputWsDTO()
	{
		// default constructor
	}
	
	public void setQuery(final String query)
	{
		this.query = query;
	}

	public String getQuery() 
	{
		return query;
	}
	
	public void setOrderId(final String orderId)
	{
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}
	
	public void setCustomerListId(final String customerListId)
	{
		this.customerListId = customerListId;
	}

	public String getCustomerListId() 
	{
		return customerListId;
	}
	

}