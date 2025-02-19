/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.media.storage.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import de.hybris.platform.media.storage.MediaStorageConfigService.MediaFolderConfig;
import de.hybris.platform.media.storage.impl.DefaultMediaStorageConfigService.DefaultSettingKeys;
import de.hybris.platform.regioncache.CacheController;
import de.hybris.platform.regioncache.region.CacheRegion;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.bouncycastle.util.encoders.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
		{ "/test/MediaCacheRegion-context.xml" }, inheritLocations = false)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class MediaCacheRecreatorTest
{
	@Resource
	private CacheController cacheController;
	@Resource
	private String defaultCacheFolderName;
	@Resource
	private String config2CacheFolderName;
	@Resource
	private MediaCacheRecreator cacheRecreator;

	@Mock
	private MediaFolderConfig config1, config2;

	MockitoSession mockito;

	@Before
	public void setUp() throws Exception
	{
		mockito = Mockito.mockitoSession().initMocks(this).strictness(Strictness.LENIENT).startMocking();
		createRandomCachedFiles(10, defaultCacheFolderName);
		createRandomCachedFiles(5, config2CacheFolderName);
		assertThat(cacheController.getRegions()).isNotNull().hasSize(1);
	}

	private void createRandomCachedFiles(final int num, final String folderName) throws IOException
	{
		for (int i = 0; i < num; i++)
		{
			final String location = RandomStringUtils.randomAlphabetic(10);
			final Path tempFile = Files.createTempFile(Paths.get(System.getProperty("java.io.tmpdir"), folderName),
					new String(Base64.encode(
							location.getBytes())) + DefaultLocalMediaFileCacheService.CACHE_FILE_NAME_DELIM, ".bin");

			try(final OutputStream bufferedWriter = new BufferedOutputStream(Files.newOutputStream(tempFile)))
			{
				for (int j = 0; j <= i; j++)
				{
					bufferedWriter.write(RandomUtils.nextBytes(1024));
				}
			}
		}
	}

	@After
	public void tearDown() throws Exception
	{
		mockito.finishMocking();
		cleanCacheFolder(defaultCacheFolderName);
		cleanCacheFolder(config2CacheFolderName);
		getMediaCacheRegion().clearCache();
	}

	private void cleanCacheFolder(final String folderName) throws IOException
	{
		Files.walkFileTree(Paths.get(System.getProperty("java.io.tmpdir"), folderName), new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException
			{
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(final Path file, final IOException exc) throws IOException
			{
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	@Test
	public void shouldRecreateCacheFromExistingCachedFileEntriesUsingDefaultCacheFolder()
	{
		// given
		final List<MediaFolderConfig> configs = Collections.emptyList();

		// when
		cacheRecreator.recreateCache(defaultCacheFolderName, configs);

		// then
		assertThat(getMediaCacheRegion().getMaxReachedSize()).isEqualTo(10);
	}


	@Test
	public void shouldRecreateCacheFromExistingCachedFileEntriesUsingDefaultCacheFolderAndConfiguredFolder()
	{
		// given
		given(config1.isLocalCacheEnabled()).willReturn(Boolean.TRUE);
		given(config2.isLocalCacheEnabled()).willReturn(Boolean.TRUE);
		given(config1.getParameter(DefaultSettingKeys.LOCAL_CACHE_ROOT_FOLDER_KEY.getKey())).willReturn(defaultCacheFolderName);
		given(config2.getParameter(DefaultSettingKeys.LOCAL_CACHE_ROOT_FOLDER_KEY.getKey())).willReturn(config2CacheFolderName);
		given(config1.getParameter(DefaultSettingKeys.LOCAL_CACHE_BLOCK_SIZE.getKey(), Integer.class)).willReturn(1024);
		given(config2.getParameter(DefaultSettingKeys.LOCAL_CACHE_BLOCK_SIZE.getKey(), Integer.class)).willReturn(4096);

		// when
		cacheRecreator.recreateCache(defaultCacheFolderName, Lists.newArrayList(config1, config2));

		// then
		assertThat(getMediaCacheRegion().getMaxReachedSize()).isEqualTo(15);
	}

	@Test
	public void shouldRecreateCacheFromExistingCachedFileEntriesUsingDefaultCacheFolderWhenNoRootCacheFolderDefined()
	{
		// given
		given(config2.isLocalCacheEnabled()).willReturn(Boolean.TRUE);
		given(config2.getParameter(DefaultSettingKeys.LOCAL_CACHE_ROOT_FOLDER_KEY.getKey())).willReturn(null);

		// when
		cacheRecreator.recreateCache(defaultCacheFolderName, Lists.newArrayList(config2));

		// then
		assertThat(getMediaCacheRegion().getMaxReachedSize()).isEqualTo(10);
	}

	@Test
	public void shouldRecreateCacheFromExistingCachedFileEntriesUsingDefaultBlockSizeWhenNoExplicitBlockSizeDefined()
	{
		// given
		given(config2.isLocalCacheEnabled()).willReturn(Boolean.TRUE);
		given(config2.getParameter(DefaultSettingKeys.LOCAL_CACHE_ROOT_FOLDER_KEY.getKey())).willReturn(config2CacheFolderName);
		given(config2.getParameter(DefaultSettingKeys.LOCAL_CACHE_BLOCK_SIZE.getKey(), Integer.class)).willReturn(null);

		// when
		cacheRecreator.recreateCache(defaultCacheFolderName, Lists.newArrayList(config2));

		// then
		assertThat(getMediaCacheRegion().getMaxReachedSize()).isEqualTo(15);
	}

	private CacheRegion getMediaCacheRegion()
	{
		final Optional<CacheRegion> found = cacheController.getRegions()
		                                                   .stream()
		                                                   .filter((Predicate<CacheRegion>) input -> (input instanceof MediaCacheRegion))
		                                                   .findFirst();

		assertThat(found).overridingErrorMessage("Media cache region not found via controller").isPresent();
		return found.orElseThrow();
	}
}
