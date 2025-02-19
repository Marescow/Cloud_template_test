/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.storesession.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import java.util.Collection;


import java.util.Objects;
public  class CurrencyDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CurrencyDataList.currencies</code> property defined at extension <code>commercewebservices</code>. */
	
	private Collection<CurrencyData> currencies;
	
	public CurrencyDataList()
	{
		// default constructor
	}
	
	public void setCurrencies(final Collection<CurrencyData> currencies)
	{
		this.currencies = currencies;
	}

	public Collection<CurrencyData> getCurrencies() 
	{
		return currencies;
	}
	

}