/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.catalog.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum CategoryDifferenceMode declared at extension catalog.
 */
public enum CategoryDifferenceMode implements HybrisEnumValue
{
	/**
	 * Generated enum value for CategoryDifferenceMode.category_new declared at extension catalog.
	 */
	CATEGORY_NEW("category_new"),
	/**
	 * Generated enum value for CategoryDifferenceMode.category_removed declared at extension catalog.
	 */
	CATEGORY_REMOVED("category_removed");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "CategoryDifferenceMode";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "CategoryDifferenceMode";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private CategoryDifferenceMode(final String code)
	{
		this.code = code.intern();
	}
	
	
	/**
	 * Gets the code of this enum value.
	 *  
	 * @return code of value
	 */
	@Override
	public String getCode()
	{
		return this.code;
	}
	
	/**
	 * Gets the type this enum value belongs to.
	 *  
	 * @return code of type
	 */
	@Override
	public String getType()
	{
		return SIMPLE_CLASSNAME;
	}
	
}
