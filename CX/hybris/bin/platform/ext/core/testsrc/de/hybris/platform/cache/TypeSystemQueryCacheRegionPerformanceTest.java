/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.cache;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import de.hybris.bootstrap.annotations.PerformanceTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.SearchResult;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.test.TestThreadsHolder;
import de.hybris.platform.util.Config;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PerformanceTest
public class TypeSystemQueryCacheRegionPerformanceTest extends ServicelayerBaseTest
{
	public static final Logger LOGGER = LoggerFactory.getLogger(TypeSystemQueryCacheRegionPerformanceTest.class);

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@After
	public void tearDown() throws Exception
	{
		Registry.getCurrentTenantNoFallback().getCache().clear();
	}

	@Test
	public void shouldUseTypeSystemQueryCacheWhenGettingAttributeDescriptors()
	{
		final AtomicBoolean run = new AtomicBoolean(true);
		final AtomicLong noiseCounter = new AtomicLong();

		final TestThreadsHolder<Runnable> backgroundNoiseThreads = createBackgroundNoiseThreads(run, noiseCounter);

		final List<ComposedType> composedTypes = getComposedTypes();

		// clear caches
		Registry.getCurrentTenantNoFallback().getCache().clear();

		//load relevant data to warmup caches
		composedTypes.forEach(composedType -> {
			composedType.getAttributeDescriptors();
			composedType.getSubTypes();
			composedType.getAllSubTypes();
		});

		backgroundNoiseThreads.waitForPrepared(10, TimeUnit.SECONDS);
		backgroundNoiseThreads.startAll();

		final SummaryStatistics allSubTypesStatistics = new SummaryStatistics();
		final SummaryStatistics subTypesStatistics = new SummaryStatistics();
		final SummaryStatistics attributeDescriptorsStatistics = new SummaryStatistics();

		final Instant timeThreshold = Instant.now().plus(10, ChronoUnit.MINUTES);
		final long queryCacheSize = Config.getLong("regioncache.querycacheregion.size", 1);
		long lastRound = 0;
		while (Instant.now().isBefore(timeThreshold))
		{
			final long currentRound = Math.floorDiv(noiseCounter.get(),
					queryCacheSize);

			if (currentRound <= lastRound)
			{
				continue;
			}
			LOGGER.info("round {}: start", currentRound);
			execWithStats(allSubTypesStatistics, composedTypes, ComposedType::getAllSubTypes);
			execWithStats(subTypesStatistics, composedTypes, ComposedType::getSubTypes);
			execWithStats(attributeDescriptorsStatistics, composedTypes, ComposedType::getAttributeDescriptors);
			LOGGER.info("round {}: finish", currentRound);

			lastRound = currentRound;
		}
		run.set(false);
		backgroundNoiseThreads.waitForAll(10, TimeUnit.SECONDS);

		LOGGER.info("allSubTypesStatistics - {}", allSubTypesStatistics.getSummary());
		LOGGER.info("subTypesStatistics - {}", subTypesStatistics.getSummary());
		LOGGER.info("attributeDescriptorsStatistics - {}", attributeDescriptorsStatistics.getSummary());
		LOGGER.info("noiseCounter: {}; composedTypes: {}", noiseCounter, composedTypes.size());


	}

	private void execWithStats(final SummaryStatistics stats, final List<ComposedType> composedTypes,
	                           final Consumer<ComposedType> logic)
	{
		final Instant start = Instant.now();
		composedTypes.forEach(logic);
		stats.addValue(Duration.between(start, Instant.now()).toMillis());

	}

/*	private void execWithStats(final SummaryStatistics stats, final List<ComposedType> composedTypes,
	                           final Consumer<List<ComposedType>> logic)
	{

	}*/

	private TestThreadsHolder<Runnable> createBackgroundNoiseThreads(final AtomicBoolean run, final AtomicLong noiseCounter)
	{
		final TestThreadsHolder<Runnable> backgroundNoiseThreads = new TestThreadsHolder<Runnable>(10, threadNumber -> () -> {
			final int luckyNumber = "youAreLucky".length();
			while (run.get())
			{
				flexibleSearchService.search("select PK from {ComposedType} where 'youAreLucky' = ?doYouFeelLuckyPunk",
						Map.of("doYouFeelLuckyPunk", randomAlphabetic(luckyNumber)));
				noiseCounter.incrementAndGet();
			}
		}, true);
		return backgroundNoiseThreads;
	}


	private List<ComposedType> getComposedTypes()
	{
		return ((SearchResult<ComposedType>) jaloSession.getFlexibleSearch()
		                                                .search("GET {ComposedType}", ComposedType.class)).getResult();
	}


}
