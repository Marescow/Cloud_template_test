/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.searchprovidercssearchservices.search.data;

import de.hybris.platform.searchprovidercssearchservices.search.data.AbstractQueryDTO;
import java.util.List;


import java.util.Objects;
public  class OrQueryDTO extends AbstractQueryDTO 

{



	/** <i>Generated property</i> for <code>OrQueryDTO.queries</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private List<AbstractQueryDTO> queries;
	
	public OrQueryDTO()
	{
		// default constructor
	}
	
	public void setQueries(final List<AbstractQueryDTO> queries)
	{
		this.queries = queries;
	}

	public List<AbstractQueryDTO> getQueries() 
	{
		return queries;
	}
	

}