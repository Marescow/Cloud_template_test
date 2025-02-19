/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.events;

import de.hybris.platform.commerceservices.event.VerificationTokenCreatedEvent;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;



/**
 * Listener for "Verification Token created" functionality event.
 */
public class VerificationTokenCreatedEventListener extends AbstractEventListener<VerificationTokenCreatedEvent>
{
	private static final Logger LOG = LoggerFactory.getLogger(VerificationTokenCreatedEventListener.class);

	private static final String SPLIT_STRING = "-";
	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	private Map<String, String> sendVerificationTokenProcessMap;

	@Override
	protected void onEvent(final VerificationTokenCreatedEvent event)
	{
		String processKey = event.getPurpose();
		String processName = sendVerificationTokenProcessMap.get(processKey);

		if (processName != null)
		{
			final SendVerificationTokenProcessModel sendVerificationTokenProcessModel = (SendVerificationTokenProcessModel) getBusinessProcessService()
					.createProcess(processKey + SPLIT_STRING + processName + SPLIT_STRING + System.currentTimeMillis(),
							processName);
			sendVerificationTokenProcessModel.setSite(event.getSite());
			sendVerificationTokenProcessModel.setLanguage(event.getLanguage());
			sendVerificationTokenProcessModel.setStore(event.getBaseStore());
			sendVerificationTokenProcessModel.setTarget(event.getTarget());
			sendVerificationTokenProcessModel.setTokenCode(event.getTokenCode());
			sendVerificationTokenProcessModel.setPurpose(event.getPurpose() != null ? event.getPurpose().toLowerCase(Locale.ENGLISH) : "");
			getModelService().save(sendVerificationTokenProcessModel);
			getBusinessProcessService().startProcess(sendVerificationTokenProcessModel);
		} else {
			LOG.warn("Could not find send verification token process by purpose: {}", event.getPurpose());
		}
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	public Map<String, String> getSendVerificationTokenProcessMap()
	{
		return sendVerificationTokenProcessMap;
	}

	public void setSendVerificationTokenProcessMap(final Map<String, String> sendVerificationTokenProcessMap)
	{
		this.sendVerificationTokenProcessMap = sendVerificationTokenProcessMap;
	}
}