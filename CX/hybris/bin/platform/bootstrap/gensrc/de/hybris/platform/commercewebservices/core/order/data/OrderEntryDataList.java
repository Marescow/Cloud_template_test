/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.order.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import java.util.List;


import java.util.Objects;
public  class OrderEntryDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>OrderEntryDataList.orderEntries</code> property defined at extension <code>commercewebservices</code>. */
	
	private List<OrderEntryData> orderEntries;
	
	public OrderEntryDataList()
	{
		// default constructor
	}
	
	public void setOrderEntries(final List<OrderEntryData> orderEntries)
	{
		this.orderEntries = orderEntries;
	}

	public List<OrderEntryData> getOrderEntries() 
	{
		return orderEntries;
	}
	

}