package de.hybris.platform.media.storage.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;

@UnitTest
public class MediaCacheRegionTest
{
	@Test
	public void shouldCalculateMaxEntriesWhenSize1MBAndBlockSize1KB()
	{
		////cache size is 1MB, block size is 1KB => there should be 1024 entries in cache
		testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(1, 1024, 1024);
	}

	@Test
	public void shouldCalculateMaxEntriesWhenSize1MBAndBlockSize1B()
	{
		//cache size is 1MB, block size is 1B => there should be 1024*1024 entries in cache
		testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(1, 1, 1024 * 1024);
	}

	@Test
	public void shouldCalculateMaxEntriesWhenSize7MBAndBlockSize1B()
	{
		//cache size is 7MB, block size is 1B => there should be 7*1024*1024 entries in cache
		testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(7, 1, 7 * 1024 * 1024);
	}

	@Test
	public void shouldCalculateMaxEntriesWhenSize13MBAndBlockSize4KB()
	{
		//cache size is 13MB, block size is 4KB => there should be 3328 entries in cache (13*1024*1024/4096)
		testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(13, 4096, 3328);
	}

	@Test
	public void shouldCalculateMaxEntriesWhenSize1MBAndBlockSize997B()
	{
		//cache size is 1MB, block size is 997B => there should be 1051 entries in cache (ceil(1024*1024/997) = 1052)
		testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(1, 997, 1052);
	}

	@Test
	public void shouldCalculateMaxEntriesWhenExpectedValueMightBeBiggerThanMaxInteger()
	{
		//cache size is 1MB, block size is 997B => there should be 1051 entries in cache (floor(1024*1024/997) = 1051)
		testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
	}

	private void testCalculateMaxEntriesBasedOnProvidedCacheSizeAndBlockSize(final int sizeInMb, final int blockSizeInB,
	                                                                         final int expectedCacheMaxEntries)
	{
		final MediaCacheRegion mediaCacheRegion = new MediaCacheRegion("test", sizeInMb, "cache", true, blockSizeInB);
		assertThat(mediaCacheRegion.getCacheMaxEntries()).isEqualTo(expectedCacheMaxEntries);
	}


}
