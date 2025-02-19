/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.validation.data;

import java.io.Serializable;
import de.hybris.platform.commercewebservices.core.validation.data.CartVoucherValidationData;
import java.util.List;


import java.util.Objects;
public  class CartVoucherValidationDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CartVoucherValidationDataList.CartVoucherValidationDataList</code> property defined at extension <code>commercewebservices</code>. */
	
	private List<CartVoucherValidationData> CartVoucherValidationDataList;
	
	public CartVoucherValidationDataList()
	{
		// default constructor
	}
	
	public void setCartVoucherValidationDataList(final List<CartVoucherValidationData> CartVoucherValidationDataList)
	{
		this.CartVoucherValidationDataList = CartVoucherValidationDataList;
	}

	public List<CartVoucherValidationData> getCartVoucherValidationDataList() 
	{
		return CartVoucherValidationDataList;
	}
	

}