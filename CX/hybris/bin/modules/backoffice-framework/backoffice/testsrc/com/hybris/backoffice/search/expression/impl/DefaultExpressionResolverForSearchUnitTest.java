/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.search.expression.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.MethodResolver;
import org.springframework.expression.PropertyAccessor;

import com.hybris.backoffice.constants.BackofficeConstants;
import com.hybris.backoffice.search.expression.ExpressionResolverForSearch;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultExpressionResolverForSearchUnitTest
{
	@Spy
	@InjectMocks
	private DefaultExpressionResolverForSearch defaultExpressionResolverForSearch;

	@Spy
	@InjectMocks
	private DefaultExpressionResolverFactoryForSearch defaultExpressionResolverFactoryForSearch;

	@Spy
	@InjectMocks
	private DefaultEvaluationContextFactoryForSearch defaultEvaluationContextFactoryForSearch;

	@Mock
	private List<PropertyAccessor> additionalPropertyAccessors;

	@Mock
	private List<MethodResolver> methodResolvers;

	@Mock
	private BeanResolver beanResolver;

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
	public void testGetValue() {
		final ItemModel itemModel = Mockito.mock(ProductModel.class);
		final String expression = "(name ?: '') + ' [' + ( code ?: '' ) + '] - ' + @labelServiceForSearch.getObjectLabel(catalogVersion)";
		final Object expectedValue = "test label value";

		final EvaluationContext context = Mockito.mock(EvaluationContext.class);
		final Expression expressionObject = Mockito.mock(Expression.class);
		final ExpressionParser expressionParser = Mockito.mock(ExpressionParser.class);
		Mockito.when(defaultExpressionResolverForSearch.getContextFactory()).thenReturn(defaultEvaluationContextFactoryForSearch);
		Mockito.when(defaultEvaluationContextFactoryForSearch.createContext(itemModel, Collections.emptyMap())).thenReturn(context);
		Mockito.when(defaultExpressionResolverForSearch.getExpressionParser()).thenReturn(expressionParser);
		Mockito.when(defaultExpressionResolverForSearch.getExpressionParser().parseExpression(expression)).thenReturn(expressionObject);
		Mockito.when(expressionObject.getValue(context, itemModel)).thenReturn(expectedValue);

		final Object labelValue = defaultExpressionResolverForSearch.getValue(itemModel, expression);
		assertThat(labelValue).isNotNull().isEqualTo(expectedValue);

		// Test exception case
		final String exceptionMsg = "test throw ExpressionException error";
		Mockito.when(expressionObject.getValue(context, itemModel)).thenThrow(new ExpressionException(exceptionMsg));
		assertThrows(ExpressionException.class, () -> {
			defaultExpressionResolverForSearch.getValue(itemModel, expression);
		});
	}

	@Test
	public void testCreateExpressionResolver() {
		Mockito.when(defaultExpressionResolverFactoryForSearch.getExpressionParser()).thenReturn(Mockito.mock(ExpressionParser.class));

		final ExpressionResolverForSearch expressionResolverForSearch =
				defaultExpressionResolverFactoryForSearch.createExpressionResolver();
		assertThat(expressionResolverForSearch).isNotNull();
	}

	@Test
	public void testCreateContext() {
		additionalPropertyAccessors = (List<PropertyAccessor>) Registry.getApplicationContext().getBean("propertyAccessorsForSearch");
		defaultEvaluationContextFactoryForSearch.setAdditionalPropertyAccessors(additionalPropertyAccessors);
		methodResolvers = (List<MethodResolver>) Registry.getApplicationContext().getBean("methodResolversForSearch");
		defaultEvaluationContextFactoryForSearch.setMethodResolvers(methodResolvers);
		beanResolver = (BeanResolver) Registry.getApplicationContext().getBean("fixedBeanResolverForSearch");
		defaultEvaluationContextFactoryForSearch.setBeanResolver(beanResolver);

		final ItemModel itemModel = Mockito.mock(ProductModel.class);
		final Map<String, Object> variables = new HashMap<>();
		variables.put(BackofficeConstants.USE_SESSION_LANGUAGE_FOR_LOCALIZED, true);
		variables.put(BackofficeConstants.KEY_SESSION_LANGUAGE_FOR_LOCALIZED, Locale.ENGLISH);

		final EvaluationContext evaluationContext = defaultEvaluationContextFactoryForSearch.createContext(itemModel, variables);
		assertThat(evaluationContext).isNotNull();
		assertThat(evaluationContext.getRootObject().getValue()).isInstanceOf(ItemModel.class);
		assertThat(evaluationContext.getBeanResolver()).isNotNull().isEqualTo(beanResolver);
		assertThat(evaluationContext.getMethodResolvers()).allMatch(methodResolvers::contains);
		additionalPropertyAccessors.forEach(value -> assertThat(evaluationContext.getPropertyAccessors()).contains(value));
	}
}
