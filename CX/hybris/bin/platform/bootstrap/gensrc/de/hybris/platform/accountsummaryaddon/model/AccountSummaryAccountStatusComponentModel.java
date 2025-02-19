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
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;

/**
 * Generated model class for type AccountSummaryAccountStatusComponent first defined at extension accountsummaryaddon.
 */
@SuppressWarnings("all")
public class AccountSummaryAccountStatusComponentModel extends SimpleCMSComponentModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "AccountSummaryAccountStatusComponent";
	
	/** <i>Generated constant</i> - Attribute key of <code>AccountSummaryAccountStatusComponent.listViewPageSize</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String LISTVIEWPAGESIZE = "listViewPageSize";
	
	/** <i>Generated constant</i> - Attribute key of <code>AccountSummaryAccountStatusComponent.gridViewPageSize</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String GRIDVIEWPAGESIZE = "gridViewPageSize";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public AccountSummaryAccountStatusComponentModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public AccountSummaryAccountStatusComponentModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _catalogVersion initial attribute declared by type <code>CMSItem</code> at extension <code>cms2</code>
	 * @param _uid initial attribute declared by type <code>CMSItem</code> at extension <code>cms2</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public AccountSummaryAccountStatusComponentModel(final CatalogVersionModel _catalogVersion, final String _uid)
	{
		super();
		setCatalogVersion(_catalogVersion);
		setUid(_uid);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _catalogVersion initial attribute declared by type <code>CMSItem</code> at extension <code>cms2</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _uid initial attribute declared by type <code>CMSItem</code> at extension <code>cms2</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public AccountSummaryAccountStatusComponentModel(final CatalogVersionModel _catalogVersion, final ItemModel _owner, final String _uid)
	{
		super();
		setCatalogVersion(_catalogVersion);
		setOwner(_owner);
		setUid(_uid);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccountSummaryAccountStatusComponent.gridViewPageSize</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the gridViewPageSize - code
	 */
	@Accessor(qualifier = "gridViewPageSize", type = Accessor.Type.GETTER)
	public Integer getGridViewPageSize()
	{
		return getPersistenceContext().getPropertyValue(GRIDVIEWPAGESIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccountSummaryAccountStatusComponent.listViewPageSize</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the listViewPageSize - code
	 */
	@Accessor(qualifier = "listViewPageSize", type = Accessor.Type.GETTER)
	public Integer getListViewPageSize()
	{
		return getPersistenceContext().getPropertyValue(LISTVIEWPAGESIZE);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>AccountSummaryAccountStatusComponent.gridViewPageSize</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the gridViewPageSize - code
	 */
	@Accessor(qualifier = "gridViewPageSize", type = Accessor.Type.SETTER)
	public void setGridViewPageSize(final Integer value)
	{
		getPersistenceContext().setPropertyValue(GRIDVIEWPAGESIZE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>AccountSummaryAccountStatusComponent.listViewPageSize</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the listViewPageSize - code
	 */
	@Accessor(qualifier = "listViewPageSize", type = Accessor.Type.SETTER)
	public void setListViewPageSize(final Integer value)
	{
		getPersistenceContext().setPropertyValue(LISTVIEWPAGESIZE, value);
	}
	
}
