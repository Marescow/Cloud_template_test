/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.cache;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Constants;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloTypeCacheUnit;
import de.hybris.platform.jalo.SearchResult;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jdbcwrapper.JdbcTestSupport;
import de.hybris.platform.jdbcwrapper.JdbcTestSupport.JdbcStatistics;
import de.hybris.platform.regioncache.key.CacheKey;
import de.hybris.platform.regioncache.key.legacy.LegacyCacheKey;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.regioncache.region.RegionType;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.typesystem.PlatformStringUtils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.slf4j.LoggerFactory;

@IntegrationTest
public class TypeSystemQueryCacheRegionTest extends ServicelayerBaseTest
{
	private static final double TS_QUERY_CACHE_REGION_OVERFILL_FACTOR = 0.8;
	private final JdbcStatistics jdbcStatistics = JdbcTestSupport.createNewJdbcStatistics();
	@Resource
	private CacheRegion typeSystemQueryCacheRegion;

	@After
	public void tearDown() throws Exception
	{
		jdbcStatistics.detach();
	}

	@Test
	public void shouldUseTypeSystemQueryCacheWhenGettingAttributeDescriptors()
	{
		final String additionalKey = "descriptorMap";
		final List<ComposedType> composedTypes = getComposedTypes();

		typeSystemQueryCacheRegion.clearCache();

		final long itemsInCacheBefore = getItemsCountInCache(Constants.TC.AttributeDescriptor, additionalKey);

		composedTypes.forEach(ComposedType::getAttributeDescriptors);

		final long itemsInCacheAfter = getItemsCountInCache(Constants.TC.AttributeDescriptor, additionalKey);
		assertThat(itemsInCacheAfter).isGreaterThanOrEqualTo(itemsInCacheBefore + composedTypes.size());
		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	@Test
	public void shouldUseCachedAttributeDescriptorsOnceCacheIsPopulated()
	{
		final List<ComposedType> composedTypes = getComposedTypes();
		Registry.getCurrentTenantNoFallback().getCache().clear();
		jdbcStatistics.attachToCurrentThread();

		composedTypes.forEach(ComposedType::getAttributeDescriptors);

		jdbcStatistics.clear();

		composedTypes.forEach(ComposedType::getAttributeDescriptors);

		jdbcStatistics.assertThat()
		              .selectStatements()
		              .filteredOn(sql -> containsIgnoreCase(sql, "attributeDescriptors"))
		              .isEmpty();
		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	@Test
	public void shouldUseTypeSystemQueryCacheWhenGettingSubTypes()
	{
		final String additionalKey = ComposedType.SUBTYPES;
		final List<ComposedType> composedTypes = getComposedTypes();

		Registry.getCurrentTenantNoFallback().getCache().clear();

		final long itemsInCacheBefore = getItemsCountInCache(Constants.TC.ComposedType, additionalKey);

		composedTypes.forEach(ComposedType::getSubTypes);

		final long itemsInCacheAfter = getItemsCountInCache(Constants.TC.ComposedType, additionalKey);
		assertThat(itemsInCacheAfter).isGreaterThanOrEqualTo(itemsInCacheBefore + composedTypes.size());
		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	@Test
	public void shouldUseCachedSubTypesOnceCacheIsPopulated()
	{
		final List<ComposedType> composedTypes = getComposedTypes();

		jdbcStatistics.attachToCurrentThread();

		Registry.getCurrentTenantNoFallback().getCache().clear();
		composedTypes.forEach(ComposedType::getSubTypes);

		jdbcStatistics.clear();

		composedTypes.forEach(ComposedType::getSubTypes);

		jdbcStatistics.assertThat()
		              .selectStatements()
		              .filteredOn(sql -> containsIgnoreCase(sql, "ComposedTypes"))
		              .isEmpty();
		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	@Test
	public void shouldUseTypeSystemQueryCacheWhenGettingAllSubTypes()
	{
		final String additionalKey = "all" + ComposedType.SUBTYPES;
		final List<ComposedType> composedTypes = getComposedTypes();

		Registry.getCurrentTenantNoFallback().getCache().clear();

		final long itemsInCacheBefore = getItemsCountInCache(Constants.TC.ComposedType, additionalKey);

		composedTypes.forEach(ComposedType::getAllSubTypes);

		final long itemsInCacheAfter = getItemsCountInCache(Constants.TC.ComposedType, additionalKey);
		assertThat(itemsInCacheAfter).isGreaterThanOrEqualTo(itemsInCacheBefore + composedTypes.size());
		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	@Test
	public void shouldUseCachedAllSubTypesOnceCacheIsPopulated()
	{
		final List<ComposedType> composedTypes = getComposedTypes();

		jdbcStatistics.attachToCurrentThread();

		Registry.getCurrentTenantNoFallback().getCache().clear();
		composedTypes.forEach(ComposedType::getAllSubTypes);

		jdbcStatistics.clear();

		composedTypes.forEach(ComposedType::getAllSubTypes);

		jdbcStatistics.assertThat()
		              .selectStatements()
		              .filteredOn(sql -> containsIgnoreCase(sql, "ComposedTypes"))
		              .isEmpty();
		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	@Test
	public void shouldHaveEnoughCacheSizeToStoreAllTypeSystemData()
	{
		final List<ComposedType> composedTypes = getComposedTypes();

		composedTypes.forEach(composedType -> {
			composedType.getSubTypes();
			composedType.getAllSubTypes();
			composedType.getAttributeDescriptors();
		});

		assertTypeSystemQueryCacheRegionIsNotOverfilled();
	}

	private void assertTypeSystemQueryCacheRegionIsNotOverfilled()
	{
		final int size = typeSystemQueryCacheRegion.getAllKeys().size();
		LoggerFactory.getLogger(TypeSystemQueryCacheRegionTest.class)
		             .info("typeSystemQueryCacheRegion after loading all data: {}", size);
		assertThat(size).isLessThan((int) (Config.getInt("regioncache.typesystemquerycacheregion.size", 0) * TS_QUERY_CACHE_REGION_OVERFILL_FACTOR));
	}

	private List<ComposedType> getComposedTypes()
	{
		return ((SearchResult<ComposedType>) jaloSession.getFlexibleSearch()
		                                                .search("GET {ComposedType}", ComposedType.class)).getResult();
	}

	private long getItemsCountInCache(final int typeCode, final String additionalKey)
	{
		return typeSystemQueryCacheRegion
				.getAllKeys()
				.stream()
				.filter(cacheKey -> cacheKey instanceof JaloTypeCacheUnit.JaloTypeCacheKey)
				.filter(cacheKey -> RegionType.TYPE_SYSTEM_QUERY_CACHE_TYPE.value()
				                                                           .equals(cacheKey.getTypeCode()))
				.filter(cacheKey -> PlatformStringUtils.valueOf(typeCode)
				                                       .equals(((JaloTypeCacheUnit.JaloTypeCacheKey) cacheKey)
						                                       .getDependentTypes()[0].getTypeCodeString()))
				.filter(cacheKey -> getAdditionalKey(cacheKey).map(s -> s.startsWith(additionalKey)).orElse(false))
				.count();
	}

	private static Optional<String> getAdditionalKey(final CacheKey cacheKey)
	{
		return Optional.of((JaloTypeCacheUnit.JaloTypeCacheKey) cacheKey)
		               .map(LegacyCacheKey::getLegacyKey)
		               .filter(objects -> Array.getLength(objects) >= 3)
		               .map(objects -> objects[3])
		               .map(String::valueOf);
	}
}
