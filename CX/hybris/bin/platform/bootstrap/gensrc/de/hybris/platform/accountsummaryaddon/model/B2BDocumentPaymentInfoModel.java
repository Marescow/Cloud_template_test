/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.model;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.accountsummaryaddon.model.B2BDocumentModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Generated model class for type B2BDocumentPaymentInfo first defined at extension accountsummaryaddon.
 */
@SuppressWarnings("all")
public class B2BDocumentPaymentInfoModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "B2BDocumentPaymentInfo";
	
	/**<i>Generated relation code constant for relation <code>B2BDocument2B2BDocumentPayInfo</code> defining source attribute <code>payDocument</code> in extension <code>accountsummaryaddon</code>.</i>*/
	public static final String _B2BDOCUMENT2B2BDOCUMENTPAYINFO = "B2BDocument2B2BDocumentPayInfo";
	
	/**<i>Generated relation code constant for relation <code>B2BDocument2B2BDocumentUseInfo</code> defining source attribute <code>useDocument</code> in extension <code>accountsummaryaddon</code>.</i>*/
	public static final String _B2BDOCUMENT2B2BDOCUMENTUSEINFO = "B2BDocument2B2BDocumentUseInfo";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.external</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String EXTERNAL = "external";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.amount</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String AMOUNT = "amount";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.date</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DATE = "date";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.ccTransactionNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String CCTRANSACTIONNUMBER = "ccTransactionNumber";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.paymentMethod</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String PAYMENTMETHOD = "paymentMethod";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.referenceNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String REFERENCENUMBER = "referenceNumber";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.payDocument</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String PAYDOCUMENT = "payDocument";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentPaymentInfo.useDocument</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String USEDOCUMENT = "useDocument";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public B2BDocumentPaymentInfoModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public B2BDocumentPaymentInfoModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _amount initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 * @param _date initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 * @param _external initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 * @param _payDocument initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public B2BDocumentPaymentInfoModel(final BigDecimal _amount, final Date _date, final String _external, final B2BDocumentModel _payDocument)
	{
		super();
		setAmount(_amount);
		setDate(_date);
		setExternal(_external);
		setPayDocument(_payDocument);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _amount initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 * @param _date initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 * @param _external initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _payDocument initial attribute declared by type <code>B2BDocumentPaymentInfo</code> at extension <code>accountsummaryaddon</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public B2BDocumentPaymentInfoModel(final BigDecimal _amount, final Date _date, final String _external, final ItemModel _owner, final B2BDocumentModel _payDocument)
	{
		super();
		setAmount(_amount);
		setDate(_date);
		setExternal(_external);
		setOwner(_owner);
		setPayDocument(_payDocument);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.amount</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the amount - amount
	 */
	@Accessor(qualifier = "amount", type = Accessor.Type.GETTER)
	public BigDecimal getAmount()
	{
		return getPersistenceContext().getPropertyValue(AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.ccTransactionNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the ccTransactionNumber
	 */
	@Accessor(qualifier = "ccTransactionNumber", type = Accessor.Type.GETTER)
	public String getCcTransactionNumber()
	{
		return getPersistenceContext().getPropertyValue(CCTRANSACTIONNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.date</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the date - date
	 */
	@Accessor(qualifier = "date", type = Accessor.Type.GETTER)
	public Date getDate()
	{
		return getPersistenceContext().getPropertyValue(DATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.external</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the external
	 */
	@Accessor(qualifier = "external", type = Accessor.Type.GETTER)
	public String getExternal()
	{
		return getPersistenceContext().getPropertyValue(EXTERNAL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.payDocument</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the payDocument
	 */
	@Accessor(qualifier = "payDocument", type = Accessor.Type.GETTER)
	public B2BDocumentModel getPayDocument()
	{
		return getPersistenceContext().getPropertyValue(PAYDOCUMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.paymentMethod</code> dynamic attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the paymentMethod - Payment Method of the use document
	 */
	@Accessor(qualifier = "paymentMethod", type = Accessor.Type.GETTER)
	public String getPaymentMethod()
	{
		return getPersistenceContext().getDynamicValue(this,PAYMENTMETHOD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.referenceNumber</code> dynamic attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the referenceNumber - The use document reference number
	 */
	@Accessor(qualifier = "referenceNumber", type = Accessor.Type.GETTER)
	public String getReferenceNumber()
	{
		return getPersistenceContext().getDynamicValue(this,REFERENCENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentPaymentInfo.useDocument</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the useDocument
	 */
	@Accessor(qualifier = "useDocument", type = Accessor.Type.GETTER)
	public B2BDocumentModel getUseDocument()
	{
		return getPersistenceContext().getPropertyValue(USEDOCUMENT);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentPaymentInfo.amount</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the amount - amount
	 */
	@Accessor(qualifier = "amount", type = Accessor.Type.SETTER)
	public void setAmount(final BigDecimal value)
	{
		getPersistenceContext().setPropertyValue(AMOUNT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentPaymentInfo.ccTransactionNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the ccTransactionNumber
	 */
	@Accessor(qualifier = "ccTransactionNumber", type = Accessor.Type.SETTER)
	public void setCcTransactionNumber(final String value)
	{
		getPersistenceContext().setPropertyValue(CCTRANSACTIONNUMBER, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentPaymentInfo.date</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the date - date
	 */
	@Accessor(qualifier = "date", type = Accessor.Type.SETTER)
	public void setDate(final Date value)
	{
		getPersistenceContext().setPropertyValue(DATE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentPaymentInfo.external</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the external
	 */
	@Accessor(qualifier = "external", type = Accessor.Type.SETTER)
	public void setExternal(final String value)
	{
		getPersistenceContext().setPropertyValue(EXTERNAL, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentPaymentInfo.payDocument</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the payDocument
	 */
	@Accessor(qualifier = "payDocument", type = Accessor.Type.SETTER)
	public void setPayDocument(final B2BDocumentModel value)
	{
		getPersistenceContext().setPropertyValue(PAYDOCUMENT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentPaymentInfo.useDocument</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the useDocument
	 */
	@Accessor(qualifier = "useDocument", type = Accessor.Type.SETTER)
	public void setUseDocument(final B2BDocumentModel value)
	{
		getPersistenceContext().setPropertyValue(USEDOCUMENT, value);
	}
	
}
