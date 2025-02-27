/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.advancedsavedquery.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum AdvancedQueryComparatorEnum declared at extension advancedsavedquery.
 */
public enum AdvancedQueryComparatorEnum implements HybrisEnumValue
{
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.equals declared at extension advancedsavedquery.
	 */
	EQUALS("equals"),
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.contains declared at extension advancedsavedquery.
	 */
	CONTAINS("contains"),
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.gt declared at extension advancedsavedquery.
	 */
	GT("gt"),
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.gtandequals declared at extension advancedsavedquery.
	 */
	GTANDEQUALS("gtandequals"),
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.lt declared at extension advancedsavedquery.
	 */
	LT("lt"),
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.ltandequals declared at extension advancedsavedquery.
	 */
	LTANDEQUALS("ltandequals"),
	/**
	 * Generated enum value for AdvancedQueryComparatorEnum.startwidth declared at extension advancedsavedquery.
	 */
	STARTWIDTH("startwidth");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "AdvancedQueryComparatorEnum";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "AdvancedQueryComparatorEnum";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private AdvancedQueryComparatorEnum(final String code)
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
