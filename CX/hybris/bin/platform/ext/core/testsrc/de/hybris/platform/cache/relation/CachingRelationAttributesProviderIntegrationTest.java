/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.cache.relation;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.persistence.property.TypeInfoMap;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.testframework.BulkPropertyConfigSwitcher;
import de.hybris.platform.util.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import javax.annotation.Resource;

import org.apache.commons.collections4.IterableUtils;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@IntegrationTest
public class CachingRelationAttributesProviderIntegrationTest extends ServicelayerBaseTest
{
	public static final Logger LOGGER = LoggerFactory.getLogger(CachingRelationAttributesProviderIntegrationTest.class);
	private final BulkPropertyConfigSwitcher relationCacheTypesEnabled = new BulkPropertyConfigSwitcher();
	@Resource
	private FlexibleSearchService flexibleSearchService;

	public static boolean areEqual(final RelationAttributes r1, final RelationAttributes r2)
	{

		if (r1 == r2) return true;
		if (r1 == null || r2 == null || r1.getClass() != r2.getClass()) return false;

		if (!Objects.equals(r1.getOwningTypeCode(), r2.getOwningTypeCode())) return false;

		final Map<String, String> r1Attributes = new HashMap<>();
		r1.forEachAttribute(r1Attributes::put);
		final Map<String, String> r2Attributes = new HashMap<>();
		r1.forEachAttribute(r2Attributes::put);

		return Objects.equals(r1Attributes, r2Attributes);
	}

	@After
	public void tearDown() throws Exception
	{
		relationCacheTypesEnabled.switchAllBack();
	}

	@Test
	public void shouldInitCacheAndGiveSameResultsAsNoCache()
	{
		testInitCache();
	}

	@Test
	public void shouldInitCacheAndGiveSameResultsAsNoCacheWithAdditionalRelations()
	{
		relationCacheTypesEnabled.switchToValue(ConfigurableRelationAttributesProvider.typeEnableFlag("OrderDiscountRelation"),
				"true");

		testInitCache();
	}

	private void testInitCache()
	{
		final ConfigurableRelationAttributesProvider noCacheProvider = getNoCacheProvider();

		final AtomicBoolean blocked = new AtomicBoolean(false);

		final CachingRelationAttributesProvider cache = getCachingRelationAttributesProvider(blocked);
		final ConfigurableRelationAttributesProvider cachedProvider = getCachedProvider(cache);

		cache.initCache();

		//everything should be cached
		blocked.set(true);

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("SELECT {PK} from {ComposedType}");
		//flexibleSearchQuery.setResultClassList(List.of(PK.class));
		final SearchResult<ComposedTypeModel> search = flexibleSearchService.search(flexibleSearchQuery);


		for (final var composedType : search.getResult())
		{
			final TypeInfoMap persistenceInfo = Registry.getPersistenceManager().getPersistenceInfo(composedType.getPk());

			final TypeId typeIdFromPk = TypeId.fromTypePk(composedType.getPk());
			testForTypeId(typeIdFromPk, noCacheProvider, cachedProvider, persistenceInfo.getCode());

			final TypeId typeIdFromName = TypeId.fromTypeName(composedType.getCode());
			testForTypeId(typeIdFromName, noCacheProvider, cachedProvider, persistenceInfo.getCode());


			if (persistenceInfo.getItemTypeCode() <= 0)
			{
				continue;
			}
			final TypeId typeIdFromCode = TypeId.fromTypeCode(persistenceInfo.getItemTypeCode());
			testForTypeId(typeIdFromCode, noCacheProvider, cachedProvider, persistenceInfo.getCode());
		}
	}

	private static ConfigurableRelationAttributesProvider getCachedProvider(final CachingRelationAttributesProvider cache)
	{
		return new ConfigurableRelationAttributesProvider(cache::getRelationAttributes, Config::getBoolean);
	}

	private static CachingRelationAttributesProvider getCachingRelationAttributesProvider(final AtomicBoolean blocked)
	{
		final TypeSystemRelationAttributesProvider typeSystemRelationAttributesProvider = new TypeSystemRelationAttributesProvider();
		final TypePKsExtractor typePKsExtractor = new TypePKsExtractor(DefaultRelationsCache.EXCLUDED_TYPE_CODES);

		return new CachingRelationAttributesProvider(
				blockable(typeSystemRelationAttributesProvider::get, blocked),
				blockable(typePKsExtractor::extractTypePKs, blocked));
	}

	private static ConfigurableRelationAttributesProvider getNoCacheProvider()
	{
		final TypeSystemRelationAttributesProvider typeSystemRelationAttributesProvider = new TypeSystemRelationAttributesProvider();
		final TypePKsExtractor typePKsExtractor = new TypePKsExtractor(DefaultRelationsCache.EXCLUDED_TYPE_CODES);

		return new ConfigurableRelationAttributesProvider(
				typeId -> typePKsExtractor.extractTypePKs(typeId).stream().map(typeSystemRelationAttributesProvider::get),
				Config::getBoolean);
	}

	private static void testForTypeId(final TypeId typeId, final ConfigurableRelationAttributesProvider noCacheProvider,
	                                  final ConfigurableRelationAttributesProvider cachedProvider, final String code)
	{
		final List<RelationAttributes> notCachedAttributesWithPk = noCacheProvider.getRelationAttributes(typeId).toList();
		final List<RelationAttributes> cachedAttributesWithP = cachedProvider.getRelationAttributes(typeId).toList();

		assertThat(notCachedAttributesWithPk).isNotNull();
		assertThat(cachedAttributesWithP).as("should return same for %s (%s)", code, typeId)
		                                 .isNotNull()
		                                 .hasSameSizeAs(notCachedAttributesWithPk)
		                                 .allMatch(r1 -> IterableUtils.matchesAll(notCachedAttributesWithPk,
				                                 r2 -> areEqual(r1, r2)));

		cachedAttributesWithP.stream()
		                     .filter(r -> r != RelationAttributes.empty())
		                     .forEach(r -> LOGGER.info("found relation attributes for {} ({}): {}",
				                     code, typeId, r));
	}

	private static <T, R> Function<T, R> blockable(final Function<T, R> delegate, final AtomicBoolean blocked)
	{

		return delegate.compose(t -> {
			if (blocked.get())
			{
				throw new IllegalStateException("blocked, param " + t);
			}
			return t;
		});
	}
}
