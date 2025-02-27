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
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorcms.jalo.components.LogoComponent LogoComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedLogoComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>LogoComponent.logo</code> attribute **/
	public static final String LOGO = "logo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LOGO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LogoComponent.logo</code> attribute.
	 * @return the logo - It is a banner component to be displayed.
	 */
	public SimpleBannerComponent getLogo(final SessionContext ctx)
	{
		return (SimpleBannerComponent)getProperty( ctx, LOGO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LogoComponent.logo</code> attribute.
	 * @return the logo - It is a banner component to be displayed.
	 */
	public SimpleBannerComponent getLogo()
	{
		return getLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LogoComponent.logo</code> attribute. 
	 * @param value the logo - It is a banner component to be displayed.
	 */
	public void setLogo(final SessionContext ctx, final SimpleBannerComponent value)
	{
		setProperty(ctx, LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LogoComponent.logo</code> attribute. 
	 * @param value the logo - It is a banner component to be displayed.
	 */
	public void setLogo(final SimpleBannerComponent value)
	{
		setLogo( getSession().getSessionContext(), value );
	}
	
}
