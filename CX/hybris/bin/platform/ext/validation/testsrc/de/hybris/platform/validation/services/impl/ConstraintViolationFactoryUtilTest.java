/*
 *
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.validation.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.impl.DefaultTypeService;
import de.hybris.platform.validation.messages.ResourceBundleProvider;
import de.hybris.platform.validation.model.constraints.AbstractConstraintModel;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ConstraintViolationFactoryUtilTest
{
	private ConstraintViolationFactoryUtil subject;
	@Mock
	private ModelService modelService;
	@Mock
	private ResourceBundleProvider bundleProvider;
	@Mock
	private I18NService i18nService;
	@Mock
	private ConstraintViolation<TestPojo> constraintViolation;

	@Before
	public void setUp()
	{
		final DefaultTypeService typeService = new DefaultTypeService();
		typeService.setModelService(modelService);

		subject = new ConstraintViolationFactoryUtil(modelService, typeService, bundleProvider, i18nService);
	}

	private record TestPojo(@NotNull String value)
	{
	}

	@Test
	public void shouldReturnEmptySetForNotExistingType()
	{
		// given
		Mockito.when(constraintViolation.getRootBeanClass()).thenReturn(TestPojo.class);
		Mockito.when(modelService.getModelType(TestPojo.class)).thenReturn(null);
		
		// when
		final Set<AbstractConstraintModel> actual = subject.buildConstraintModelForLocalizedMessage(constraintViolation);

		// then
		assertNotNull(actual);
		assertTrue(actual.isEmpty());
	}

}
