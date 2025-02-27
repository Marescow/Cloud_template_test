/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorcms.jalo.actions;

import de.hybris.platform.acceleratorcms.constants.AcceleratorCmsConstants;
import de.hybris.platform.cms2.jalo.contents.components.AbstractCMSComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.util.Utilities;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorcms.jalo.actions.AbstractCMSAction AbstractCMSAction}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedAbstractCMSAction extends SimpleCMSComponent
{
	/** Qualifier of the <code>AbstractCMSAction.url</code> attribute **/
	public static final String URL = "url";
	/** Qualifier of the <code>AbstractCMSAction.components</code> attribute **/
	public static final String COMPONENTS = "components";
	/** Relation ordering override parameter constants for CmsActionsForCmsComponents from ((acceleratorcms))*/
	protected static String CMSACTIONSFORCMSCOMPONENTS_SRC_ORDERED = "relation.CmsActionsForCmsComponents.source.ordered";
	protected static String CMSACTIONSFORCMSCOMPONENTS_TGT_ORDERED = "relation.CmsActionsForCmsComponents.target.ordered";
	/** Relation disable markmodifed parameter constants for CmsActionsForCmsComponents from ((acceleratorcms))*/
	protected static String CMSACTIONSFORCMSCOMPONENTS_MARKMODIFIED = "relation.CmsActionsForCmsComponents.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(URL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractCMSAction.components</code> attribute.
	 * @return the components
	 */
	public Collection<AbstractCMSComponent> getComponents(final SessionContext ctx)
	{
		final List<AbstractCMSComponent> items = getLinkedItems( 
			ctx,
			false,
			AcceleratorCmsConstants.Relations.CMSACTIONSFORCMSCOMPONENTS,
			"AbstractCMSComponent",
			null,
			Utilities.getRelationOrderingOverride(CMSACTIONSFORCMSCOMPONENTS_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractCMSAction.components</code> attribute.
	 * @return the components
	 */
	public Collection<AbstractCMSComponent> getComponents()
	{
		return getComponents( getSession().getSessionContext() );
	}
	
	public long getComponentsCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			AcceleratorCmsConstants.Relations.CMSACTIONSFORCMSCOMPONENTS,
			"AbstractCMSComponent",
			null
		);
	}
	
	public long getComponentsCount()
	{
		return getComponentsCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractCMSAction.components</code> attribute. 
	 * @param value the components
	 */
	public void setComponents(final SessionContext ctx, final Collection<AbstractCMSComponent> value)
	{
		setLinkedItems( 
			ctx,
			false,
			AcceleratorCmsConstants.Relations.CMSACTIONSFORCMSCOMPONENTS,
			null,
			value,
			Utilities.getRelationOrderingOverride(CMSACTIONSFORCMSCOMPONENTS_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(CMSACTIONSFORCMSCOMPONENTS_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractCMSAction.components</code> attribute. 
	 * @param value the components
	 */
	public void setComponents(final Collection<AbstractCMSComponent> value)
	{
		setComponents( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to components. 
	 * @param value the item to add to components
	 */
	public void addToComponents(final SessionContext ctx, final AbstractCMSComponent value)
	{
		addLinkedItems( 
			ctx,
			false,
			AcceleratorCmsConstants.Relations.CMSACTIONSFORCMSCOMPONENTS,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(CMSACTIONSFORCMSCOMPONENTS_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(CMSACTIONSFORCMSCOMPONENTS_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to components. 
	 * @param value the item to add to components
	 */
	public void addToComponents(final AbstractCMSComponent value)
	{
		addToComponents( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from components. 
	 * @param value the item to remove from components
	 */
	public void removeFromComponents(final SessionContext ctx, final AbstractCMSComponent value)
	{
		removeLinkedItems( 
			ctx,
			false,
			AcceleratorCmsConstants.Relations.CMSACTIONSFORCMSCOMPONENTS,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(CMSACTIONSFORCMSCOMPONENTS_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(CMSACTIONSFORCMSCOMPONENTS_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from components. 
	 * @param value the item to remove from components
	 */
	public void removeFromComponents(final AbstractCMSComponent value)
	{
		removeFromComponents( getSession().getSessionContext(), value );
	}
	
	/**
	 * @deprecated since 2011, use {@link Utilities#getMarkModifiedOverride(de.hybris.platform.jalo.type.RelationType)
	 */
	@Override
	@Deprecated(since = "2105", forRemoval = true)
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("AbstractCMSComponent");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(CMSACTIONSFORCMSCOMPONENTS_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractCMSAction.url</code> attribute.
	 * @return the url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractCMSAction.url</code> attribute.
	 * @return the url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractCMSAction.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractCMSAction.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
}
