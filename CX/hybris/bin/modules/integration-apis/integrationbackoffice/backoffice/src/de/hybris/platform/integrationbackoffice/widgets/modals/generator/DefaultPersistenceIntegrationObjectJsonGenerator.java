/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationbackoffice.widgets.modeling.services.ReadService;
import de.hybris.platform.integrationservices.config.ReadOnlyAttributesConfiguration;
import de.hybris.platform.integrationservices.model.DescriptorFactory;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.integrationservices.util.ApplicationBeans;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A generator implementation that creates samples of JSON payload for using in POST requests against an integration
 * object REST endpoint.
 */
public class DefaultPersistenceIntegrationObjectJsonGenerator extends DefaultAbstractIntegrationObjectJsonGenerator
{
	private ModelService modelService;
	private final ReadOnlyAttributesConfiguration configuration;

	/**
	 * Instantiates this generator with required dependencies. This constructor is outdated and should not be used.
	 * Use {@link #DefaultPersistenceIntegrationObjectJsonGenerator(DescriptorFactory, ModelService, ReadOnlyAttributesConfiguration)}
	 *
	 * @param readService   a service to read metadata for the integration object attribute models.
	 * @param configuration configured attributes that should not be set, i.e. they are read-only, in integration
	 *                      objects.
	 */
	public DefaultPersistenceIntegrationObjectJsonGenerator(final ReadService readService,
	                                                        final ReadOnlyAttributesConfiguration configuration)
	{
		this(null, null, configuration);
	}

	/**
	 * Instantiates this generator with required dependencies.
	 *
	 * @param factory a factory to use for converting an integration object model into an integration object
	 *                descriptor.
	 * @param config  configured attributes that should not be set, i.e. they are read-only, in integration
	 *                objects.
	 */
	public DefaultPersistenceIntegrationObjectJsonGenerator(final DescriptorFactory factory,
	                                                        final ModelService service,
	                                                        final ReadOnlyAttributesConfiguration config)
	{
		super(factory);
		modelService = service;
		configuration = config;
	}

	@Override
	Collection<TypeAttributeDescriptor> selectAttributes(final TypeDescriptor type)
	{
		final ItemModel sampleItem = createItem(type);
		final Set<String> excludedAttributes = configuration.getReadOnlyAttributes();
		return type.getAttributes()
		           .stream()
		           .filter(Predicate.not(a -> excludedAttributes.contains(a.getAttributeName())))
		           .filter(a -> !a.isPrimitive() || a.isSettable(sampleItem))
		           .collect(Collectors.toSet());
	}

	private ItemModel createItem(final TypeDescriptor type)
	{
		return getModelService().create(type.getTypeCode());
	}

	private ModelService getModelService()
	{
		if (modelService == null)
		{
			modelService = ApplicationBeans.getBean("modelService", ModelService.class);
		}
		return modelService;
	}
}
