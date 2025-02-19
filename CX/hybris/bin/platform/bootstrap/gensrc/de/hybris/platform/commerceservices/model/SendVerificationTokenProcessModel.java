/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.model;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.store.BaseStoreModel;

/**
 * Generated model class for type SendVerificationTokenProcess first defined at extension commerceservices.
 * <p>
 * Process model for sending a verification token.
 */
@SuppressWarnings("all")
public class SendVerificationTokenProcessModel extends BusinessProcessModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "SendVerificationTokenProcess";
	
	/** <i>Generated constant</i> - Attribute key of <code>SendVerificationTokenProcess.site</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String SITE = "site";
	
	/** <i>Generated constant</i> - Attribute key of <code>SendVerificationTokenProcess.store</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String STORE = "store";
	
	/** <i>Generated constant</i> - Attribute key of <code>SendVerificationTokenProcess.language</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String LANGUAGE = "language";
	
	/** <i>Generated constant</i> - Attribute key of <code>SendVerificationTokenProcess.tokenCode</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String TOKENCODE = "tokenCode";
	
	/** <i>Generated constant</i> - Attribute key of <code>SendVerificationTokenProcess.target</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String TARGET = "target";
	
	/** <i>Generated constant</i> - Attribute key of <code>SendVerificationTokenProcess.purpose</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String PURPOSE = "purpose";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public SendVerificationTokenProcessModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public SendVerificationTokenProcessModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _code initial attribute declared by type <code>BusinessProcess</code> at extension <code>processing</code>
	 * @param _processDefinitionName initial attribute declared by type <code>BusinessProcess</code> at extension <code>processing</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public SendVerificationTokenProcessModel(final String _code, final String _processDefinitionName)
	{
		super();
		setCode(_code);
		setProcessDefinitionName(_processDefinitionName);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _code initial attribute declared by type <code>BusinessProcess</code> at extension <code>processing</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _processDefinitionName initial attribute declared by type <code>BusinessProcess</code> at extension <code>processing</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public SendVerificationTokenProcessModel(final String _code, final ItemModel _owner, final String _processDefinitionName)
	{
		super();
		setCode(_code);
		setOwner(_owner);
		setProcessDefinitionName(_processDefinitionName);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.language</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the language - Language that will be used in the process.
	 */
	@Accessor(qualifier = "language", type = Accessor.Type.GETTER)
	public LanguageModel getLanguage()
	{
		return getPersistenceContext().getPropertyValue(LANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.purpose</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the purpose - Purpose for which the verification token is requested.
	 */
	@Accessor(qualifier = "purpose", type = Accessor.Type.GETTER)
	public String getPurpose()
	{
		return getPersistenceContext().getPropertyValue(PURPOSE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.site</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the site - Attribute contains base site object that will be used in the process.
	 */
	@Accessor(qualifier = "site", type = Accessor.Type.GETTER)
	public BaseSiteModel getSite()
	{
		return getPersistenceContext().getPropertyValue(SITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.store</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the store - Attribute contains base store object that will be used in the process.
	 */
	@Accessor(qualifier = "store", type = Accessor.Type.GETTER)
	public BaseStoreModel getStore()
	{
		return getPersistenceContext().getPropertyValue(STORE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.target</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the target - Target to receive verification token code.
	 */
	@Accessor(qualifier = "target", type = Accessor.Type.GETTER)
	public String getTarget()
	{
		return getPersistenceContext().getPropertyValue(TARGET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SendVerificationTokenProcess.tokenCode</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the tokenCode - Verification token code that will be sent to target user.
	 */
	@Accessor(qualifier = "tokenCode", type = Accessor.Type.GETTER)
	public String getTokenCode()
	{
		return getPersistenceContext().getPropertyValue(TOKENCODE);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SendVerificationTokenProcess.language</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the language - Language that will be used in the process.
	 */
	@Accessor(qualifier = "language", type = Accessor.Type.SETTER)
	public void setLanguage(final LanguageModel value)
	{
		getPersistenceContext().setPropertyValue(LANGUAGE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SendVerificationTokenProcess.purpose</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the purpose - Purpose for which the verification token is requested.
	 */
	@Accessor(qualifier = "purpose", type = Accessor.Type.SETTER)
	public void setPurpose(final String value)
	{
		getPersistenceContext().setPropertyValue(PURPOSE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SendVerificationTokenProcess.site</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the site - Attribute contains base site object that will be used in the process.
	 */
	@Accessor(qualifier = "site", type = Accessor.Type.SETTER)
	public void setSite(final BaseSiteModel value)
	{
		getPersistenceContext().setPropertyValue(SITE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SendVerificationTokenProcess.store</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the store - Attribute contains base store object that will be used in the process.
	 */
	@Accessor(qualifier = "store", type = Accessor.Type.SETTER)
	public void setStore(final BaseStoreModel value)
	{
		getPersistenceContext().setPropertyValue(STORE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SendVerificationTokenProcess.target</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the target - Target to receive verification token code.
	 */
	@Accessor(qualifier = "target", type = Accessor.Type.SETTER)
	public void setTarget(final String value)
	{
		getPersistenceContext().setPropertyValue(TARGET, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SendVerificationTokenProcess.tokenCode</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the tokenCode - Verification token code that will be sent to target user.
	 */
	@Accessor(qualifier = "tokenCode", type = Accessor.Type.SETTER)
	public void setTokenCode(final String value)
	{
		getPersistenceContext().setPropertyValue(TOKENCODE, value);
	}
	
}
