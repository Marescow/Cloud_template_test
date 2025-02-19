/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.solrsearch.resolvers;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.hybris.backoffice.search.resolvers.LabelServiceForSearch;


@RunWith(MockitoJUnitRunner.class)
public class ItemModelLabelValueResolverTest
{
	@Spy
	@InjectMocks
	private ItemModelLabelValueResolver resolver;

	@Mock
	private LabelServiceForSearch labelServiceForSearch;

	@Test
	public void resolveIndexKeyShouldCreateFieldWithLowercaseIsoCode()
	{
		//given
		final IndexedProperty indexProperty = new IndexedProperty();
		indexProperty.setName("catalogVersion");
		indexProperty.setType("string");
		final LanguageModel language = new LanguageModel();
		language.setIsocode("es_CO");

		//when
		final String result = resolver.resolveIndexKey(indexProperty, language);

		//then
		assertThat(result).isEqualTo("catalogVersion_es_co_string");
	}

	@Test
	public void testGetLabelValueByLabelServiceForSearch() {
		final ItemModel mockedItemModel = Mockito.mock(ItemModel.class);
		final Locale mockedLocale = Mockito.mock(Locale.class);

		resolver.setLabelServiceProxy(null);
		Mockito.when(labelServiceForSearch.getObjectLabel(mockedItemModel, mockedLocale)).thenReturn("test");

		final String labelValue = resolver.getLabelValue(mockedItemModel, mockedLocale);
		assertThat(labelValue).isEqualTo("test");
		verify(labelServiceForSearch).getObjectLabel(mockedItemModel, mockedLocale);
	}
}
