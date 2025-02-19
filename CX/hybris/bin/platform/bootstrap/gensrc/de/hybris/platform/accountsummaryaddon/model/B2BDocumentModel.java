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
import de.hybris.platform.accountsummaryaddon.enums.DocumentStatus;
import de.hybris.platform.accountsummaryaddon.model.B2BDocumentPaymentInfoModel;
import de.hybris.platform.accountsummaryaddon.model.B2BDocumentTypeModel;
import de.hybris.platform.accountsummaryaddon.model.DocumentMediaModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Generated model class for type B2BDocument first defined at extension accountsummaryaddon.
 */
@SuppressWarnings("all")
public class B2BDocumentModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "B2BDocument";
	
	/**<i>Generated relation code constant for relation <code>B2BUnit2B2BDocument</code> defining source attribute <code>unit</code> in extension <code>accountsummaryaddon</code>.</i>*/
	public static final String _B2BUNIT2B2BDOCUMENT = "B2BUnit2B2BDocument";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.documentNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DOCUMENTNUMBER = "documentNumber";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.status</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String STATUS = "status";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.date</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DATE = "date";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.dueDate</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DUEDATE = "dueDate";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.amount</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String AMOUNT = "amount";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.openAmount</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String OPENAMOUNT = "openAmount";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.currency</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String CURRENCY = "currency";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.documentMedia</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DOCUMENTMEDIA = "documentMedia";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.documentType</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DOCUMENTTYPE = "documentType";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.unit</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String UNIT = "unit";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.order</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String ORDER = "order";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.payDocumentPaymentInfo</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String PAYDOCUMENTPAYMENTINFO = "payDocumentPaymentInfo";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocument.useDocumentPaymentInfo</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String USEDOCUMENTPAYMENTINFO = "useDocumentPaymentInfo";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public B2BDocumentModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public B2BDocumentModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _amount initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _currency initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _documentNumber initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _documentType initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _unit initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public B2BDocumentModel(final BigDecimal _amount, final CurrencyModel _currency, final String _documentNumber, final B2BDocumentTypeModel _documentType, final B2BUnitModel _unit)
	{
		super();
		setAmount(_amount);
		setCurrency(_currency);
		setDocumentNumber(_documentNumber);
		setDocumentType(_documentType);
		setUnit(_unit);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _amount initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _currency initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _documentNumber initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _documentType initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _unit initial attribute declared by type <code>B2BDocument</code> at extension <code>accountsummaryaddon</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public B2BDocumentModel(final BigDecimal _amount, final CurrencyModel _currency, final String _documentNumber, final B2BDocumentTypeModel _documentType, final ItemModel _owner, final B2BUnitModel _unit)
	{
		super();
		setAmount(_amount);
		setCurrency(_currency);
		setDocumentNumber(_documentNumber);
		setDocumentType(_documentType);
		setOwner(_owner);
		setUnit(_unit);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.amount</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the amount - amount
	 */
	@Accessor(qualifier = "amount", type = Accessor.Type.GETTER)
	public BigDecimal getAmount()
	{
		return getPersistenceContext().getPropertyValue(AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.currency</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the currency
	 */
	@Accessor(qualifier = "currency", type = Accessor.Type.GETTER)
	public CurrencyModel getCurrency()
	{
		return getPersistenceContext().getPropertyValue(CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.date</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the date - date
	 */
	@Accessor(qualifier = "date", type = Accessor.Type.GETTER)
	public Date getDate()
	{
		return getPersistenceContext().getPropertyValue(DATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.documentMedia</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the documentMedia
	 */
	@Accessor(qualifier = "documentMedia", type = Accessor.Type.GETTER)
	public DocumentMediaModel getDocumentMedia()
	{
		return getPersistenceContext().getPropertyValue(DOCUMENTMEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.documentNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the documentNumber
	 */
	@Accessor(qualifier = "documentNumber", type = Accessor.Type.GETTER)
	public String getDocumentNumber()
	{
		return getPersistenceContext().getPropertyValue(DOCUMENTNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.documentType</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the documentType
	 */
	@Accessor(qualifier = "documentType", type = Accessor.Type.GETTER)
	public B2BDocumentTypeModel getDocumentType()
	{
		return getPersistenceContext().getPropertyValue(DOCUMENTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.dueDate</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the dueDate - dueDate
	 */
	@Accessor(qualifier = "dueDate", type = Accessor.Type.GETTER)
	public Date getDueDate()
	{
		return getPersistenceContext().getPropertyValue(DUEDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.openAmount</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the openAmount - openAmount
	 */
	@Accessor(qualifier = "openAmount", type = Accessor.Type.GETTER)
	public BigDecimal getOpenAmount()
	{
		return getPersistenceContext().getPropertyValue(OPENAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.order</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the order
	 */
	@Accessor(qualifier = "order", type = Accessor.Type.GETTER)
	public AbstractOrderModel getOrder()
	{
		return getPersistenceContext().getPropertyValue(ORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.payDocumentPaymentInfo</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the payDocumentPaymentInfo
	 */
	@Accessor(qualifier = "payDocumentPaymentInfo", type = Accessor.Type.GETTER)
	public Collection<B2BDocumentPaymentInfoModel> getPayDocumentPaymentInfo()
	{
		return getPersistenceContext().getPropertyValue(PAYDOCUMENTPAYMENTINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.status</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the status
	 */
	@Accessor(qualifier = "status", type = Accessor.Type.GETTER)
	public DocumentStatus getStatus()
	{
		return getPersistenceContext().getPropertyValue(STATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.unit</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the unit
	 */
	@Accessor(qualifier = "unit", type = Accessor.Type.GETTER)
	public B2BUnitModel getUnit()
	{
		return getPersistenceContext().getPropertyValue(UNIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocument.useDocumentPaymentInfo</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the useDocumentPaymentInfo
	 */
	@Accessor(qualifier = "useDocumentPaymentInfo", type = Accessor.Type.GETTER)
	public Collection<B2BDocumentPaymentInfoModel> getUseDocumentPaymentInfo()
	{
		return getPersistenceContext().getPropertyValue(USEDOCUMENTPAYMENTINFO);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.amount</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the amount - amount
	 */
	@Accessor(qualifier = "amount", type = Accessor.Type.SETTER)
	public void setAmount(final BigDecimal value)
	{
		getPersistenceContext().setPropertyValue(AMOUNT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.currency</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the currency
	 */
	@Accessor(qualifier = "currency", type = Accessor.Type.SETTER)
	public void setCurrency(final CurrencyModel value)
	{
		getPersistenceContext().setPropertyValue(CURRENCY, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.date</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the date - date
	 */
	@Accessor(qualifier = "date", type = Accessor.Type.SETTER)
	public void setDate(final Date value)
	{
		getPersistenceContext().setPropertyValue(DATE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.documentMedia</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the documentMedia
	 */
	@Accessor(qualifier = "documentMedia", type = Accessor.Type.SETTER)
	public void setDocumentMedia(final DocumentMediaModel value)
	{
		getPersistenceContext().setPropertyValue(DOCUMENTMEDIA, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.documentNumber</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the documentNumber
	 */
	@Accessor(qualifier = "documentNumber", type = Accessor.Type.SETTER)
	public void setDocumentNumber(final String value)
	{
		getPersistenceContext().setPropertyValue(DOCUMENTNUMBER, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.documentType</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the documentType
	 */
	@Accessor(qualifier = "documentType", type = Accessor.Type.SETTER)
	public void setDocumentType(final B2BDocumentTypeModel value)
	{
		getPersistenceContext().setPropertyValue(DOCUMENTTYPE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.dueDate</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the dueDate - dueDate
	 */
	@Accessor(qualifier = "dueDate", type = Accessor.Type.SETTER)
	public void setDueDate(final Date value)
	{
		getPersistenceContext().setPropertyValue(DUEDATE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.openAmount</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the openAmount - openAmount
	 */
	@Accessor(qualifier = "openAmount", type = Accessor.Type.SETTER)
	public void setOpenAmount(final BigDecimal value)
	{
		getPersistenceContext().setPropertyValue(OPENAMOUNT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.order</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the order
	 */
	@Accessor(qualifier = "order", type = Accessor.Type.SETTER)
	public void setOrder(final AbstractOrderModel value)
	{
		getPersistenceContext().setPropertyValue(ORDER, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.payDocumentPaymentInfo</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the payDocumentPaymentInfo
	 */
	@Accessor(qualifier = "payDocumentPaymentInfo", type = Accessor.Type.SETTER)
	public void setPayDocumentPaymentInfo(final Collection<B2BDocumentPaymentInfoModel> value)
	{
		getPersistenceContext().setPropertyValue(PAYDOCUMENTPAYMENTINFO, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.status</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the status
	 */
	@Accessor(qualifier = "status", type = Accessor.Type.SETTER)
	public void setStatus(final DocumentStatus value)
	{
		getPersistenceContext().setPropertyValue(STATUS, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.unit</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the unit
	 */
	@Accessor(qualifier = "unit", type = Accessor.Type.SETTER)
	public void setUnit(final B2BUnitModel value)
	{
		getPersistenceContext().setPropertyValue(UNIT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocument.useDocumentPaymentInfo</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the useDocumentPaymentInfo
	 */
	@Accessor(qualifier = "useDocumentPaymentInfo", type = Accessor.Type.SETTER)
	public void setUseDocumentPaymentInfo(final Collection<B2BDocumentPaymentInfoModel> value)
	{
		getPersistenceContext().setPropertyValue(USEDOCUMENTPAYMENTINFO, value);
	}
	
}
