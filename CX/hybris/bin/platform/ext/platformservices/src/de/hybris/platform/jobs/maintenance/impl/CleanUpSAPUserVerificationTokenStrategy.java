/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.jobs.maintenance.impl;

import de.hybris.platform.core.model.user.SAPUserVerificationTokenModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.jobs.maintenance.MaintenanceCleanupStrategy;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * CleanUp SAP Verification Token strategy. This strategy looks for expired records in SAPUserVerificationToken and removes them.
 */
public class CleanUpSAPUserVerificationTokenStrategy
		implements MaintenanceCleanupStrategy<SAPUserVerificationTokenModel, CronJobModel>
{
	private static final Logger LOG = LoggerFactory.getLogger(CleanUpSAPUserVerificationTokenStrategy.class);
	private static final String FETCH_OUTDATED_TOKENS_QUERY =
			"select {" + SAPUserVerificationTokenModel.PK + "} from {" + SAPUserVerificationTokenModel._TYPECODE + "} " + "where {"
					+ SAPUserVerificationTokenModel.EXPIRATIONTIME + "} <= ?time ";
	private final ModelService modelService;

	public CleanUpSAPUserVerificationTokenStrategy(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	public FlexibleSearchQuery createFetchQuery(final CronJobModel cjm)
	{

		final Date currentDateTime = DateTime.now().toDate();
		final var flexibleSearchQuery = new FlexibleSearchQuery(FETCH_OUTDATED_TOKENS_QUERY, Collections.singletonMap("time", currentDateTime));
		flexibleSearchQuery.setResultClassList(List.of(SAPUserVerificationTokenModel.class));
		return flexibleSearchQuery;
	}

	@Override
	public void process(final List<SAPUserVerificationTokenModel> elements)
	{
		if (!elements.isEmpty())
		{
			LOG.info("Removing SAPUserVerificationToken: {} tokens.", elements.size());
			modelService.removeAll(elements);
		}
	}
}
