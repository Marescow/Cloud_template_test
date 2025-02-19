/*
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.media.storage.impl;

import static de.hybris.platform.media.storage.MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
import static de.hybris.platform.media.storage.impl.DefaultMediaStorageConfigService.DefaultSettingKeys.LOCAL_CACHE_BLOCK_SIZE;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.MediaStorageConfigService.MediaFolderConfig;
import de.hybris.platform.media.storage.MediaStorageRegistry;
import de.hybris.platform.media.storage.MediaStorageStrategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.Silent.class)
@UnitTest
public class MediaCacheUtilsTest
{
	@Mock
	private MediaStorageRegistry storageRegistry;

	@Mock
	private MediaStorageConfigService storageConfigService;

	@Mock
	private MediaStorageStrategy remoteStorageStrategy;

	@Mock
	private LocalFileMediaStorageStrategy localStorageStrategy;

	@Mock
	private MediaFolderConfig remoteMediaFolderConfig;

	@Mock
	private MediaFolderConfig localMediaFolderConfig;

	@Test
	public void shouldReturnOnlyRemoteFolders()
	{
		// given
		final Map<String, MediaStorageStrategy> strategies = new HashMap<>();
		final Set<MediaFolderConfig> remoteFolderConfigs = new HashSet<>();
		final Set<MediaFolderConfig> localFolderConfigs = new HashSet<>();

		strategies.put("remoteStrategy", remoteStorageStrategy);
		strategies.put("localStrategy", localStorageStrategy);
		when(storageRegistry.getStorageStrategies()).thenReturn(strategies);

		remoteFolderConfigs.add(remoteMediaFolderConfig);
		when(storageConfigService.getFolderConfigsForStrategy("remoteStrategy")).thenReturn(remoteFolderConfigs);

		localFolderConfigs.add(localMediaFolderConfig);
		when(storageConfigService.getFolderConfigsForStrategy("localStrategy")).thenReturn(localFolderConfigs);

		// when
		final Iterable<MediaFolderConfig> result = MediaCacheUtils.getRemoteStorageFolderConfigs(storageRegistry,
				storageConfigService);

		assertThat(result).isNotNull();

		final List<MediaFolderConfig> resultList = Lists.newArrayList(result);

		// then
		assertThat(resultList).isNotEmpty();
		assertThat(resultList).hasSize(1);
		assertThat(resultList).contains(remoteMediaFolderConfig);
		assertThat(resultList).doesNotContain(localMediaFolderConfig);
	}

	@Test
	public void shouldReturnDefaultCacheBlockSize()
	{
		// given
		when(remoteMediaFolderConfig.getParameter(LOCAL_CACHE_BLOCK_SIZE.getKey(), Integer.class)).thenReturn(null);
		when(storageConfigService.getDefaultCacheBlockSize()).thenReturn(DEFAULT_BLOCK_SIZE);

		// when
		final int blockSize = MediaCacheUtils.getFolderBlockSize(remoteMediaFolderConfig, storageConfigService);

		// then
		assertThat(blockSize).isEqualTo(DEFAULT_BLOCK_SIZE);
	}

	@Test
	public void shouldReturnFolderCacheBlockSize()
	{
		// given
		when(remoteMediaFolderConfig.getParameter(LOCAL_CACHE_BLOCK_SIZE.getKey(), Integer.class)).thenReturn(4096);
		when(storageConfigService.getDefaultCacheBlockSize()).thenReturn(DEFAULT_BLOCK_SIZE);

		// when
		final int blockSize = MediaCacheUtils.getFolderBlockSize(remoteMediaFolderConfig, storageConfigService);

		// then
		assertThat(blockSize).isEqualTo(4096);
	}
}
