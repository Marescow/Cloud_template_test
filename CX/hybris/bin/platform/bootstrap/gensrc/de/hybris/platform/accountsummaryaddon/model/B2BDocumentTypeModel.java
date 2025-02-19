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
import de.hybris.platform.accountsummaryaddon.enums.DocumentPayableOrUsable;
import de.hybris.platform.accountsummaryaddon.model.B2BDocumentModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import java.util.Collection;
import java.util.Locale;

/**
 * Generated model class for type B2BDocumentType first defined at extension accountsummaryaddon.
 */
@SuppressWarnings("all")
public class B2BDocumentTypeModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "B2BDocumentType";
	
	/**<i>Generated relation code constant for relation <code>B2BDocumentB2BDocumenTypeRelation</code> defining source attribute <code>document</code> in extension <code>accountsummaryaddon</code>.</i>*/
	public static final String _B2BDOCUMENTB2BDOCUMENTYPERELATION = "B2BDocumentB2BDocumenTypeRelation";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentType.code</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String CODE = "code";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentType.includeInOpenBalance</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String INCLUDEINOPENBALANCE = "includeInOpenBalance";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentType.displayInAllList</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DISPLAYINALLLIST = "displayInAllList";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentType.name</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String NAME = "name";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentType.payableOrUsable</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String PAYABLEORUSABLE = "payableOrUsable";
	
	/** <i>Generated constant</i> - Attribute key of <code>B2BDocumentType.document</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DOCUMENT = "document";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public B2BDocumentTypeModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public B2BDocumentTypeModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _code initial attribute declared by type <code>B2BDocumentType</code> at extension <code>accountsummaryaddon</code>
	 * @param _displayInAllList initial attribute declared by type <code>B2BDocumentType</code> at extension <code>accountsummaryaddon</code>
	 * @param _includeInOpenBalance initial attribute declared by type <code>B2BDocumentType</code> at extension <code>accountsummaryaddon</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public B2BDocumentTypeModel(final String _code, final Boolean _displayInAllList, final Boolean _includeInOpenBalance)
	{
		super();
		setCode(_code);
		setDisplayInAllList(_displayInAllList);
		setIncludeInOpenBalance(_includeInOpenBalance);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _code initial attribute declared by type <code>B2BDocumentType</code> at extension <code>accountsummaryaddon</code>
	 * @param _displayInAllList initial attribute declared by type <code>B2BDocumentType</code> at extension <code>accountsummaryaddon</code>
	 * @param _includeInOpenBalance initial attribute declared by type <code>B2BDocumentType</code> at extension <code>accountsummaryaddon</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public B2BDocumentTypeModel(final String _code, final Boolean _displayInAllList, final Boolean _includeInOpenBalance, final ItemModel _owner)
	{
		super();
		setCode(_code);
		setDisplayInAllList(_displayInAllList);
		setIncludeInOpenBalance(_includeInOpenBalance);
		setOwner(_owner);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.code</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the code - code
	 */
	@Accessor(qualifier = "code", type = Accessor.Type.GETTER)
	public String getCode()
	{
		return getPersistenceContext().getPropertyValue(CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.displayInAllList</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the displayInAllList - displayInAllList
	 */
	@Accessor(qualifier = "displayInAllList", type = Accessor.Type.GETTER)
	public Boolean getDisplayInAllList()
	{
		return getPersistenceContext().getPropertyValue(DISPLAYINALLLIST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.document</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the document
	 */
	@Accessor(qualifier = "document", type = Accessor.Type.GETTER)
	public Collection<B2BDocumentModel> getDocument()
	{
		return getPersistenceContext().getPropertyValue(DOCUMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.includeInOpenBalance</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the includeInOpenBalance - includeInOpenBalance
	 */
	@Accessor(qualifier = "includeInOpenBalance", type = Accessor.Type.GETTER)
	public Boolean getIncludeInOpenBalance()
	{
		return getPersistenceContext().getPropertyValue(INCLUDEINOPENBALANCE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.name</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the name - name
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.GETTER)
	public String getName()
	{
		return getName(null);
	}
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.name</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @param loc the value localization key 
	 * @return the name - name
	 * @throws IllegalArgumentException if localization key cannot be mapped to data language
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.GETTER)
	public String getName(final Locale loc)
	{
		return getPersistenceContext().getLocalizedValue(NAME, loc);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BDocumentType.payableOrUsable</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the payableOrUsable - payableOrUsable
	 */
	@Accessor(qualifier = "payableOrUsable", type = Accessor.Type.GETTER)
	public DocumentPayableOrUsable getPayableOrUsable()
	{
		return getPersistenceContext().getPropertyValue(PAYABLEORUSABLE);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.code</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the code - code
	 */
	@Accessor(qualifier = "code", type = Accessor.Type.SETTER)
	public void setCode(final String value)
	{
		getPersistenceContext().setPropertyValue(CODE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.displayInAllList</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the displayInAllList - displayInAllList
	 */
	@Accessor(qualifier = "displayInAllList", type = Accessor.Type.SETTER)
	public void setDisplayInAllList(final Boolean value)
	{
		getPersistenceContext().setPropertyValue(DISPLAYINALLLIST, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.document</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the document
	 */
	@Accessor(qualifier = "document", type = Accessor.Type.SETTER)
	public void setDocument(final Collection<B2BDocumentModel> value)
	{
		getPersistenceContext().setPropertyValue(DOCUMENT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.includeInOpenBalance</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the includeInOpenBalance - includeInOpenBalance
	 */
	@Accessor(qualifier = "includeInOpenBalance", type = Accessor.Type.SETTER)
	public void setIncludeInOpenBalance(final Boolean value)
	{
		getPersistenceContext().setPropertyValue(INCLUDEINOPENBALANCE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.name</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the name - name
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.SETTER)
	public void setName(final String value)
	{
		setName(value,null);
	}
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.name</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the name - name
	 * @param loc the value localization key 
	 * @throws IllegalArgumentException if localization key cannot be mapped to data language
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.SETTER)
	public void setName(final String value, final Locale loc)
	{
		getPersistenceContext().setLocalizedValue(NAME, loc, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>B2BDocumentType.payableOrUsable</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the payableOrUsable - payableOrUsable
	 */
	@Accessor(qualifier = "payableOrUsable", type = Accessor.Type.SETTER)
	public void setPayableOrUsable(final DocumentPayableOrUsable value)
	{
		getPersistenceContext().setPropertyValue(PAYABLEORUSABLE, value);
	}
	
}
