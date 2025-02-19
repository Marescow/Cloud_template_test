/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.cronjob;

import de.hybris.platform.acceleratorservices.email.dao.impl.DefaultEmailAddressDao;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * A Cron Job to clean up emailAddress.
 */
public class EmailAddressCleanUpJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(EmailAddressCleanUpJob.class);
	private DefaultEmailAddressDao emailAddressDao;

	@Override
	public PerformResult perform(final CronJobModel job)
	{
		try
		{
			final List<EmailAddressModel> allEmails = this.emailAddressDao.getAllEmailAddress();
			for (final EmailAddressModel emailAddress : allEmails)
			{
				//remove email address
				getModelService().remove(emailAddress);
			}
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		catch (RuntimeException e)
		{
			LOG.error("Exception occurred during emailAddress cleanup", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
		}
	}

	@Override
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ModelService getModelService()
	{
		return this.modelService;
	}

	public DefaultEmailAddressDao getEmailAddressDao()
	{
		return this.emailAddressDao;
	}

	public void setEmailAddressDao(final DefaultEmailAddressDao emailAddressDao)
	{
		this.emailAddressDao = emailAddressDao;
	}
}
