/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.searchprovidercssearchservices.search.data;

import de.hybris.platform.searchprovidercssearchservices.search.data.AbstractFacetFilterDTO;
import java.util.List;


import java.util.Objects;
public  class BucketsFacetFilterDTO extends AbstractFacetFilterDTO 

{



	/** <i>Generated property</i> for <code>BucketsFacetFilterDTO.bucketIds</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private List<String> bucketIds;
	
	public BucketsFacetFilterDTO()
	{
		// default constructor
	}
	
	public void setBucketIds(final List<String> bucketIds)
	{
		this.bucketIds = bucketIds;
	}

	public List<String> getBucketIds() 
	{
		return bucketIds;
	}
	

}