/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.catalog.impl;

import de.hybris.platform.catalog.KeywordService;
import de.hybris.platform.catalog.daos.KeywordDao;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Required;


/**
 * Default implementation of {@link KeywordService}. Use {@link KeywordDao} to search keywords.
 */
public class DefaultKeywordService implements KeywordService
{
	private static final String CATALOG_VERSION_STRING_PARAMETER = "catalogVersion";
	private static final String KEYWORD_STRING_PARAMETER = "keyword";
	private static final String LANGUAGE_STRING_PARAMETER = "language";
	private static final String TYPECODE_STRING_PARAMETER = "typeCode";
	private KeywordDao keywordDao;

	@Override
	public KeywordModel getKeyword(final CatalogVersionModel catalogVersion, final String keywordValue)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(CATALOG_VERSION_STRING_PARAMETER, catalogVersion);
		ServicesUtil.validateParameterNotNullStandardMessage(KEYWORD_STRING_PARAMETER, keywordValue);

		final List<KeywordModel> res = keywordDao.getKeywords(catalogVersion, keywordValue);

		ServicesUtil.validateIfSingleResult(res, KeywordModel.class, "catalogVersion, keyword", keywordValue + ", "
				+ catalogVersion.getPk());

		return res.get(0);
	}

	@Override
	public KeywordModel getKeyword(final String typeCode, final CatalogVersionModel catalogVersion, final String keywordValue)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(CATALOG_VERSION_STRING_PARAMETER, catalogVersion);
		ServicesUtil.validateParameterNotNullStandardMessage(KEYWORD_STRING_PARAMETER, keywordValue);
		ServicesUtil.validateParameterNotNullStandardMessage(TYPECODE_STRING_PARAMETER, typeCode);

		final List<KeywordModel> res = keywordDao.getKeywords(typeCode, catalogVersion, keywordValue);

		ServicesUtil.validateIfSingleResult(res, KeywordModel.class, "typeCode, catalogVersion, keyword", typeCode + ", "
				+ keywordValue + ", " + catalogVersion.getPk());

		return res.get(0);
	}

	@Override
	public KeywordModel getKeyword(final CatalogVersionModel catalogVersion, final String keywordValue,
	                               final LanguageModel language)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(CATALOG_VERSION_STRING_PARAMETER, catalogVersion);
		ServicesUtil.validateParameterNotNullStandardMessage(KEYWORD_STRING_PARAMETER, keywordValue);
		ServicesUtil.validateParameterNotNullStandardMessage(LANGUAGE_STRING_PARAMETER, language);

		final List<KeywordModel> result = keywordDao.getKeywords(catalogVersion, keywordValue);
		final List<KeywordModel> filteredResult = result.stream()
		                                                .filter(keywordModel -> Objects.equals(keywordModel.getLanguage(),
				                                                language))
		                                                .toList();

		ServicesUtil.validateIfSingleResult(filteredResult, KeywordModel.class, "catalogVersion, keyword, language",
				catalogVersion.getPk() + ", " + keywordValue + ", " + language.getIsocode());

		return filteredResult.get(0);
	}

	@Required
	public void setKeywordDao(final KeywordDao keywordDao)
	{
		this.keywordDao = keywordDao;
	}

}
