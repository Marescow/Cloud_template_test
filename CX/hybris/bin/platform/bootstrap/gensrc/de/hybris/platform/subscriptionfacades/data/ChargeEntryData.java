/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.subscriptionfacades.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.product.data.PriceData;


import java.util.Objects;
public  class ChargeEntryData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ChargeEntryData.price</code> property defined at extension <code>subscriptionfacades</code>. */
	
	private PriceData price;
	
	public ChargeEntryData()
	{
		// default constructor
	}
	
	public void setPrice(final PriceData price)
	{
		this.price = price;
	}

	public PriceData getPrice() 
	{
		return price;
	}
	

}