/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.data;

import java.io.Serializable;
import de.hybris.platform.accountsummaryaddon.document.data.B2BAmountBalanceData;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUnitData;
import de.hybris.platform.commercefacades.user.data.AddressData;


import java.util.Objects;
public  class AccountSummaryInfoData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>AccountSummaryInfoData.b2bUnitData</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private B2BUnitData b2bUnitData;

	/** <i>Generated property</i> for <code>AccountSummaryInfoData.amountBalanceData</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private B2BAmountBalanceData amountBalanceData;

	/** <i>Generated property</i> for <code>AccountSummaryInfoData.billingAddress</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private AddressData billingAddress;

	/** <i>Generated property</i> for <code>AccountSummaryInfoData.formattedCreditLimit</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String formattedCreditLimit;

	/** <i>Generated property</i> for <code>AccountSummaryInfoData.accountManagerName</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String accountManagerName;

	/** <i>Generated property</i> for <code>AccountSummaryInfoData.accountManagerEmail</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String accountManagerEmail;
	
	public AccountSummaryInfoData()
	{
		// default constructor
	}
	
	public void setB2bUnitData(final B2BUnitData b2bUnitData)
	{
		this.b2bUnitData = b2bUnitData;
	}

	public B2BUnitData getB2bUnitData() 
	{
		return b2bUnitData;
	}
	
	public void setAmountBalanceData(final B2BAmountBalanceData amountBalanceData)
	{
		this.amountBalanceData = amountBalanceData;
	}

	public B2BAmountBalanceData getAmountBalanceData() 
	{
		return amountBalanceData;
	}
	
	public void setBillingAddress(final AddressData billingAddress)
	{
		this.billingAddress = billingAddress;
	}

	public AddressData getBillingAddress() 
	{
		return billingAddress;
	}
	
	public void setFormattedCreditLimit(final String formattedCreditLimit)
	{
		this.formattedCreditLimit = formattedCreditLimit;
	}

	public String getFormattedCreditLimit() 
	{
		return formattedCreditLimit;
	}
	
	public void setAccountManagerName(final String accountManagerName)
	{
		this.accountManagerName = accountManagerName;
	}

	public String getAccountManagerName() 
	{
		return accountManagerName;
	}
	
	public void setAccountManagerEmail(final String accountManagerEmail)
	{
		this.accountManagerEmail = accountManagerEmail;
	}

	public String getAccountManagerEmail() 
	{
		return accountManagerEmail;
	}
	

}