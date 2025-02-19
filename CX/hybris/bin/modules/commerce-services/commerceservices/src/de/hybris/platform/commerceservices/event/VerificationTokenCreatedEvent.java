/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.store.BaseStoreModel;

/**
 * Verification token created event, implementation of {@link AbstractEvent}
 */
public class VerificationTokenCreatedEvent extends AbstractEvent
{
	private BaseStoreModel baseStore;
	private BaseSiteModel site;
	private LanguageModel language;
	private String target;
	private String tokenCode;
	private String purpose;

	public BaseStoreModel getBaseStore()
	{
		return baseStore;
	}

	public void setBaseStore(final BaseStoreModel baseStore)
	{
		this.baseStore = baseStore;
	}

	public BaseSiteModel getSite()
	{
		return site;
	}

	public void setSite(final BaseSiteModel site)
	{
		this.site = site;
	}

	public LanguageModel getLanguage()
	{
		return language;
	}

	public void setLanguage(final LanguageModel language)
	{
		this.language = language;
	}

	public String getTarget()
	{
		return target;
	}

	public void setTarget(final String target)
	{
		this.target = target;
	}

	public String getTokenCode()
	{
		return tokenCode;
	}

	public void setTokenCode(final String tokenCode)
	{
		this.tokenCode = tokenCode;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose(final String purpose)
	{
		this.purpose = purpose;
	}
}