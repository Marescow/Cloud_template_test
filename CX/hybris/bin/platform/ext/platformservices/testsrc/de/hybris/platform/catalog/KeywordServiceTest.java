/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.catalog;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.daos.KeywordDao;
import de.hybris.platform.catalog.impl.DefaultKeywordService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Arrays;
import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * tests {@link DefaultKeywordService}
 */
@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class KeywordServiceTest
{
	private final static String keyword = "keyword";
	private final static String typecode = "Typecode";
	private CatalogVersionModel catalogVersion;
	private LanguageModel language;
	private DefaultKeywordService keywordService;
	@Mock
	private KeywordDao keywordDao;

	@Before
	public void setUp()
	{
		catalogVersion = new CatalogVersionModel();
		language = new LanguageModel();
		keywordService = new DefaultKeywordService();
		keywordService.setKeywordDao(keywordDao);
	}

	@Test
	public void testGetKeyward()
	{
		final KeywordModel keywordModel = new KeywordModel();
		Mockito.when(keywordDao.getKeywords(catalogVersion, keyword)).thenReturn(Collections.singletonList(keywordModel));

		Assertions.assertThat(keywordService.getKeyword(catalogVersion, keyword)).isSameAs(keywordModel);
	}


	@Test
	public void testGetKeywardFailToMany()
	{

		Mockito.when(keywordDao.getKeywords(catalogVersion, keyword)).thenReturn(
				Arrays.asList(new KeywordModel(), new KeywordModel()));

		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, keyword))
				.isInstanceOf(AmbiguousIdentifierException.class);
	}

	@Test
	public void testGetKeywardFailNullArg()
	{
		assertThatThrownBy(() -> keywordService.getKeyword(null, keyword)).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, null)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void testGetKeywardFailEmpty()
	{

		Mockito.when(keywordDao.getKeywords(catalogVersion, keyword)).thenReturn(Collections.emptyList());

		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, keyword)).isInstanceOf(
				UnknownIdentifierException.class);
	}


	@Test
	public void testTypecodeGetKeyward()
	{
		final KeywordModel keywordModel = new KeywordModel();
		Mockito.when(keywordDao.getKeywords(typecode, catalogVersion, keyword)).thenReturn(
				Collections.singletonList(keywordModel));

		Assertions.assertThat(keywordService.getKeyword(typecode, catalogVersion, keyword)).isSameAs(keywordModel);
	}


	@Test
	public void testTypecodeGetKeywardFailToMany()
	{

		Mockito.when(keywordDao.getKeywords(typecode, catalogVersion, keyword)).thenReturn(
				Arrays.asList(new KeywordModel(), new KeywordModel()));


		assertThatThrownBy(() -> keywordService.getKeyword(typecode, catalogVersion, keyword))
				.isInstanceOf(AmbiguousIdentifierException.class);
	}

	@Test
	public void testTypecodeGetKeywardFailEmpty()
	{

		Mockito.when(keywordDao.getKeywords(typecode, catalogVersion, keyword)).thenReturn(Collections.emptyList());

		assertThatThrownBy(() -> keywordService.getKeyword(typecode, catalogVersion, keyword)).isInstanceOf(
				UnknownIdentifierException.class);
	}


	@Test
	public void testTypecodeGetKeywardFailNullArg()
	{
		assertThatThrownBy(() -> keywordService.getKeyword(null, catalogVersion, keyword)).isInstanceOf(
				IllegalArgumentException.class);
		assertThatThrownBy(() -> keywordService.getKeyword(typecode, null, keyword)).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> keywordService.getKeyword(typecode, catalogVersion, null)).isInstanceOf(
				IllegalArgumentException.class);
	}

	@Test
	public void testLanguageGetKeywordFailNullArg()
	{
		assertThatThrownBy(() -> keywordService.getKeyword(null, keyword, language)).isInstanceOf(
				IllegalArgumentException.class);
		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, null, language)).isInstanceOf(
				IllegalArgumentException.class);
		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, keyword, null)).isInstanceOf(
				IllegalArgumentException.class);
	}

	@Test
	public void testLanguageGetKeywordFailToMany()
	{
		final KeywordModel keywordModel1 = new KeywordModel();
		keywordModel1.setLanguage(language);
		final KeywordModel keywordModel2 = new KeywordModel();
		keywordModel2.setLanguage(language);

		Mockito.when(keywordDao.getKeywords(catalogVersion, keyword)).thenReturn(
				Arrays.asList(keywordModel1, keywordModel2));

		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, keyword, language))
				.isInstanceOf(AmbiguousIdentifierException.class);
	}

	@Test
	public void testLanguageGetKeywordFailEmpty()
	{
		Mockito.when(keywordDao.getKeywords(catalogVersion, keyword)).thenReturn(Collections.emptyList());

		assertThatThrownBy(() -> keywordService.getKeyword(catalogVersion, keyword, language)).isInstanceOf(
				UnknownIdentifierException.class);
	}

	@Test
	public void testLanguageGetKeyword()
	{
		final KeywordModel keywordModel = new KeywordModel();
		keywordModel.setLanguage(language);
		Mockito.when(keywordDao.getKeywords(catalogVersion, keyword))
		       .thenReturn(Collections.singletonList(keywordModel));

		Assertions.assertThat(keywordService.getKeyword(catalogVersion, keyword, language)).isSameAs(keywordModel);
	}
}
