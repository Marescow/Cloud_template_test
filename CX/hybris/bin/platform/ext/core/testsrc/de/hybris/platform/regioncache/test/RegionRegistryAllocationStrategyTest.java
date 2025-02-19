/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.regioncache.test;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.regioncache.RegionRegistryAllocationStrategy;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.regioncache.region.RegionType;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * Test for RegionRegistryAllocationStrategy
 */
@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class RegionRegistryAllocationStrategyTest
{

	@Mock
	private CacheRegion region;

	private final RegionRegistryAllocationStrategy obj = new RegionRegistryAllocationStrategy();

	/**
	 * For region configuration with empty types RegionRegistryAllocationStrategy should "create" registry. Not to create
	 * registry RegionType.NON_REGISTRABLE (only) should be provided as a type.
	 */
	@Test
	public void shouldUseRegistryForManualRegion()
	{
		whenRegionHandlesTypes();
		assertThat(obj.isRegionRequiresRegistry(region)).isTrue();
	}

	@Test
	public void shouldNotUseRegistryForManualRegion()
	{
		whenRegionHandlesTypes(RegionType.NON_REGISTRABLE);

		assertThat(obj.isRegionRequiresRegistry(region)).isFalse();
	}

	@Test
	public void testNullValue()
	{
		assertThat(obj.isRegionRequiresRegistry(null)).isFalse();
	}


	@Test
	public void shouldUseRegistryForQueryCacheRegion()
	{
		whenRegionHandlesTypes(RegionType.QUERY_CACHE_TYPE);

		assertThat(obj.isRegionRequiresRegistry(region)).isTrue();
	}

	@Test
	public void shouldUseRegistryForTypeSystemQueryCacheRegionType()
	{
		whenRegionHandlesTypes(RegionType.TYPE_SYSTEM_QUERY_CACHE_TYPE);

		assertThat(obj.isRegionRequiresRegistry(region)).isTrue();
	}
	@Test
	public void shouldUseRegistryForAllTypesRegionType()
	{
		whenRegionHandlesTypes(RegionType.ALL_TYPES);

		assertThat(obj.isRegionRequiresRegistry(region)).isTrue();
	}

	@Test
	public void shouldUseRegistryForNullRegionType()
	{
		whenRegionHandlesTypeValues((String) null);

		assertThat(obj.isRegionRequiresRegistry(region)).isTrue();
	}

	@Test
	public void shouldUseRegistryForEmptyRegionType()
	{
		whenRegionHandlesTypeValues("");

		assertThat(obj.isRegionRequiresRegistry(region)).isTrue();
	}

	private void whenRegionHandlesTypes(final RegionType ... regionTypes)
	{
		final String[] types = Arrays.stream(regionTypes).map(RegionType::value).toArray(String[]::new);
		whenRegionHandlesTypeValues(types);
	}

	private void whenRegionHandlesTypeValues(final String ... types)
	{
		Mockito.when(region.getHandledTypes()).thenReturn(types);
	}

}
