/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.process.email.context.impl;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Locale;


/**
 * Velocity context for a verification token email.
 */
public class VerificationTokenEmailContext extends AbstractEmailContext<SendVerificationTokenProcessModel>
{
	private String target;
	private String tokenCode;
	private String purpose;
	private int ttlSeconds;
	private int maxAttempts;

	@Override
	public void init(final SendVerificationTokenProcessModel sendVerificationProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(sendVerificationProcessModel, emailPageModel);
		target = sendVerificationProcessModel.getTarget();
		tokenCode = sendVerificationProcessModel.getTokenCode();
		purpose = sendVerificationProcessModel.getPurpose();
		ttlSeconds = getConfigurationService().getConfiguration().getInt("otp.customer." + purpose.toLowerCase(Locale.getDefault()) +".token.ttlseconds", 0);
		maxAttempts = getConfigurationService().getConfiguration().getInt("otp.customer." + purpose.toLowerCase(Locale.getDefault()) + ".token.max.verification.attempts", 0);
	}

	@Override
	protected BaseSiteModel getSite(final SendVerificationTokenProcessModel sendVerificationProcessModel)
	{
		return sendVerificationProcessModel.getSite();
	}

	@Override
	protected CustomerModel getCustomer(final SendVerificationTokenProcessModel businessProcessModel)
	{
		return null;
	}

	@Override
	protected LanguageModel getEmailLanguage(final SendVerificationTokenProcessModel businessProcessModel)
	{
		return businessProcessModel.getLanguage();
	}
	@Override
	public String getToEmail()
	{
		return target;
	}

	@Override
	public String getDisplayName()
	{
		return "";
	}

	public String getTarget()
	{
		return this.target;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public String getTokenCode()
	{
		return tokenCode;
	}
	public int getTtlSeconds()
	{
		return ttlSeconds;
	}

	public int getMaxAttempts()
	{
		return maxAttempts;
	}
}