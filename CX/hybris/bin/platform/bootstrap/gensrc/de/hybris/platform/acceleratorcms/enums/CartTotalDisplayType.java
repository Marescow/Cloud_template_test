/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorcms.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum CartTotalDisplayType declared at extension acceleratorcms.
 * <p/>
 * This type is intended to allow configuration of the Total displayed on the MiniCart.
 */
public enum CartTotalDisplayType implements HybrisEnumValue
{
	/**
	 * Generated enum value for CartTotalDisplayType.SUBTOTAL declared at extension acceleratorcms.
	 */
	SUBTOTAL("SUBTOTAL"),
	/**
	 * Generated enum value for CartTotalDisplayType.TOTAL_WITHOUT_DELIVERY declared at extension acceleratorcms.
	 */
	TOTAL_WITHOUT_DELIVERY("TOTAL_WITHOUT_DELIVERY"),
	/**
	 * Generated enum value for CartTotalDisplayType.TOTAL declared at extension acceleratorcms.
	 */
	TOTAL("TOTAL");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "CartTotalDisplayType";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "CartTotalDisplayType";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private CartTotalDisplayType(final String code)
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
