/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.process.strategies.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Optional;


/**
 * Strategy to impersonate site and initialize session context from an instance of SendVerificationTokenProcessModel.
 */
public class SendVerificationTokenProcessContextStrategy extends AbstractProcessContextStrategy
{
	@Override
	public BaseSiteModel getCmsSite(final BusinessProcessModel businessProcessModel)
	{
		ServicesUtil.validateParameterNotNull(businessProcessModel, BUSINESS_PROCESS_MUST_NOT_BE_NULL_MSG);

		return Optional.of(businessProcessModel)
				.filter(SendVerificationTokenProcessModel.class::isInstance)
				.map(SendVerificationTokenProcessModel.class::cast)
				.map(SendVerificationTokenProcessModel::getSite)
				.orElse(null);
	}

	@Override
	protected LanguageModel computeLanguage(final BusinessProcessModel businessProcess)
	{
		return Optional.of(businessProcess)
				.filter(SendVerificationTokenProcessModel.class::isInstance)
				.map(SendVerificationTokenProcessModel.class::cast)
				.map(SendVerificationTokenProcessModel::getLanguage)
				.orElse(super.computeLanguage(businessProcess));
	}

	@Override
	protected CustomerModel getCustomer(final BusinessProcessModel businessProcess)
	{
		return null;
	}
}
