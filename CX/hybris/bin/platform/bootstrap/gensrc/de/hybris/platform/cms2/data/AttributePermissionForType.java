/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cms2.data;

import java.io.Serializable;


import java.util.Objects;
public  class AttributePermissionForType  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>AttributePermissionForType.typecode</code> property defined at extension <code>cms2</code>. */
	
	private String typecode;

	/** <i>Generated property</i> for <code>AttributePermissionForType.include</code> property defined at extension <code>cms2</code>. */
	
	private String include;

	/** <i>Generated property</i> for <code>AttributePermissionForType.exclude</code> property defined at extension <code>cms2</code>. */
	
	private String exclude;
	
	public AttributePermissionForType()
	{
		// default constructor
	}
	
	public void setTypecode(final String typecode)
	{
		this.typecode = typecode;
	}

	public String getTypecode() 
	{
		return typecode;
	}
	
	public void setInclude(final String include)
	{
		this.include = include;
	}

	public String getInclude() 
	{
		return include;
	}
	
	public void setExclude(final String exclude)
	{
		this.exclude = exclude;
	}

	public String getExclude() 
	{
		return exclude;
	}
	

}