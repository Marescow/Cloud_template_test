/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmscockpit.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum LiveEditVariant declared at extension cmscockpit.
 * <p/>
 * By adding new value here we can introduce new liveedit variants.
 */
public enum LiveEditVariant implements HybrisEnumValue
{
	/**
	 * Generated enum value for LiveEditVariant.QuickEdit declared at extension cmscockpit.
	 */
	QUICKEDIT("QuickEdit");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "LiveEditVariant";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "LiveEditVariant";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private LiveEditVariant(final String code)
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
