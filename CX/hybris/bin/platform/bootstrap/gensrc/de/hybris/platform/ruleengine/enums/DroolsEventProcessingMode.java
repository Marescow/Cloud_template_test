/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ruleengine.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum DroolsEventProcessingMode declared at extension ruleengine.
 */
public enum DroolsEventProcessingMode implements HybrisEnumValue
{
	/**
	 * Generated enum value for DroolsEventProcessingMode.STREAM declared at extension ruleengine.
	 */
	STREAM("STREAM"),
	/**
	 * Generated enum value for DroolsEventProcessingMode.CLOUD declared at extension ruleengine.
	 */
	CLOUD("CLOUD");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "DroolsEventProcessingMode";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "DroolsEventProcessingMode";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private DroolsEventProcessingMode(final String code)
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
