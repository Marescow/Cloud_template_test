/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
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
 * Customer suggestions query.
 */
@ApiModel(value="CustomerSuggestionsInputWsDTO", description="Customer suggestions query.")
public  class CustomerSuggestionsInputWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** The first characters of customer email or part of name. A BadRequestException is thrown when the query is too short. Minimum query length can be updated via `assistedserviceswebservices.customer.suggestions.minimum.query.length` property.<br/><br/><i>Generated property</i> for <code>CustomerSuggestionsInputWsDTO.customerQuery</code> property defined at extension <code>assistedservicewebservices</code>. */
@ApiModelProperty(name="customerQuery", value="The first characters of customer email or part of name. A BadRequestException is thrown when the query is too short. Minimum query length can be updated via `assistedserviceswebservices.customer.suggestions.minimum.query.length` property.", required=true, example="john.doe") 	
	private String customerQuery;
	
	public CustomerSuggestionsInputWsDTO()
	{
		// default constructor
	}
	
	public void setCustomerQuery(final String customerQuery)
	{
		this.customerQuery = customerQuery;
	}

	public String getCustomerQuery() 
	{
		return customerQuery;
	}
	

}