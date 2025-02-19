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
import de.hybris.platform.assistedservicewebservices.dto.CustomerSuggestionWsDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Represents an autocomplete suggestion list of customers.
 */
@ApiModel(value="CustomerSuggestionsResponse", description="Represents an autocomplete suggestion list of customers.")
public  class CustomerSuggestionsResponseWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CustomerSuggestionsResponseWsDTO.suggestions</code> property defined at extension <code>assistedservicewebservices</code>. */
@ApiModelProperty(name="suggestions") 	
	private List<CustomerSuggestionWsDto> suggestions;
	
	public CustomerSuggestionsResponseWsDTO()
	{
		// default constructor
	}
	
	public void setSuggestions(final List<CustomerSuggestionWsDto> suggestions)
	{
		this.suggestions = suggestions;
	}

	public List<CustomerSuggestionWsDto> getSuggestions() 
	{
		return suggestions;
	}
	

}