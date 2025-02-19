/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.personalizationcmsweb.data;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * List of CxCmsComponents
 */
@ApiModel(value="cxCmsComponentsList", description="List of CxCmsComponents")
public  class CxCmsComponentsListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** List of components<br/><br/><i>Generated property</i> for <code>CxCmsComponentsListWsDTO.components</code> property defined at extension <code>personalizationcmsweb</code>. */
@ApiModelProperty(name="components", value="List of components") 	
	private List<String> components;
	
	public CxCmsComponentsListWsDTO()
	{
		// default constructor
	}
	
	public void setComponents(final List<String> components)
	{
		this.components = components;
	}

	public List<String> getComponents() 
	{
		return components;
	}
	

}