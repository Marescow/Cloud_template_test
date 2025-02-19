/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.process.email.actions;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implementation of {@link GenerateEmailAction} responsible for creating an email message for target email of Verification
 */
public class GenerateVerificationTokenEmailAction extends GenerateEmailAction
{

	private static final Logger LOG = LoggerFactory.getLogger(GenerateVerificationTokenEmailAction.class);

	private EmailService emailService;

	@Override
	public Transition executeAction(final BusinessProcessModel businessProcessModel) throws RetryLaterException
	{
		final Transition transition = super.executeAction(businessProcessModel);

		if (transition == Transition.OK)
		{

			final SendVerificationTokenProcessModel sendVerificationModel = (SendVerificationTokenProcessModel) businessProcessModel;

			if (StringUtils.isBlank(sendVerificationModel.getTarget()))
			{
				LOG.warn("There are no target email will be sent.");
				return Transition.NOK;
			}

			final EmailAddressModel targetEmail = emailService.getOrCreateEmailAddressForEmail(sendVerificationModel.getTarget(), "");
			final List<EmailAddressModel> recipients = new ArrayList<>();
			recipients.add(targetEmail);

			// Get the email message (there should only be one!) and set recipients
			final EmailMessageModel emailMessage = businessProcessModel.getEmails().iterator().next();
			emailMessage.setToAddresses(recipients);
			modelService.save(emailMessage);
		}

		return transition;

	}

	public EmailService getEmailService()
	{
		return emailService;
	}

	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}
}