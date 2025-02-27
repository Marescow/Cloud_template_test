/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.subscriptionservices.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum TermOfServiceFrequency declared at extension subscriptionservices.
 */
public enum TermOfServiceFrequency implements HybrisEnumValue
{
	/**
	 * Generated enum value for TermOfServiceFrequency.none declared at extension subscriptionservices.
	 */
	NONE("none"),
	/**
	 * Generated enum value for TermOfServiceFrequency.monthly declared at extension subscriptionservices.
	 */
	MONTHLY("monthly"),
	/**
	 * Generated enum value for TermOfServiceFrequency.quarterly declared at extension subscriptionservices.
	 */
	QUARTERLY("quarterly"),
	/**
	 * Generated enum value for TermOfServiceFrequency.annually declared at extension subscriptionservices.
	 */
	ANNUALLY("annually");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "TermOfServiceFrequency";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "TermOfServiceFrequency";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private TermOfServiceFrequency(final String code)
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
