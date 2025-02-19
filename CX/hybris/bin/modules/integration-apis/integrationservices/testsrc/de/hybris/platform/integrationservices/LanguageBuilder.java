/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationservices;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.integrationservices.util.IntegrationTestUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.rules.ExternalResource;

import com.google.common.base.Preconditions;


/**
 * Builder for an {@link LanguageModel}
 */
public class LanguageBuilder extends ExternalResource
{
	private String isoCode;
	private String name;

	private final Set<String> createdLanguages;

	private LanguageBuilder()
	{
		createdLanguages = new HashSet<>();
	}

	public static LanguageBuilder languageBuilder()
	{
		return new LanguageBuilder();
	}

	public LanguageBuilder withCode(final String code)
	{
		isoCode = code;
		return this;
	}

	public LanguageBuilder withName(final String name)
	{
		this.name = name;
		return this;
	}

	public LanguageModel build() throws ImpExException
	{
		Preconditions.checkArgument(isoCode != null, "isoCode cannot be null");

		IntegrationTestUtil.importImpEx("INSERT_UPDATE Language; isocode[unique = true]; name[lang=en]",
				"; " + isoCode + " ; " + deriveName());

		createdLanguages.add(isoCode);
		return languageByCode(isoCode).orElse(null);
	}

	public void cleanup()
	{
		IntegrationTestUtil.remove(LanguageModel.class, it -> createdLanguages.contains(it.getIsocode()));
		createdLanguages.clear();
	}

	@Override
	protected void before() throws ImpExException
	{
		build();
	}

	@Override
	protected void after()
	{
		cleanup();
	}

	private String deriveName()
	{
		return name != null ? name : "Does Not Matter";
	}

	private Optional<LanguageModel> languageByCode(final String isoCode)
	{
		return IntegrationTestUtil.findAny(LanguageModel.class, e -> e.getIsocode().equals(isoCode));
	}
}
