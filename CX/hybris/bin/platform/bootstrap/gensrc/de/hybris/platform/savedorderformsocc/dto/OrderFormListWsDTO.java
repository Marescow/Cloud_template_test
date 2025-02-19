/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.savedorderformsocc.dto;

import java.io.Serializable;
import de.hybris.platform.savedorderformsocc.dto.OrderFormWsDTO;
import java.util.List;


import java.util.Objects;
public  class OrderFormListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>OrderFormListWsDTO.orderForms</code> property defined at extension <code>savedorderformsocc</code>. */
	
	private List<OrderFormWsDTO> orderForms;
	
	public OrderFormListWsDTO()
	{
		// default constructor
	}
	
	public void setOrderForms(final List<OrderFormWsDTO> orderForms)
	{
		this.orderForms = orderForms;
	}

	public List<OrderFormWsDTO> getOrderForms() 
	{
		return orderForms;
	}
	

}