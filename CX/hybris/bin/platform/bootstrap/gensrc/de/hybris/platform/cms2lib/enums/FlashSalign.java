/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cms2lib.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum FlashSalign declared at extension cms2lib.
 */
public enum FlashSalign implements HybrisEnumValue
{
	/**
	 * Generated enum value for FlashSalign.l declared at extension cms2lib.
	 */
	L("l"),
	/**
	 * Generated enum value for FlashSalign.r declared at extension cms2lib.
	 */
	R("r"),
	/**
	 * Generated enum value for FlashSalign.t declared at extension cms2lib.
	 */
	T("t"),
	/**
	 * Generated enum value for FlashSalign.tl declared at extension cms2lib.
	 */
	TL("tl"),
	/**
	 * Generated enum value for FlashSalign.tr declared at extension cms2lib.
	 */
	TR("tr");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "FlashSalign";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "FlashSalign";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private FlashSalign(final String code)
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
