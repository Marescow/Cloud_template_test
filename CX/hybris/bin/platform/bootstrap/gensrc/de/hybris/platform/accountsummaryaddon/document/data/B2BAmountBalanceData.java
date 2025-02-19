/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.document.data;

import java.io.Serializable;
import  java.lang.String;
import de.hybris.platform.accountsummaryaddon.document.data.B2BNumberOfDayRangeData;
import java.util.Map;


import java.util.Objects;
public  class B2BAmountBalanceData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>B2BAmountBalanceData.openBalance</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String openBalance;

	/** <i>Generated property</i> for <code>B2BAmountBalanceData.pastDueBalance</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String pastDueBalance;

	/** <i>Generated property</i> for <code>B2BAmountBalanceData.currentBalance</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String currentBalance;

	/** <i>Generated property</i> for <code>B2BAmountBalanceData.dueBalance</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Map<B2BNumberOfDayRangeData,String> dueBalance;
	
	public B2BAmountBalanceData()
	{
		// default constructor
	}
	
	public void setOpenBalance(final String openBalance)
	{
		this.openBalance = openBalance;
	}

	public String getOpenBalance() 
	{
		return openBalance;
	}
	
	public void setPastDueBalance(final String pastDueBalance)
	{
		this.pastDueBalance = pastDueBalance;
	}

	public String getPastDueBalance() 
	{
		return pastDueBalance;
	}
	
	public void setCurrentBalance(final String currentBalance)
	{
		this.currentBalance = currentBalance;
	}

	public String getCurrentBalance() 
	{
		return currentBalance;
	}
	
	public void setDueBalance(final Map<B2BNumberOfDayRangeData,String> dueBalance)
	{
		this.dueBalance = dueBalance;
	}

	public Map<B2BNumberOfDayRangeData,String> getDueBalance() 
	{
		return dueBalance;
	}
	

}