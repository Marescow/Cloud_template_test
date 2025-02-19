/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.jalo;

import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.commerceservices.constants.CommerceServicesConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.processengine.jalo.BusinessProcess;
import de.hybris.platform.store.BaseStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.processengine.jalo.BusinessProcess SendVerificationTokenProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedSendVerificationTokenProcess extends BusinessProcess
{
	/** Qualifier of the <code>SendVerificationTokenProcess.site</code> attribute **/
	public static final String SITE = "site";
	/** Qualifier of the <code>SendVerificationTokenProcess.store</code> attribute **/
	public static final String STORE = "store";
	/** Qualifier of the <code>SendVerificationTokenProcess.language</code> attribute **/
	public static final String LANGUAGE = "language";
	/** Qualifier of the <code>SendVerificationTokenProcess.tokenCode</code> attribute **/
	public static final String TOKENCODE = "tokenCode";
	/** Qualifier of the <code>SendVerificationTokenProcess.target</code> attribute **/
	public static final String TARGET = "target";
	/** Qualifier of the <code>SendVerificationTokenProcess.purpose</code> attribute **/
	public static final String PURPOSE = "purpose";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(BusinessProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SITE, AttributeMode.INITIAL);
		tmp.put(STORE, AttributeMode.INITIAL);
		tmp.put(LANGUAGE, AttributeMode.INITIAL);
		tmp.put(TOKENCODE, AttributeMode.INITIAL);
		tmp.put(TARGET, AttributeMode.INITIAL);
		tmp.put(PURPOSE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.language</code> attribute.
	 * @return the language - Language that will be used in the process.
	 */
	public Language getLanguage(final SessionContext ctx)
	{
		return (Language)getProperty( ctx, LANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.language</code> attribute.
	 * @return the language - Language that will be used in the process.
	 */
	public Language getLanguage()
	{
		return getLanguage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.language</code> attribute. 
	 * @param value the language - Language that will be used in the process.
	 */
	public void setLanguage(final SessionContext ctx, final Language value)
	{
		setProperty(ctx, LANGUAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.language</code> attribute. 
	 * @param value the language - Language that will be used in the process.
	 */
	public void setLanguage(final Language value)
	{
		setLanguage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.purpose</code> attribute.
	 * @return the purpose - Purpose for which the verification token is requested.
	 */
	public String getPurpose(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PURPOSE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.purpose</code> attribute.
	 * @return the purpose - Purpose for which the verification token is requested.
	 */
	public String getPurpose()
	{
		return getPurpose( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.purpose</code> attribute. 
	 * @param value the purpose - Purpose for which the verification token is requested.
	 */
	public void setPurpose(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PURPOSE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.purpose</code> attribute. 
	 * @param value the purpose - Purpose for which the verification token is requested.
	 */
	public void setPurpose(final String value)
	{
		setPurpose( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.site</code> attribute.
	 * @return the site - Attribute contains base site object that will be used in the process.
	 */
	public BaseSite getSite(final SessionContext ctx)
	{
		return (BaseSite)getProperty( ctx, SITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.site</code> attribute.
	 * @return the site - Attribute contains base site object that will be used in the process.
	 */
	public BaseSite getSite()
	{
		return getSite( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.site</code> attribute. 
	 * @param value the site - Attribute contains base site object that will be used in the process.
	 */
	public void setSite(final SessionContext ctx, final BaseSite value)
	{
		setProperty(ctx, SITE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.site</code> attribute. 
	 * @param value the site - Attribute contains base site object that will be used in the process.
	 */
	public void setSite(final BaseSite value)
	{
		setSite( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.store</code> attribute.
	 * @return the store - Attribute contains base store object that will be used in the process.
	 */
	public BaseStore getStore(final SessionContext ctx)
	{
		return (BaseStore)getProperty( ctx, STORE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.store</code> attribute.
	 * @return the store - Attribute contains base store object that will be used in the process.
	 */
	public BaseStore getStore()
	{
		return getStore( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.store</code> attribute. 
	 * @param value the store - Attribute contains base store object that will be used in the process.
	 */
	public void setStore(final SessionContext ctx, final BaseStore value)
	{
		setProperty(ctx, STORE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.store</code> attribute. 
	 * @param value the store - Attribute contains base store object that will be used in the process.
	 */
	public void setStore(final BaseStore value)
	{
		setStore( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.target</code> attribute.
	 * @return the target - Target to receive verification token code.
	 */
	public String getTarget(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TARGET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.target</code> attribute.
	 * @return the target - Target to receive verification token code.
	 */
	public String getTarget()
	{
		return getTarget( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.target</code> attribute. 
	 * @param value the target - Target to receive verification token code.
	 */
	public void setTarget(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TARGET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.target</code> attribute. 
	 * @param value the target - Target to receive verification token code.
	 */
	public void setTarget(final String value)
	{
		setTarget( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.tokenCode</code> attribute.
	 * @return the tokenCode - Verification token code that will be sent to target user.
	 */
	public String getTokenCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TOKENCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.tokenCode</code> attribute.
	 * @return the tokenCode - Verification token code that will be sent to target user.
	 */
	public String getTokenCode()
	{
		return getTokenCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.tokenCode</code> attribute. 
	 * @param value the tokenCode - Verification token code that will be sent to target user.
	 */
	public void setTokenCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TOKENCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SendVerificationTokenProcess.tokenCode</code> attribute. 
	 * @param value the tokenCode - Verification token code that will be sent to target user.
	 */
	public void setTokenCode(final String value)
	{
		setTokenCode( getSession().getSessionContext(), value );
	}
	
}
