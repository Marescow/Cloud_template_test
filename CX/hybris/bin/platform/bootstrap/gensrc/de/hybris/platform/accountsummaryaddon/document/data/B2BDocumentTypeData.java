/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:34
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.document.data;

import java.io.Serializable;


import java.util.Objects;
public  class B2BDocumentTypeData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>B2BDocumentTypeData.code</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String code;

	/** <i>Generated property</i> for <code>B2BDocumentTypeData.includeInOpenBalance</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Boolean includeInOpenBalance;

	/** <i>Generated property</i> for <code>B2BDocumentTypeData.displayInAllList</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Boolean displayInAllList;

	/** <i>Generated property</i> for <code>B2BDocumentTypeData.name</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String name;

	/** <i>Generated property</i> for <code>B2BDocumentTypeData.payableOrUsable</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String payableOrUsable;
	
	public B2BDocumentTypeData()
	{
		// default constructor
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	
	public void setIncludeInOpenBalance(final Boolean includeInOpenBalance)
	{
		this.includeInOpenBalance = includeInOpenBalance;
	}

	public Boolean getIncludeInOpenBalance() 
	{
		return includeInOpenBalance;
	}
	
	public void setDisplayInAllList(final Boolean displayInAllList)
	{
		this.displayInAllList = displayInAllList;
	}

	public Boolean getDisplayInAllList() 
	{
		return displayInAllList;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setPayableOrUsable(final String payableOrUsable)
	{
		this.payableOrUsable = payableOrUsable;
	}

	public String getPayableOrUsable() 
	{
		return payableOrUsable;
	}
	

}