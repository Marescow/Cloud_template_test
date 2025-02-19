/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.eventtracking.model.events;

import java.io.Serializable;

import de.hybris.eventtracking.model.events.AbstractProductAwareTrackingEvent;

public  class AbstractProductAndCartAwareTrackingEvent extends AbstractProductAwareTrackingEvent 
{


	/** <i>Generated property</i> for <code>AbstractProductAndCartAwareTrackingEvent.cartId</code> property defined at extension <code>eventtrackingmodel</code>. */
	
	private String cartId;
	
	public AbstractProductAndCartAwareTrackingEvent()
	{
		super();
	}

	public AbstractProductAndCartAwareTrackingEvent(final Serializable source)
	{
		super(source);
	}
	
	public void setCartId(final String cartId)
	{
		this.cartId = cartId;
	}

	public String getCartId() 
	{
		return cartId;
	}
	


}
