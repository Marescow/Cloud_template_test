/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorcms.jalo.components;

import de.hybris.platform.acceleratorcms.constants.AcceleratorCmsConstants;
import de.hybris.platform.cms2lib.components.AbstractBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorcms.jalo.components.DynamicBannerComponent DynamicBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDynamicBannerComponent extends AbstractBannerComponent
{
	/** Qualifier of the <code>DynamicBannerComponent.mediaCodePattern</code> attribute **/
	public static final String MEDIACODEPATTERN = "mediaCodePattern";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIACODEPATTERN, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicBannerComponent.mediaCodePattern</code> attribute.
	 * @return the mediaCodePattern - Media code pattern that will be used for evaluation.
	 */
	public String getMediaCodePattern(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MEDIACODEPATTERN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicBannerComponent.mediaCodePattern</code> attribute.
	 * @return the mediaCodePattern - Media code pattern that will be used for evaluation.
	 */
	public String getMediaCodePattern()
	{
		return getMediaCodePattern( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicBannerComponent.mediaCodePattern</code> attribute. 
	 * @param value the mediaCodePattern - Media code pattern that will be used for evaluation.
	 */
	public void setMediaCodePattern(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MEDIACODEPATTERN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicBannerComponent.mediaCodePattern</code> attribute. 
	 * @param value the mediaCodePattern - Media code pattern that will be used for evaluation.
	 */
	public void setMediaCodePattern(final String value)
	{
		setMediaCodePattern( getSession().getSessionContext(), value );
	}
	
}
