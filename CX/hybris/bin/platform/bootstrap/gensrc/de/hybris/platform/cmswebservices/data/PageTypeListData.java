/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.data;

import java.io.Serializable;
import de.hybris.platform.cmswebservices.data.PageTypeData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Specifies a list of available page types.
 */
@ApiModel(value="PageTypeListData", description="Specifies a list of available page types.")
public  class PageTypeListData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>PageTypeListData.pageTypes</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="pageTypes") 	
	private List<PageTypeData> pageTypes;
	
	public PageTypeListData()
	{
		// default constructor
	}
	
	public void setPageTypes(final List<PageTypeData> pageTypes)
	{
		this.pageTypes = pageTypes;
	}

	public List<PageTypeData> getPageTypes() 
	{
		return pageTypes;
	}
	

}