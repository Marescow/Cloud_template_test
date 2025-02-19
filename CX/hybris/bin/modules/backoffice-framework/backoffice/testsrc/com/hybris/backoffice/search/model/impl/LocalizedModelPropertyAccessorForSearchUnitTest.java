/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.search.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.type.AttributeDescriptorModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.Locale;

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
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.TypedValue;

import com.hybris.backoffice.constants.BackofficeConstants;
import com.hybris.backoffice.search.utils.LocaleUtil;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class LocalizedModelPropertyAccessorForSearchUnitTest
{
	@Spy
	@InjectMocks
	private LocalizedModelPropertyAccessorForSearch localizedModelPropertyAccessorForSearch;

	@Mock
	private ModelService modelService;

	@Mock
	private TypeService typeService;

	private AutoCloseable openedMocks = null;

	@Before
	public void onSetup() {
		openedMocks = MockitoAnnotations.openMocks(this);
	}

	@After
	public void onCleanup() throws Exception
	{
		openedMocks.close();
	}

	@Test
	public void testDefaultOrderValue() {
		assertThat(localizedModelPropertyAccessorForSearch.getOrder()).isEqualTo(300);
	}

	@Test
	public void testDefaultSpecificTargetClasses() {
		final Class[] specTargetClasses = localizedModelPropertyAccessorForSearch.getSpecificTargetClasses();
		assertThat(specTargetClasses).isNotNull().hasSize(1).contains(ItemModel.class);
	}

	@Test
	public void testCanWriteMethod() throws AccessException
	{
		assertThat(localizedModelPropertyAccessorForSearch.canWrite(null, null, null)).isFalse();
	}

	@Test
	public void testCanReadMethod() throws AccessException
	{
		final EvaluationContext mockedEvaluationContext = Mockito.mock(EvaluationContext.class);
		final ItemModel mockedItemModel = Mockito.mock(ProductModel.class);
		Mockito.when(mockedItemModel.getItemtype()).thenReturn(ProductModel._TYPECODE);

		final AttributeDescriptorModel mockedAttributeDescriptorModel = Mockito.mock(AttributeDescriptorModel.class);
		Mockito.when(typeService.getAttributeDescriptor(mockedItemModel.getItemtype(), "name")).thenReturn(mockedAttributeDescriptorModel);

		Mockito.when(mockedAttributeDescriptorModel.getLocalized()).thenReturn(true);
		assertThat(localizedModelPropertyAccessorForSearch.canRead(mockedEvaluationContext, mockedItemModel, "name")).isTrue();

		Mockito.when(mockedAttributeDescriptorModel.getLocalized()).thenReturn(false);
		assertThat(localizedModelPropertyAccessorForSearch.canRead(mockedEvaluationContext, mockedItemModel, "name")).isFalse();

		Mockito.when(mockedAttributeDescriptorModel.getLocalized()).thenThrow(new UnknownIdentifierException("test throw exception"));
		assertThat(localizedModelPropertyAccessorForSearch.canRead(mockedEvaluationContext, mockedItemModel, "name")).isFalse();
	}

	@Test
	public void testReadMethod() throws AccessException
	{
		final EvaluationContext mockedEvaluationContext = Mockito.mock(EvaluationContext.class);
		Mockito.when(mockedEvaluationContext.lookupVariable(BackofficeConstants.USE_SESSION_LANGUAGE_FOR_LOCALIZED)).thenReturn(Boolean.TRUE);
		Mockito.when(mockedEvaluationContext.lookupVariable(BackofficeConstants.KEY_SESSION_LANGUAGE_FOR_LOCALIZED)).thenReturn(null);

		final ItemModel mockedItemModel = Mockito.mock(ProductModel.class);
		final Object labelValue = "test label value";

		Mockito.when(modelService.getAttributeValue(mockedItemModel, "name", LocaleUtil.getCurrentLocale())).thenReturn(labelValue);
		TypedValue typedValue = localizedModelPropertyAccessorForSearch.read(mockedEvaluationContext, mockedItemModel, "name");
		assertThat(typedValue).isNotNull().isInstanceOf(TypedValue.class);
		assertThat(typedValue.getValue()).isEqualTo(labelValue);

		Mockito.when(mockedEvaluationContext.lookupVariable(BackofficeConstants.USE_SESSION_LANGUAGE_FOR_LOCALIZED)).thenReturn(Boolean.FALSE);
		Mockito.when(modelService.getAttributeValue(mockedItemModel, "name", Locale.ENGLISH)).thenReturn(labelValue);
		typedValue = localizedModelPropertyAccessorForSearch.read(mockedEvaluationContext, mockedItemModel, "name");
		assertThat(typedValue).isNotNull().isInstanceOf(TypedValue.class);
		assertThat(typedValue.getValue()).isEqualTo(labelValue);

		Mockito.when(mockedEvaluationContext.lookupVariable(BackofficeConstants.USE_SESSION_LANGUAGE_FOR_LOCALIZED)).thenReturn(Boolean.FALSE);
		Mockito.when(modelService.getAttributeValue(mockedItemModel, "name", Locale.ENGLISH)).thenThrow(new IllegalArgumentException());
		typedValue = localizedModelPropertyAccessorForSearch.read(mockedEvaluationContext, mockedItemModel, "name");
		assertThat(typedValue).isNotNull().isInstanceOf(TypedValue.class);
		assertThat(typedValue.getValue()).isNull();
	}
}
