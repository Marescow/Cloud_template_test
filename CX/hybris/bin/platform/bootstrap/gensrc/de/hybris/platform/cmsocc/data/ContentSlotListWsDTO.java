/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmsocc.data;

import java.io.Serializable;
import de.hybris.platform.cmsocc.data.ContentSlotWsDTO;
import java.util.List;


import java.util.Objects;
public  class ContentSlotListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ContentSlotListWsDTO.contentSlot</code> property defined at extension <code>cmsocc</code>. */
	
	private List<ContentSlotWsDTO> contentSlot;
	
	public ContentSlotListWsDTO()
	{
		// default constructor
	}
	
	public void setContentSlot(final List<ContentSlotWsDTO> contentSlot)
	{
		this.contentSlot = contentSlot;
	}

	public List<ContentSlotWsDTO> getContentSlot() 
	{
		return contentSlot;
	}
	

}