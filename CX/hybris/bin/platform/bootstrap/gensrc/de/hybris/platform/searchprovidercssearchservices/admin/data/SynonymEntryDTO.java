/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.searchprovidercssearchservices.admin.data;

import java.io.Serializable;
import java.util.List;


import java.util.Objects;
public  class SynonymEntryDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>SynonymEntryDTO.id</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private String id;

	/** <i>Generated property</i> for <code>SynonymEntryDTO.input</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private List<String> input;

	/** <i>Generated property</i> for <code>SynonymEntryDTO.synonyms</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private List<String> synonyms;
	
	public SynonymEntryDTO()
	{
		// default constructor
	}
	
	public void setId(final String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	
	public void setInput(final List<String> input)
	{
		this.input = input;
	}

	public List<String> getInput() 
	{
		return input;
	}
	
	public void setSynonyms(final List<String> synonyms)
	{
		this.synonyms = synonyms;
	}

	public List<String> getSynonyms() 
	{
		return synonyms;
	}
	

}