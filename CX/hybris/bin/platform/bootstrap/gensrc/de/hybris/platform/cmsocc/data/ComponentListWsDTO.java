/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmsocc.data;

import java.io.Serializable;
import de.hybris.platform.cmsocc.data.ComponentWsDTO;
import java.util.List;


import java.util.Objects;
public  class ComponentListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ComponentListWsDTO.component</code> property defined at extension <code>cmsocc</code>. */
	
	private List<ComponentWsDTO> component;
	
	public ComponentListWsDTO()
	{
		// default constructor
	}
	
	public void setComponent(final List<ComponentWsDTO> component)
	{
		this.component = component;
	}

	public List<ComponentWsDTO> getComponent() 
	{
		return component;
	}
	

}