/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationservices.jalo;

import de.hybris.platform.catalog.jalo.classification.ClassAttributeAssignment;
import de.hybris.platform.integrationservices.constants.IntegrationservicesConstants;
import de.hybris.platform.integrationservices.jalo.AbstractIntegrationObjectItemAttribute;
import de.hybris.platform.integrationservices.jalo.IntegrationObjectItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem IntegrationObjectItemClassificationAttribute}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedIntegrationObjectItemClassificationAttribute extends AbstractIntegrationObjectItemAttribute
{
	/** Qualifier of the <code>IntegrationObjectItemClassificationAttribute.classAttributeAssignment</code> attribute **/
	public static final String CLASSATTRIBUTEASSIGNMENT = "classAttributeAssignment";
	/** Qualifier of the <code>IntegrationObjectItemClassificationAttribute.integrationObjectItem</code> attribute **/
	public static final String INTEGRATIONOBJECTITEM = "integrationObjectItem";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n INTEGRATIONOBJECTITEM's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedIntegrationObjectItemClassificationAttribute> INTEGRATIONOBJECTITEMHANDLER = new BidirectionalOneToManyHandler<GeneratedIntegrationObjectItemClassificationAttribute>(
	IntegrationservicesConstants.TC.INTEGRATIONOBJECTITEMCLASSIFICATIONATTRIBUTE,
	false,
	"integrationObjectItem",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractIntegrationObjectItemAttribute.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CLASSATTRIBUTEASSIGNMENT, AttributeMode.INITIAL);
		tmp.put(INTEGRATIONOBJECTITEM, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IntegrationObjectItemClassificationAttribute.classAttributeAssignment</code> attribute.
	 * @return the classAttributeAssignment
	 */
	public ClassAttributeAssignment getClassAttributeAssignment(final SessionContext ctx)
	{
		return (ClassAttributeAssignment)getProperty( ctx, CLASSATTRIBUTEASSIGNMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IntegrationObjectItemClassificationAttribute.classAttributeAssignment</code> attribute.
	 * @return the classAttributeAssignment
	 */
	public ClassAttributeAssignment getClassAttributeAssignment()
	{
		return getClassAttributeAssignment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IntegrationObjectItemClassificationAttribute.classAttributeAssignment</code> attribute. 
	 * @param value the classAttributeAssignment
	 */
	public void setClassAttributeAssignment(final SessionContext ctx, final ClassAttributeAssignment value)
	{
		setProperty(ctx, CLASSATTRIBUTEASSIGNMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IntegrationObjectItemClassificationAttribute.classAttributeAssignment</code> attribute. 
	 * @param value the classAttributeAssignment
	 */
	public void setClassAttributeAssignment(final ClassAttributeAssignment value)
	{
		setClassAttributeAssignment( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		INTEGRATIONOBJECTITEMHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IntegrationObjectItemClassificationAttribute.integrationObjectItem</code> attribute.
	 * @return the integrationObjectItem
	 */
	public IntegrationObjectItem getIntegrationObjectItem(final SessionContext ctx)
	{
		return (IntegrationObjectItem)getProperty( ctx, INTEGRATIONOBJECTITEM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IntegrationObjectItemClassificationAttribute.integrationObjectItem</code> attribute.
	 * @return the integrationObjectItem
	 */
	public IntegrationObjectItem getIntegrationObjectItem()
	{
		return getIntegrationObjectItem( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IntegrationObjectItemClassificationAttribute.integrationObjectItem</code> attribute. 
	 * @param value the integrationObjectItem
	 */
	public void setIntegrationObjectItem(final SessionContext ctx, final IntegrationObjectItem value)
	{
		INTEGRATIONOBJECTITEMHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IntegrationObjectItemClassificationAttribute.integrationObjectItem</code> attribute. 
	 * @param value the integrationObjectItem
	 */
	public void setIntegrationObjectItem(final IntegrationObjectItem value)
	{
		setIntegrationObjectItem( getSession().getSessionContext(), value );
	}
	
}
