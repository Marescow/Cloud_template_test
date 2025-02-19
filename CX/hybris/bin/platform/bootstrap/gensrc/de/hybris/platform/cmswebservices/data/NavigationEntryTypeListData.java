/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.data;

import java.io.Serializable;
import de.hybris.platform.cmswebservices.data.NavigationEntryTypeData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Specifies a list of navigation entry types.
 *
 * @deprecated no longer needed
 */
@ApiModel(value="NavigationEntryTypeListData", description="Specifies a list of navigation entry types.")
@Deprecated(since = "1811", forRemoval = true)
public  class NavigationEntryTypeListData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>NavigationEntryTypeListData.navigationEntryTypes</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="navigationEntryTypes") 	
	private List<NavigationEntryTypeData> navigationEntryTypes;
	
	public NavigationEntryTypeListData()
	{
		// default constructor
	}
	
	public void setNavigationEntryTypes(final List<NavigationEntryTypeData> navigationEntryTypes)
	{
		this.navigationEntryTypes = navigationEntryTypes;
	}

	public List<NavigationEntryTypeData> getNavigationEntryTypes() 
	{
		return navigationEntryTypes;
	}
	

}