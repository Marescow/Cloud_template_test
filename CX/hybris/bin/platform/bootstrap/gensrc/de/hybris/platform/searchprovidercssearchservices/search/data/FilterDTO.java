/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.searchprovidercssearchservices.search.data;

import java.io.Serializable;
import de.hybris.platform.searchprovidercssearchservices.search.data.AbstractQueryDTO;


import java.util.Objects;
public  class FilterDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>FilterDTO.query</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private AbstractQueryDTO query;
	
	public FilterDTO()
	{
		// default constructor
	}
	
	public void setQuery(final AbstractQueryDTO query)
	{
		this.query = query;
	}

	public AbstractQueryDTO getQuery() 
	{
		return query;
	}
	

}