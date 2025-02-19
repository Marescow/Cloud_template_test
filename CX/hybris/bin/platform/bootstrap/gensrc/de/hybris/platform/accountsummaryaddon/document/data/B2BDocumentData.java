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
import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import java.math.BigDecimal;
import java.util.Date;


import java.util.Objects;
public  class B2BDocumentData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>B2BDocumentData.documentNumber</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String documentNumber;

	/** <i>Generated property</i> for <code>B2BDocumentData.documentType</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private B2BDocumentTypeData documentType;

	/** <i>Generated property</i> for <code>B2BDocumentData.status</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String status;

	/** <i>Generated property</i> for <code>B2BDocumentData.date</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Date date;

	/** <i>Generated property</i> for <code>B2BDocumentData.dueDate</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Date dueDate;

	/** <i>Generated property</i> for <code>B2BDocumentData.amount</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private BigDecimal amount;

	/** <i>Generated property</i> for <code>B2BDocumentData.formattedAmount</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String formattedAmount;

	/** <i>Generated property</i> for <code>B2BDocumentData.openAmount</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private BigDecimal openAmount;

	/** <i>Generated property</i> for <code>B2BDocumentData.formattedOpenAmount</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private String formattedOpenAmount;

	/** <i>Generated property</i> for <code>B2BDocumentData.currency</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private CurrencyData currency;

	/** <i>Generated property</i> for <code>B2BDocumentData.documentMedia</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private MediaData documentMedia;

	/** <i>Generated property</i> for <code>B2BDocumentData.selectable</code> property defined at extension <code>accountsummaryaddon</code>. */
	
	private Boolean selectable;
	
	public B2BDocumentData()
	{
		// default constructor
	}
	
	public void setDocumentNumber(final String documentNumber)
	{
		this.documentNumber = documentNumber;
	}

	public String getDocumentNumber() 
	{
		return documentNumber;
	}
	
	public void setDocumentType(final B2BDocumentTypeData documentType)
	{
		this.documentType = documentType;
	}

	public B2BDocumentTypeData getDocumentType() 
	{
		return documentType;
	}
	
	public void setStatus(final String status)
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	
	public void setDate(final Date date)
	{
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
	}
	
	public void setDueDate(final Date dueDate)
	{
		this.dueDate = dueDate;
	}

	public Date getDueDate() 
	{
		return dueDate;
	}
	
	public void setAmount(final BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getAmount() 
	{
		return amount;
	}
	
	public void setFormattedAmount(final String formattedAmount)
	{
		this.formattedAmount = formattedAmount;
	}

	public String getFormattedAmount() 
	{
		return formattedAmount;
	}
	
	public void setOpenAmount(final BigDecimal openAmount)
	{
		this.openAmount = openAmount;
	}

	public BigDecimal getOpenAmount() 
	{
		return openAmount;
	}
	
	public void setFormattedOpenAmount(final String formattedOpenAmount)
	{
		this.formattedOpenAmount = formattedOpenAmount;
	}

	public String getFormattedOpenAmount() 
	{
		return formattedOpenAmount;
	}
	
	public void setCurrency(final CurrencyData currency)
	{
		this.currency = currency;
	}

	public CurrencyData getCurrency() 
	{
		return currency;
	}
	
	public void setDocumentMedia(final MediaData documentMedia)
	{
		this.documentMedia = documentMedia;
	}

	public MediaData getDocumentMedia() 
	{
		return documentMedia;
	}
	
	public void setSelectable(final Boolean selectable)
	{
		this.selectable = selectable;
	}

	public Boolean getSelectable() 
	{
		return selectable;
	}
	

}