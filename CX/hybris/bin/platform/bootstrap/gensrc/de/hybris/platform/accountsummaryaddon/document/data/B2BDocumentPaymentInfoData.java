/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.document.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import java.util.Objects;
public  class B2BDocumentPaymentInfoData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>B2BDocumentPaymentInfoData.external</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String external;

	/** <i>Generated property</i> for <code>B2BDocumentPaymentInfoData.amount</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private BigDecimal amount;

	/** <i>Generated property</i> for <code>B2BDocumentPaymentInfoData.date</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Date date;

	/** <i>Generated property</i> for <code>B2BDocumentPaymentInfoData.referenceNumber</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String referenceNumber;

	/** <i>Generated property</i> for <code>B2BDocumentPaymentInfoData.paymentMethod</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String paymentMethod;

	/** <i>Generated property</i> for <code>B2BDocumentPaymentInfoData.formattedAmount</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String formattedAmount;
	
	public B2BDocumentPaymentInfoData()
	{
		// default constructor
	}
	
	public void setExternal(final String external)
	{
		this.external = external;
	}

	public String getExternal() 
	{
		return external;
	}
	
	public void setAmount(final BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getAmount() 
	{
		return amount;
	}
	
	public void setDate(final Date date)
	{
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
	}
	
	public void setReferenceNumber(final String referenceNumber)
	{
		this.referenceNumber = referenceNumber;
	}

	public String getReferenceNumber() 
	{
		return referenceNumber;
	}
	
	public void setPaymentMethod(final String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() 
	{
		return paymentMethod;
	}
	
	public void setFormattedAmount(final String formattedAmount)
	{
		this.formattedAmount = formattedAmount;
	}

	public String getFormattedAmount() 
	{
		return formattedAmount;
	}
	

}