/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.apiregistryservices.strategies.impl;

import de.hybris.platform.apiregistryservices.model.AbstractDestinationModel;
import de.hybris.platform.apiregistryservices.model.ConsumedDestinationModel;
import de.hybris.platform.apiregistryservices.services.DestinationService;
import de.hybris.platform.apiregistryservices.strategies.ConsumedDestinationLocatorStrategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;


/**
 *  Default implementation of interface {@link ConsumedDestinationLocatorStrategy}:
 *  Find the {@link ConsumedDestinationModel} by client type name
 */
public class DefaultConsumedDestinationLocatorStrategy implements ConsumedDestinationLocatorStrategy
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultConsumedDestinationLocatorStrategy.class);
	public static final String CLIENT_CLASS_NAME = "clientClassName";

	private DestinationService<AbstractDestinationModel> destinationService;

	@Override
	public ConsumedDestinationModel lookup(final String clientTypeName)
	{
		final List<AbstractDestinationModel> destinations = getDestinationService().getAllDestinations().stream()
				.filter(ConsumedDestinationModel.class::isInstance)
				.filter(dest -> dest.getAdditionalProperties().containsKey(CLIENT_CLASS_NAME)
						&& dest.getAdditionalProperties().get(CLIENT_CLASS_NAME).equals(clientTypeName))
				.toList();

		if (destinations.isEmpty())
		{
			LOG.warn("Failed to find consumed destination for the given client type name [{}].", clientTypeName);
			return null;
		}

		if (destinations.size() > 1)
		{
			LOG.warn("Multiple consumed destinations found for the given client type name [{}].", clientTypeName);
			return null;
		}

		return (ConsumedDestinationModel) destinations.get(0);
	}

	protected DestinationService<AbstractDestinationModel> getDestinationService()
	{
		return destinationService;
	}

	@Required
	public void setDestinationService(final DestinationService<AbstractDestinationModel> destinationService)
	{
		this.destinationService = destinationService;
	}
}
