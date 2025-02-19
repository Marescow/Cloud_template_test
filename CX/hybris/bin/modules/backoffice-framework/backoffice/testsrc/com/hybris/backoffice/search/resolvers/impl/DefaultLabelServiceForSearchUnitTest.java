/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.search.resolvers.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.enums.SyncItemStatus;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.Config;

import javax.annotation.Resource;

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
import com.hybris.backoffice.search.model.ModelValueHandlerForSearch;
import com.hybris.backoffice.search.utils.LocaleUtil;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultLabelServiceForSearchUnitTest
{
	@InjectMocks
	@Spy
	private DefaultLabelServiceForSearch labelServiceForSearch;

	@Mock
	private EnumerationService defaultEnumerationService;

	@Mock
	private TypeService typeService;

	@Mock
	private ModelValueHandlerForSearch defaultModelValueHandler;

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
	public void testGetDefinedLabelRules() {
		final Map<String, String> labelRule4Source = Config.getParametersByPattern(
				BackofficeConstants.BACKOFFICE_SEARCH_INDEX_LABEL_RULE_PATTERN);

		Map<String, String> labelRules = labelServiceForSearch.getDefinedLabelRules();
		assertThat(labelRules).isNotNull().hasSameSizeAs(labelRule4Source);
		assertThat(labelRules.values()).allMatch(labelRule4Source::containsValue);
	}

	@Test
	public void testGetObjectLabelForObjectIsNull() {
		// Don't mock LocaleUtil static class, directly using LocaleUtil's methods to set current locale context
		LocaleUtil.setThreadLocale(Locale.ENGLISH);
		final String labelValue = labelServiceForSearch.getObjectLabel(null, Locale.GERMAN);
		assertThat(labelValue).isNotNull().isEqualTo(BackofficeConstants.BACKOFFICE_LABEL_VALUE_IS_NULL);
		assertThat(LocaleUtil.getCurrentLocale()).isNotNull().isEqualTo(Locale.ENGLISH);
	}

	@Test
	public void testGetObjectLabelForObjectIsItemType() {
		final ItemModel itemModel = modelService.create(ProductModel._TYPECODE);
		final Locale locale = Locale.ENGLISH;
		final Object expectedLabelValue = "expectedLabelValue";
		final String expectedLabelRuleValue = labelServiceForSearch.getDefinedLabelRules().get(itemModel.getItemtype().toLowerCase());

		Mockito.when(typeService.isAssignableFrom(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		Mockito.when(defaultModelValueHandler.getValue(itemModel, expectedLabelRuleValue, locale, true)).thenReturn(expectedLabelValue);

		final String labelValue = labelServiceForSearch.getObjectLabel(itemModel, locale);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValue);
	}

	@Test
	public void testGetObjectLabelForObjectIsEnumType() {
		final HybrisEnumValue enumValue = SyncItemStatus.IN_SYNC;
		final Locale locale = Locale.ENGLISH;
		final String expectedLabelValue = "expectedLabelValue";

		Mockito.when(defaultEnumerationService.getEnumerationName(enumValue, locale)).thenReturn(expectedLabelValue);

		final String labelValue = labelServiceForSearch.getObjectLabel(enumValue, locale);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValue);
	}

	@Test
	public void testGetObjectLabelForObjectIsOtherType() {
		final Locale locale = Locale.ENGLISH;
		final String expectedLabelValue = "expectedLabelValue";

		final String labelValue = labelServiceForSearch.getObjectLabel(expectedLabelValue, locale);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValue);
	}
}
