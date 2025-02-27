/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commons.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum documentTypeEnum declared at extension commons.
 */
public enum DocumentTypeEnum implements HybrisEnumValue
{
	/**
	 * Generated enum value for documentTypeEnum.pdf declared at extension commons.
	 */
	PDF("pdf"),
	/**
	 * Generated enum value for documentTypeEnum.xml declared at extension commons.
	 */
	XML("xml"),
	/**
	 * Generated enum value for documentTypeEnum.text declared at extension commons.
	 */
	TEXT("text");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "documentTypeEnum";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "DocumentTypeEnum";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private DocumentTypeEnum(final String code)
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
