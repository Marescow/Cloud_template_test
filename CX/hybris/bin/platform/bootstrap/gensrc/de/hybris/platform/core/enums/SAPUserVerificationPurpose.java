/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.core.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum SAPUserVerificationPurpose declared at extension core.
 */
public enum SAPUserVerificationPurpose implements HybrisEnumValue
{
	/**
	 * Generated enum value for SAPUserVerificationPurpose.LOGIN declared at extension core.
	 */
	LOGIN("LOGIN");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "SAPUserVerificationPurpose";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "SAPUserVerificationPurpose";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private SAPUserVerificationPurpose(final String code)
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
