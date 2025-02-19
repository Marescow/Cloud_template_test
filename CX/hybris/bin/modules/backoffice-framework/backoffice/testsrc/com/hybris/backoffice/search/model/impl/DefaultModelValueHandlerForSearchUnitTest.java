/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.search.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.hybris.backoffice.constants.BackofficeConstants;
import com.hybris.backoffice.search.expression.ExpressionResolverFactoryForSearch;
import com.hybris.backoffice.search.expression.ExpressionResolverForSearch;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultModelValueHandlerForSearchUnitTest
{
	@Spy
	@InjectMocks
	private DefaultModelValueHandlerForSearch modelValueHandlerForSearch;

	@Mock
	private ExpressionResolverFactoryForSearch expressionResolverFactory;

	@Mock
	private ExpressionResolverForSearch expressionResolverForSearch;

	@Resource(name = "modelService")
	private ModelService modelService;

	private AutoCloseable openedMocks = null;

	@Before
	public void onSetup() {
		openedMocks = MockitoAnnotations.openMocks(this);

		if (Objects.isNull(modelService)) {
			modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");
		}
	}

	@After
	public void onCleanup() throws Exception
	{
		openedMocks.close();
	}

	@Test
	public void testGetValueForObjectIsNull() {
		final Object value = modelValueHandlerForSearch.getValue(null,  "key",
				Locale.ENGLISH, true);
		assertThat(value).isNull();
	}

	@Test
	public void testGetValueForKeyIsEmpty() {
		final Object value = modelValueHandlerForSearch.getValue(new Object(),  "key",
				Locale.ENGLISH, true);
		assertThat(value).isNull();
	}

	@Test
	public void testGetValue() {
		final ItemModel itemModel = modelService.create(ProductModel._TYPECODE);
		final String labelRule = "(name ?: '') + ' [' + ( code ?: '' ) + '] - ' + @labelServiceForSearch.getObjectLabel(catalogVersion)";
		final Object expectedLabelValue = "productLabel";
		final Locale locale = Locale.ENGLISH;

		final Map<String, Object> variables = new HashMap<>();
		variables.put(BackofficeConstants.USE_SESSION_LANGUAGE_FOR_LOCALIZED, true);
		variables.put(BackofficeConstants.KEY_SESSION_LANGUAGE_FOR_LOCALIZED, locale);
		Mockito.when(expressionResolverForSearch.getValue(itemModel, labelRule, variables)).thenReturn(expectedLabelValue);

		final Object value = modelValueHandlerForSearch.getValue(itemModel, labelRule, locale, true);
		assertThat(value).isNotNull().isEqualTo(expectedLabelValue);
	}
}
