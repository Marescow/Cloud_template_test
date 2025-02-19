/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum DocumentPayableOrUsable declared at extension accountsummaryaddon.
 */
public enum DocumentPayableOrUsable implements HybrisEnumValue
{
	/**
	 * Generated enum value for DocumentPayableOrUsable.PAY declared at extension accountsummaryaddon.
	 */
	PAY("PAY"),
	/**
	 * Generated enum value for DocumentPayableOrUsable.USE declared at extension accountsummaryaddon.
	 */
	USE("USE");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "DocumentPayableOrUsable";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "DocumentPayableOrUsable";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private DocumentPayableOrUsable(final String code)
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
