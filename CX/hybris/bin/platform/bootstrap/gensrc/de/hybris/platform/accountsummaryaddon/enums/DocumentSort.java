/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum DocumentSort declared at extension accountsummaryaddon.
 */
public enum DocumentSort implements HybrisEnumValue
{
	/**
	 * Generated enum value for DocumentSort.documentNumber declared at extension accountsummaryaddon.
	 */
	DOCUMENTNUMBER("documentNumber"),
	/**
	 * Generated enum value for DocumentSort.documentType declared at extension accountsummaryaddon.
	 */
	DOCUMENTTYPE("documentType"),
	/**
	 * Generated enum value for DocumentSort.status declared at extension accountsummaryaddon.
	 */
	STATUS("status"),
	/**
	 * Generated enum value for DocumentSort.date declared at extension accountsummaryaddon.
	 */
	DATE("date"),
	/**
	 * Generated enum value for DocumentSort.dueDate declared at extension accountsummaryaddon.
	 */
	DUEDATE("dueDate"),
	/**
	 * Generated enum value for DocumentSort.amount declared at extension accountsummaryaddon.
	 */
	AMOUNT("amount"),
	/**
	 * Generated enum value for DocumentSort.openAmount declared at extension accountsummaryaddon.
	 */
	OPENAMOUNT("openAmount");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "DocumentSort";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "DocumentSort";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private DocumentSort(final String code)
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
