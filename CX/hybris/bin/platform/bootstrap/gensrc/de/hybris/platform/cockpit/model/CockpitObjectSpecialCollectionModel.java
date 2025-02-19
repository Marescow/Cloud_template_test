/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cockpit.model;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.cockpit.enums.CockpitSpecialCollectionType;
import de.hybris.platform.cockpit.model.CockpitObjectAbstractCollectionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;

/**
 * Generated model class for type CockpitObjectSpecialCollection first defined at extension cockpit.
 */
@SuppressWarnings("all")
public class CockpitObjectSpecialCollectionModel extends CockpitObjectAbstractCollectionModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "CockpitObjectSpecialCollection";
	
	/** <i>Generated constant</i> - Attribute key of <code>CockpitObjectSpecialCollection.collectionType</code> attribute defined at extension <code>cockpit</code>. */
	public static final String COLLECTIONTYPE = "collectionType";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public CockpitObjectSpecialCollectionModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public CockpitObjectSpecialCollectionModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _collectionType initial attribute declared by type <code>CockpitObjectSpecialCollection</code> at extension <code>cockpit</code>
	 * @param _qualifier initial attribute declared by type <code>CockpitObjectAbstractCollection</code> at extension <code>cockpit</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public CockpitObjectSpecialCollectionModel(final CockpitSpecialCollectionType _collectionType, final String _qualifier)
	{
		super();
		setCollectionType(_collectionType);
		setQualifier(_qualifier);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _collectionType initial attribute declared by type <code>CockpitObjectSpecialCollection</code> at extension <code>cockpit</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _qualifier initial attribute declared by type <code>CockpitObjectAbstractCollection</code> at extension <code>cockpit</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public CockpitObjectSpecialCollectionModel(final CockpitSpecialCollectionType _collectionType, final ItemModel _owner, final String _qualifier)
	{
		super();
		setCollectionType(_collectionType);
		setOwner(_owner);
		setQualifier(_qualifier);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CockpitObjectSpecialCollection.collectionType</code> attribute defined at extension <code>cockpit</code>. 
	 * @return the collectionType
	 */
	@Accessor(qualifier = "collectionType", type = Accessor.Type.GETTER)
	public CockpitSpecialCollectionType getCollectionType()
	{
		return getPersistenceContext().getPropertyValue(COLLECTIONTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>CockpitObjectSpecialCollection.collectionType</code> attribute defined at extension <code>cockpit</code>. 
	 *  
	 * @param value the collectionType
	 */
	@Accessor(qualifier = "collectionType", type = Accessor.Type.SETTER)
	public void setCollectionType(final CockpitSpecialCollectionType value)
	{
		getPersistenceContext().setPropertyValue(COLLECTIONTYPE, value);
	}
	
}
