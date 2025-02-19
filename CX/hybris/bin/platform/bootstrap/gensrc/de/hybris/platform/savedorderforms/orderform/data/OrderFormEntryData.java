/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:42
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.savedorderforms.orderform.data;

import java.io.Serializable;


import java.util.Objects;
public  class OrderFormEntryData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>OrderFormEntryData.sku</code> property defined at extension <code>savedorderforms</code>. */
	
	private String sku;

	/** <i>Generated property</i> for <code>OrderFormEntryData.quantity</code> property defined at extension <code>savedorderforms</code>. */
	
	private Integer quantity;
	
	public OrderFormEntryData()
	{
		// default constructor
	}
	
	public void setSku(final String sku)
	{
		this.sku = sku;
	}

	public String getSku() 
	{
		return sku;
	}
	
	public void setQuantity(final Integer quantity)
	{
		this.quantity = quantity;
	}

	public Integer getQuantity() 
	{
		return quantity;
	}
	

}