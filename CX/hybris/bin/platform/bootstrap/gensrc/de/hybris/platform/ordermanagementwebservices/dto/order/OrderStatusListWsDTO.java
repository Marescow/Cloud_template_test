/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:34
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ordermanagementwebservices.dto.order;

import java.io.Serializable;
import java.util.List;


import java.util.Objects;
public  class OrderStatusListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>OrderStatusListWsDTO.statuses</code> property defined at extension <code>ordermanagementwebservices</code>. */
	
	private List<String> statuses;
	
	public OrderStatusListWsDTO()
	{
		// default constructor
	}
	
	public void setStatuses(final List<String> statuses)
	{
		this.statuses = statuses;
	}

	public List<String> getStatuses() 
	{
		return statuses;
	}
	

}