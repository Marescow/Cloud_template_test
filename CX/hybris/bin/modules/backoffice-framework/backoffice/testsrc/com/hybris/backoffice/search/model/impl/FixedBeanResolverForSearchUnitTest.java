/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.search.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.context.ApplicationContext;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import com.hybris.backoffice.search.resolvers.LabelServiceForSearch;
import com.hybris.backoffice.search.resolvers.impl.DefaultLabelServiceForSearch;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class FixedBeanResolverForSearchUnitTest
{
	@Spy
	@InjectMocks
	private FixedBeanResolverForSearch fixedBeanResolverForSearch;

	@Mock
	private ApplicationContext applicationContext;

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
	public void testResolveMethod() throws AccessException
	{
		final EvaluationContext context = Mockito.mock(SimpleEvaluationContext.class);
		final List<String> availableBeanNames = new ArrayList<>(1);
		availableBeanNames.add("labelServiceForSearch");
		fixedBeanResolverForSearch.setAvailableBeanNames(availableBeanNames);

		final LabelServiceForSearch expectedBeanObject = Mockito.mock(DefaultLabelServiceForSearch.class);
		Mockito.when(fixedBeanResolverForSearch.getApplicationContext().getBean("labelServiceForSearch")).thenReturn(expectedBeanObject);

		final Object labelServiceForSearch = fixedBeanResolverForSearch.resolve(context, "labelServiceForSearch");
		assertThat(labelServiceForSearch).
				isNotNull().
				isInstanceOf(DefaultLabelServiceForSearch.class).
				isEqualTo(expectedBeanObject);
	}

	@Test
	public void testResolveMethodForContextIsNull() throws AccessException
	{
		final Object labelServiceForSearch = fixedBeanResolverForSearch.resolve(null, "labelServiceForSearch");
		assertThat(labelServiceForSearch).isNull();
	}

	@Test
	public void testResolveMethodForBeanIsInvalid() throws AccessException
	{
		final EvaluationContext context = Mockito.mock(SimpleEvaluationContext.class);
		final List<String> availableBeanNames = new ArrayList<>(1);
		fixedBeanResolverForSearch.setAvailableBeanNames(availableBeanNames);

		final Object labelServiceForSearch = fixedBeanResolverForSearch.resolve(context, "labelServiceForSearch");
		assertThat(labelServiceForSearch).isNull();
	}
}
