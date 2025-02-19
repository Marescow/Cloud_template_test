/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.data;

import java.io.Serializable;
import de.hybris.platform.cmswebservices.data.ComponentTypeData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Specifies a list of available component types.
 */
@ApiModel(value="ComponentTypeListData", description="Specifies a list of available component types.")
public  class ComponentTypeListData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ComponentTypeListData.componentTypes</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="componentTypes") 	
	private List<ComponentTypeData> componentTypes;
	
	public ComponentTypeListData()
	{
		// default constructor
	}
	
	public void setComponentTypes(final List<ComponentTypeData> componentTypes)
	{
		this.componentTypes = componentTypes;
	}

	public List<ComponentTypeData> getComponentTypes() 
	{
		return componentTypes;
	}
	

}