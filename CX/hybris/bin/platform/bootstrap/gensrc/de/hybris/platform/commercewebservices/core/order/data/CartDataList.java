/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.order.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.order.data.CartData;
import java.util.List;


import java.util.Objects;
public  class CartDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CartDataList.carts</code> property defined at extension <code>commercewebservices</code>. */
	
	private List<CartData> carts;
	
	public CartDataList()
	{
		// default constructor
	}
	
	public void setCarts(final List<CartData> carts)
	{
		this.carts = carts;
	}

	public List<CartData> getCarts() 
	{
		return carts;
	}
	

}