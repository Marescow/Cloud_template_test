/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.document.data;

import java.io.Serializable;


import java.util.Objects;
public  class B2BNumberOfDayRangeData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>B2BNumberOfDayRangeData.minBoundery</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Integer minBoundery;

	/** <i>Generated property</i> for <code>B2BNumberOfDayRangeData.maxBoundery</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Integer maxBoundery;
	
	public B2BNumberOfDayRangeData()
	{
		// default constructor
	}
	
	public void setMinBoundery(final Integer minBoundery)
	{
		this.minBoundery = minBoundery;
	}

	public Integer getMinBoundery() 
	{
		return minBoundery;
	}
	
	public void setMaxBoundery(final Integer maxBoundery)
	{
		this.maxBoundery = maxBoundery;
	}

	public Integer getMaxBoundery() 
	{
		return maxBoundery;
	}
	

}