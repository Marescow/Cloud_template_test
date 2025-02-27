/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.orderprocessing.jalo;

import de.hybris.platform.acceleratorservices.constants.AcceleratorServicesConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.ordermodify.jalo.OrderModificationRecordEntry;
import de.hybris.platform.orderprocessing.jalo.OrderProcess;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorservices.orderprocessing.jalo.OrderModificationProcess OrderModificationProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedOrderModificationProcess extends OrderProcess
{
	/** Qualifier of the <code>OrderModificationProcess.orderModificationRecordEntry</code> attribute **/
	public static final String ORDERMODIFICATIONRECORDENTRY = "orderModificationRecordEntry";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(OrderProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ORDERMODIFICATIONRECORDENTRY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderModificationProcess.orderModificationRecordEntry</code> attribute.
	 * @return the orderModificationRecordEntry - Object storing order modification details.
	 */
	public OrderModificationRecordEntry getOrderModificationRecordEntry(final SessionContext ctx)
	{
		return (OrderModificationRecordEntry)getProperty( ctx, ORDERMODIFICATIONRECORDENTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderModificationProcess.orderModificationRecordEntry</code> attribute.
	 * @return the orderModificationRecordEntry - Object storing order modification details.
	 */
	public OrderModificationRecordEntry getOrderModificationRecordEntry()
	{
		return getOrderModificationRecordEntry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderModificationProcess.orderModificationRecordEntry</code> attribute. 
	 * @param value the orderModificationRecordEntry - Object storing order modification details.
	 */
	public void setOrderModificationRecordEntry(final SessionContext ctx, final OrderModificationRecordEntry value)
	{
		setProperty(ctx, ORDERMODIFICATIONRECORDENTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderModificationProcess.orderModificationRecordEntry</code> attribute. 
	 * @param value the orderModificationRecordEntry - Object storing order modification details.
	 */
	public void setOrderModificationRecordEntry(final OrderModificationRecordEntry value)
	{
		setOrderModificationRecordEntry( getSession().getSessionContext(), value );
	}
	
}
