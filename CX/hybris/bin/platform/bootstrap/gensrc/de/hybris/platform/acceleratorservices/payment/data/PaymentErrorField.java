/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.payment.data;

import java.io.Serializable;


import java.util.Objects;
public  class PaymentErrorField  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>PaymentErrorField.name</code> property defined at extension <code>acceleratorservices</code>. */
	
	private String name;

	/** <i>Generated property</i> for <code>PaymentErrorField.missing</code> property defined at extension <code>acceleratorservices</code>. */
	
	private boolean missing;

	/** <i>Generated property</i> for <code>PaymentErrorField.invalid</code> property defined at extension <code>acceleratorservices</code>. */
	
	private boolean invalid;
	
	public PaymentErrorField()
	{
		// default constructor
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setMissing(final boolean missing)
	{
		this.missing = missing;
	}

	public boolean isMissing() 
	{
		return missing;
	}
	
	public void setInvalid(final boolean invalid)
	{
		this.invalid = invalid;
	}

	public boolean isInvalid() 
	{
		return invalid;
	}
	

}