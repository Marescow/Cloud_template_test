/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices;

import java.util.List;

import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * A context for naming persisted items within an integration test, so that their names/IDs could identify the test that created them.
 */
public class NamingContext extends ExternalResource
{
	private static final List<String> KNOWN_TEST_CLASS_SUFFIXES =
			List.of("IntegrationTest", "UnitTest", "Test", "Specification", "Spec", "IT");
	private static final String UNKNOWN_TEST_NAME = "";
	private static NamingContext currentContext;

	private String testName;
	private Description contextDescription;

	/**
	 * Returns the current naming context. If no context is set, a default context is created.
	 *
	 * @return a current naming context instance.
	 */
	public static NamingContext getCurrentContext()
	{
		if (currentContext == null)
		{
			currentContext = new NamingContext();
		}
		return currentContext;
	}

	private NamingContext()
	{
		// make the constructor private to prevent instantiation,
		// so that it could be instantiated only through the Singleton pattern
	}

	/**
	 * Specifies context name to be used for naming items in an integration test.
	 *
	 * @param name name of the test
	 */
	public void withName(final String name)
	{
		testName = name;
	}

	/**
	 * Returns the name of the test.
	 *
	 * @return the name of the test
	 */
	public String getTestName()
	{
		return testName != null ? testName : deriveTestName();
	}

	/**
	 * Returns a name that is specific to the test, for which this naming context is created.
	 *
	 * @param name a name to make specific to the test
	 * @return a test specific name or the provided name, if it's already specific to the test.
	 */
	public static String testSpecific(final String name)
	{
		final String testName = getCurrentContext().getTestName();
		return name != null && name.startsWith(testName)
				? name
				: testName + "_" + name;
	}

	private String deriveTestName()
	{
		return contextDescription == null
				? UNKNOWN_TEST_NAME
				: stripSuffix(contextDescription.getTestClass().getSimpleName());
	}

	private String stripSuffix(final String className)
	{
		return KNOWN_TEST_CLASS_SUFFIXES.stream()
				.filter(className::endsWith)
				.findFirst()
				.map(suffix -> className.substring(0, className.length() - suffix.length()))
				.orElse(className);
	}

	@Override
	public Statement apply(final Statement base, final Description description)
	{
		contextDescription = description;
		return super.apply(base, description);
	}

	@Override
	protected void after()
	{
		currentContext = null;
	}
}
