/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.core.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum Gender declared at extension core.
 */
public enum Gender implements HybrisEnumValue
{
	/**
	 * Generated enum value for Gender.MALE declared at extension core.
	 */
	MALE("MALE"),
	/**
	 * Generated enum value for Gender.FEMALE declared at extension core.
	 */
	FEMALE("FEMALE");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "Gender";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "Gender";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private Gender(final String code)
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
