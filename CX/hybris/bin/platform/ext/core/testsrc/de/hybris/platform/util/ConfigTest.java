/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.util;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.testframework.PropertyConfigSwitcher;

import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertThrows;


@IntegrationTest
public class ConfigTest extends ServicelayerBaseTest
{
	private PropertyConfigSwitcher flag1;
	private PropertyConfigSwitcher flag2;

	@Before
	public void setUp()
	{
		flag1 = new PropertyConfigSwitcher(Config.MODELT_DETECTION_FLAG1);
		flag2 = new PropertyConfigSwitcher(Config.MODELT_DETECTION_FLAG2);
	}

	@After
	public void tearDown()
	{
		flag1.switchBackToDefault();
		flag2.switchBackToDefault();
	}

	@Test
	public void shouldDetectOnPremEnvironmentWhenBothModelTPropertiesAreNotSet()
	{
		flag1.switchToValue("");
		flag2.switchToValue("");
		assertThat(Config.isCloudEnvironment()).isFalse();

		flag1.switchToValue("");
		flag2.switchToValue(null);
		assertThat(Config.isCloudEnvironment()).isFalse();

		flag1.switchToValue(null);
		flag2.switchToValue("");
		assertThat(Config.isCloudEnvironment()).isFalse();

		flag1.switchToValue(null);
		flag2.switchToValue(null);
		assertThat(Config.isCloudEnvironment()).isFalse();
	}

	@Test
	public void shouldDetectCloudEnvironmentWhenBothModelTPropertiesAreSet()
	{
		flag1.switchToValue("f1");
		flag2.switchToValue("f2");

		assertThat(Config.isCloudEnvironment()).isTrue();
	}

	@Test
	public void shouldDetectCloudEnvironmentWhenOnlyOneModelTPropertyIsSet()
	{
		flag1.switchToValue("");
		flag2.switchToValue("f2");
		assertThat(Config.isCloudEnvironment()).isTrue();

		flag1.switchToValue(null);
		flag2.switchToValue("f2");
		assertThat(Config.isCloudEnvironment()).isTrue();

		flag1.switchToValue("f1");
		flag2.switchToValue("");
		assertThat(Config.isCloudEnvironment()).isTrue();

		flag1.switchToValue("f1");
		flag2.switchToValue(null);
		assertThat(Config.isCloudEnvironment()).isTrue();
	}

	@Test
	public void shouldVerifyIfConfigPropertyWithValueInRangeIsReturned()
	{
		//given
		final int DEFAULT_VALUE = 5;
		final int MIN_VALUE = 3;
		final int MAX_VALUE = 10;
		final int IN_RANGE_VALUE = 8;
		final String RANDOM_PROPERTY = "test.config.a" + System.currentTimeMillis();
		Config.setParameter(RANDOM_PROPERTY, IN_RANGE_VALUE + ""); // value in range

		// when
		final int value = Config.getInt(RANDOM_PROPERTY, DEFAULT_VALUE, MIN_VALUE, MAX_VALUE);

		//then
		assertThat(value).isEqualTo(IN_RANGE_VALUE);
	}
	@Test
	public void shouldVerifyIfConfigPropertyWithValueOutsideMinRangeValueIsSafeguarded()
	{
		//given
		final int DEFAULT_VALUE = 5;
		final int MIN_VALUE = 3;
		final int MAX_VALUE = 10;
		final String RANDOM_PROPERTY = "test.config.a" + System.currentTimeMillis();
		Config.setParameter(RANDOM_PROPERTY, "2"); // outside min range

		// when
		final int value = Config.getInt(RANDOM_PROPERTY, DEFAULT_VALUE, MIN_VALUE, MAX_VALUE);

		//then
		assertThat(value).isEqualTo(MIN_VALUE);
	}

	@Test
	public void shouldVerifyIfConfigPropertyWithValueOutsideMaxRangeValueIsSafeguarded()
	{
		//given
		final int DEFAULT_VALUE = 5;
		final int MIN_VALUE = 3;
		final int MAX_VALUE = 10;
		final String RANDOM_PROPERTY = "test.config.a" + System.currentTimeMillis();
		Config.setParameter(RANDOM_PROPERTY, "11"); // outside max range

		// when
		final int value = Config.getInt(RANDOM_PROPERTY, DEFAULT_VALUE, MIN_VALUE, MAX_VALUE);

		//then
		assertThat(value).isEqualTo(MAX_VALUE);
	}

	@Test
	public void shouldVerifyIfConfigPropertyValueIsSetToDefaultIfNotExisting()
	{
		//given
		final int DEFAULT_VALUE = 5;
		final int MIN_VALUE = 3;
		final int MAX_VALUE = 10;
		final String RANDOM_PROPERTY = "test.config.a" + System.currentTimeMillis();

		// when
		final int value = Config.getInt(RANDOM_PROPERTY, DEFAULT_VALUE, MIN_VALUE, MAX_VALUE);

		//then
		assertThat(value).isEqualTo(DEFAULT_VALUE);
	}

	@Test
	public void shouldThrowExceptionIfConfigPropertyIsNotNumberWhenExpected()
	{
		//given
		final int DEFAULT_VALUE = 5;
		final int MIN_VALUE = 3;
		final int MAX_VALUE = 10;
		final String RANDOM_PROPERTY = "test.config.a" + System.currentTimeMillis();

		Config.setParameter(RANDOM_PROPERTY, "not a number");

		//when
		assertThrows(NumberFormatException.class, () -> Config.getInt(RANDOM_PROPERTY, DEFAULT_VALUE, MIN_VALUE, MAX_VALUE));
	}

	@Test
	public void shouldReturnValidValueIfPredicateForRetrievalConfigPropertyResultTrue()
	{
		//given
		final String DEFAULT_VALUE = "Default valid value longer than 10 characters";
		final String VALID_VALUE = "Value which fulfills the condition";
		final Predicate<String> condition = value -> value.chars().distinct().count() > 10;
		final String RANDOM_PROPERTY = "test.config.string" + System.currentTimeMillis();

		Config.setParameter(RANDOM_PROPERTY, VALID_VALUE);

		//when
		final var result = Config.getString(RANDOM_PROPERTY, DEFAULT_VALUE, condition);

		//then
		assertThat(result).isEqualTo(VALID_VALUE);
	}

	@Test
	public void shouldReturnDefaultValueIfPredicateForRetrievalConfigPropertyResultFalse()
	{
		//given
		final String DEFAULT_VALUE = "Default valid value longer than 10 characters";
		final String TO_SHORT_VALUE = "abc123";
		final Predicate<String> condition = value -> value.chars().distinct().count() > 10;
		final String RANDOM_PROPERTY = "test.config.string" + System.currentTimeMillis();

		Config.setParameter(RANDOM_PROPERTY, TO_SHORT_VALUE);

		//when
		final var result = Config.getString(RANDOM_PROPERTY, DEFAULT_VALUE, condition);

		//then
		assertThat(result).isEqualTo(DEFAULT_VALUE);
	}
}