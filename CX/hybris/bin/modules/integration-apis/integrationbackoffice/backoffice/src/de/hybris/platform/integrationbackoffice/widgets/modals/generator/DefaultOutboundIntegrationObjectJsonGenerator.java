/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import de.hybris.platform.integrationbackoffice.widgets.modeling.services.ReadService;
import de.hybris.platform.integrationservices.config.ReadOnlyAttributesConfiguration;
import de.hybris.platform.integrationservices.model.DescriptorFactory;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;

import java.util.Collection;

/**
 * A generator implementation that creates JSON payload examples expected for an integration object in outbound
 * communications.
 */
public class DefaultOutboundIntegrationObjectJsonGenerator extends DefaultAbstractIntegrationObjectJsonGenerator
{
	/**
	 * Instantiates this generator with required dependencies. This constructor is outdated and should not be used.
	 * Use {@link #DefaultOutboundIntegrationObjectJsonGenerator(DescriptorFactory)} instead.
	 *
	 * @param readService   a service to read metadata for the integration object attribute models.
	 * @param configuration configured attributes that should not be set, i.e. they are read-only, in integration
	 *                      objects.
	 */
	public DefaultOutboundIntegrationObjectJsonGenerator(final ReadService readService,
	                                                     final ReadOnlyAttributesConfiguration configuration)
	{
		this(null);
	}

	/**
	 * Instantiates this generator with required dependencies.
	 *
	 * @param factory a factory to use for converting an integration object model into an integration object
	 *                descriptor.
	 */
	public DefaultOutboundIntegrationObjectJsonGenerator(final DescriptorFactory factory)
	{
		super(factory);
	}

	@Override
	Collection<TypeAttributeDescriptor> selectAttributes(final TypeDescriptor item)
	{
		return item.getAttributes();
	}
}
