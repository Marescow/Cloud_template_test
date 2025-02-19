/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.bootstrap.config.TenantInfo;
import de.hybris.platform.core.MasterTenant;
import de.hybris.platform.core.Registry;
import de.hybris.platform.util.config.ConfigIntf;

import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class UtilitiesTest
{
	private static final String EXTENSION_NAME = "processing";
	private static final String DEFAULT_EXTENSION_WEBROOT = "/processing";
	private static final String CUSTOM_EXTENSION_WEBROOT = "/customProcessing";
	private static final String UNKNOWN_EXTENSION_WEBROOT = "/unknown";

	@Mock
	private MasterTenant masterTenant;
	@Mock
	private ConfigIntf config;

	@After
	public void cleanUp()
	{
		Utilities.clearWebrootToExtensionMappingCache();
	}

	@Test
	public void shouldReturnNullContextWhenNullRequestUriIsProvided()
	{
		final String nullRequestUri = null;
		final String result = Utilities.getContextFromRequestUri(nullRequestUri);
		assertNull(result);
	}

	@Test
	public void shouldReturnEmptyContextWhenEmptyRequestUriIsProvided()
	{
		final String emptyRequestUri = "";
		final String result = Utilities.getContextFromRequestUri(emptyRequestUri);
		assertEquals("", result);
	}

	@Test
	public void shouldReturnEmptyContextWhenOnlyWhitespacesAsRequestUriAreProvided()
	{
		final String whitespacesRequestUri = " \t  ";
		final String result = Utilities.getContextFromRequestUri(whitespacesRequestUri);
		assertEquals("", result);
	}

	@Test
	public void shouldReturnContextWhenContextIsPassedAsRequestUri()
	{
		final String context1 = "/";
		final String context2 = "/ctx";
		assertEquals(context1, Utilities.getContextFromRequestUri(context1));
		assertEquals(context2, Utilities.getContextFromRequestUri(context2));
	}

	@Test
	public void shouldReturnProperContextForComplexRequestUri()
	{
		final String context1 = "/ctx1/";
		final String context2 = "/ctx2/sub1";
		final String context3 = "/ctx3/sub1/";
		final String context4 = "/ctx4/sub1/sub2";
		assertEquals("/ctx1", Utilities.getContextFromRequestUri(context1));
		assertEquals("/ctx2", Utilities.getContextFromRequestUri(context2));
		assertEquals("/ctx3", Utilities.getContextFromRequestUri(context3));
		assertEquals("/ctx4", Utilities.getContextFromRequestUri(context4));
	}

	@Test
	public void shouldGetExtensionForCustomWebroot()
	{
		//given
		Utilities.clearWebrootToExtensionMappingCache();
		when(masterTenant.getConfig()).thenReturn(config);
		when(config.getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true)).thenReturn(
				Map.of(EXTENSION_NAME, CUSTOM_EXTENSION_WEBROOT));

		try (final MockedStatic<Registry> registryStatic = Mockito.mockStatic(Registry.class))
		{
			registryStatic.when(Registry::getMasterTenant).thenReturn(masterTenant);

			//when
			final String result = Utilities.getExtensionForWebroot(CUSTOM_EXTENSION_WEBROOT, MasterTenant.MASTERTENANT_ID);

			//then
			verify(config).getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true);
			assertEquals(EXTENSION_NAME, result);
		}
	}

	@Test
	public void shouldGetExtensionForDefaultWebroot()
	{
		//given
		Utilities.clearWebrootToExtensionMappingCache();
		when(masterTenant.getConfig()).thenReturn(config);
		when(config.getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true)).thenReturn(Map.of());

		try (final MockedStatic<Registry> registryStatic = Mockito.mockStatic(Registry.class))
		{
			registryStatic.when(Registry::getMasterTenant).thenReturn(masterTenant);

			//when
			final String result = Utilities.getExtensionForWebroot(DEFAULT_EXTENSION_WEBROOT, MasterTenant.MASTERTENANT_ID);

			//then
			verify(config).getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true);
			assertEquals(EXTENSION_NAME, result);
		}
	}

	@Test
	public void shouldGetExtensionForWebrootFromCache()
	{
		//given
		Utilities.clearWebrootToExtensionMappingCache();
		when(masterTenant.getConfig()).thenReturn(config);
		when(config.getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true)).thenReturn(
				Map.of(EXTENSION_NAME, CUSTOM_EXTENSION_WEBROOT));

		try (final MockedStatic<Registry> registryStatic = Mockito.mockStatic(Registry.class))
		{
			registryStatic.when(Registry::getMasterTenant).thenReturn(masterTenant);
			//first call should fill cache
			Utilities.getExtensionForWebroot(CUSTOM_EXTENSION_WEBROOT, MasterTenant.MASTERTENANT_ID);

			//when
			//second call should return value from cache
			final String result = Utilities.getExtensionForWebroot(CUSTOM_EXTENSION_WEBROOT, MasterTenant.MASTERTENANT_ID);

			//then
			//getParametersMatching should be called only once - for first call
			verify(config).getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true);
			assertEquals(EXTENSION_NAME, result);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExtensionWhenUnknownWebroot()
	{
		//given
		Utilities.clearWebrootToExtensionMappingCache();
		when(masterTenant.getConfig()).thenReturn(config);
		when(config.getParametersMatching(TenantInfo.TENANT_WEBROOT_CONFIG_REGEXP_PATTERN, true)).thenReturn(Map.of());

		try (final MockedStatic<Registry> registryStatic = Mockito.mockStatic(Registry.class))
		{
			registryStatic.when(Registry::getMasterTenant).thenReturn(masterTenant);

			//when
			Utilities.getExtensionForWebroot(UNKNOWN_EXTENSION_WEBROOT, MasterTenant.MASTERTENANT_ID);
		}
	}
}
