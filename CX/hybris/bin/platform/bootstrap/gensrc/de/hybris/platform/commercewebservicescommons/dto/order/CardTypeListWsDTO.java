/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.order;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.order.CardTypeWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of a Card Type List
 */
@ApiModel(value="CardTypeList", description="Representation of a Card Type List")
public  class CardTypeListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** List of card types<br/><br/><i>Generated property</i> for <code>CardTypeListWsDTO.cardTypes</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="cardTypes", value="List of card types") 	
	private List<CardTypeWsDTO> cardTypes;
	
	public CardTypeListWsDTO()
	{
		// default constructor
	}
	
	public void setCardTypes(final List<CardTypeWsDTO> cardTypes)
	{
		this.cardTypes = cardTypes;
	}

	public List<CardTypeWsDTO> getCardTypes() 
	{
		return cardTypes;
	}
	

}