/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.media.storage.impl;

import static de.hybris.platform.media.storage.impl.DefaultMediaCacheCleanupService.DEFAULT_PERIOD_IN_MINUTES;
import static de.hybris.platform.media.storage.impl.DefaultMediaCacheCleanupService.EVICTED_FILE_MARKER;
import static de.hybris.platform.media.storage.impl.DefaultMediaCacheCleanupService.PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES;
import static de.hybris.platform.media.storage.impl.DefaultMediaCacheCleanupService.PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.suspend.SuspendResumeService;
import de.hybris.platform.core.suspend.SystemStatus;
import de.hybris.platform.media.storage.DefaultMediaStorageConfig;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.MediaStorageRegistry;
import de.hybris.platform.media.storage.MediaStorageStrategy;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.testframework.BulkPropertyConfigSwitcher;
import de.hybris.platform.testframework.assertions.assertj.TestLogListenerAssert;
import de.hybris.platform.testframework.log.TestLogListener;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.MediaUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integration tests for the {@link DefaultMediaCacheCleanupService}.
 */
@IntegrationTest
public class DefaultMediaCacheCleanupServiceTest extends ServicelayerBaseTest
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultMediaCacheCleanupServiceTest.class);
	private static final File LOCAL_STORAGE_DATA_DIR = MediaUtil.getLocalStorageDataDir();
	private static final String FOLDER_SPECIFIC_CACHE = "myFolderCache";
	private static final Path SPECIFIC_FOLDER_CACHE_PATH = Path.of(LOCAL_STORAGE_DATA_DIR + "/" + FOLDER_SPECIFIC_CACHE);
	private static final String PROPERTY_MEDIA_DEFAULT_LOCAL_CACHE = "media.default.local.cache";
	public static final String BIN_FILE_EXTENSION = ".bin";

	private static String defaultCache;
	private static Path defaultCachePath;

	private final TestLogListener testLogListener = new TestLogListener();
	private final BulkPropertyConfigSwitcher bulkPropertyConfigSwitcher = new BulkPropertyConfigSwitcher();
	private boolean restartScheduler = false;

	@Resource(name = "defaultMediaCacheCleanupService")
	private DefaultMediaCacheCleanupService mediaCacheCleanupService;
	@Mock
	private SuspendResumeService suspendResumeService;
	@Mock
	private MediaStorageRegistry storageRegistry;
	@Mock
	private MediaStorageConfigService storageConfigService;
	@Mock
	private MediaStorageConfigService.MediaFolderConfig folderConfig;
	private MockitoSession mockito;

	@Before
	public void setUp() throws IOException
	{
		defaultCache = "tempDir_" + UUID.randomUUID();
		defaultCachePath = Files.createDirectories(LOCAL_STORAGE_DATA_DIR.toPath().resolve(defaultCache));
		mockito = mockitoSession().initMocks(this).startMocking();
		testLogListener.attach();
	}

	@After
	public void tearDown()
	{
		cleanUpCache();
		mockito.finishMocking();
		testLogListener.detach();
		bulkPropertyConfigSwitcher.switchAllBack();

		if (!mediaCacheCleanupService.isSchedulerRunning() && restartScheduler)
		{
			mediaCacheCleanupService.startScheduler(mediaCacheCleanupService.getCleanupPeriod());
		}
	}

	private void stopDefaultScheduler()
	{
		if (mediaCacheCleanupService.isSchedulerRunning())
		{
			mediaCacheCleanupService.stopScheduler();
			restartScheduler = true;
		}
	}

	@Test
	public void shouldNotStartSchedulerWhenLocalCachingIsDisabledForNonLocalStrategy()
	{
		stopDefaultScheduler();

		// given
		setupMockStorageConfigForNonLocal(false);
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_DEFAULT_LOCAL_CACHE, "true");
		assertThat(MediaCacheUtils.getRemoteStorageFolderConfigs(storageRegistry, storageConfigService)).isEmpty();

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());

		// when
		testMediaCacheCleanupService.init();

		// then
		assertThat(testMediaCacheCleanupService.isSchedulerRunning()).isFalse();
		verify(testMediaCacheCleanupService, never()).startScheduler(any(Duration.class));

		final String expectedLogMessage = "Local file caching is not configured for either the default strategy " +
				"or any folder-specific configuration. Cache cleanup will not be started";
		TestLogListenerAssert.assertThat(testLogListener)
		                     .hasLog()
		                     .withMessageContaining(expectedLogMessage)
		                     .loggedFrom(DefaultMediaCacheCleanupService.class)
		                     .withLogLevel(TestLogListenerAssert.LogLevel.WARN)
		                     .occurrences(1);

	}

	@Test
	public void shouldNotStartSchedulerForDefaultLocalStorageStrategyAndNoRemoteStorageFolders()
	{
		stopDefaultScheduler();

		// given
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "true");
		given(storageConfigService.getDefaultStrategyId()).willReturn("testLocalStrategy");
		given(storageRegistry.getStorageStrategies()).willReturn(
				Map.of("testLocalStrategy", new LocalFileMediaStorageStrategy()));
		assertThat(MediaCacheUtils.getRemoteStorageFolderConfigs(storageRegistry, storageConfigService)).isEmpty();

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());

		// when
		testMediaCacheCleanupService.init();

		// then
		assertThat(testMediaCacheCleanupService.isSchedulerRunning()).isFalse();
		verify(testMediaCacheCleanupService, never()).startScheduler(any(Duration.class));

		final String expectedLogMessage = "Local file caching is not configured for either the default strategy " +
				"or any folder-specific configuration. Cache cleanup will not be started";
		TestLogListenerAssert.assertThat(testLogListener)
		                     .hasLog()
		                     .withMessageContaining(expectedLogMessage)
		                     .loggedFrom(DefaultMediaCacheCleanupService.class)
		                     .withLogLevel(TestLogListenerAssert.LogLevel.WARN)
		                     .occurrences(1);
	}

	@Test
	public void shouldStartSchedulerForRemoteStorageFoldersEvenIfDefaultStrategyIsLocal()
	{
		stopDefaultScheduler();

		// given
		given(storageConfigService.getDefaultStrategyId()).willReturn("testLocalStrategy");
		given(storageRegistry.getStorageStrategies()).willReturn(
				Map.of("testLocalStrategy", new LocalFileMediaStorageStrategy(), "testFolderStrategy",
						new TestMediaStorageStrategy()));

		given(folderConfig.isLocalCacheEnabled()).willReturn(true);
		given(folderConfig.getParameter(
				DefaultMediaStorageConfigService.DefaultSettingKeys.LOCAL_CACHE_ROOT_FOLDER_KEY.getKey())).willReturn(
				FOLDER_SPECIFIC_CACHE);
		given(storageConfigService.getFolderConfigsForStrategy("testFolderStrategy")).willReturn(List.of(folderConfig));

		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES, "10");

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());

		try
		{
			// when
			testMediaCacheCleanupService.init();

			// then
			assertThat(testMediaCacheCleanupService.isSchedulerRunning()).isTrue();
			verify(testMediaCacheCleanupService, atLeastOnce()).startScheduler(Duration.ofMinutes(10));
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldStartSchedulerWhenPropertyIsEnabledAndWithConfiguredPeriod()
	{
		stopDefaultScheduler();

		// given
		setupMockStorageConfigForNonLocal(true);
		// default "false" settings will be overridden by global settings
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_DEFAULT_LOCAL_CACHE, "false");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES, "10");

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());

		try
		{
			// when
			testMediaCacheCleanupService.init();

			// then
			assertThat(testMediaCacheCleanupService.isSchedulerRunning()).isTrue();
			verify(testMediaCacheCleanupService, atLeastOnce()).startScheduler(Duration.ofMinutes(10));
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldStartSchedulerWithDefaultPeriodWhenPeriodPropertyNotSet()
	{
		stopDefaultScheduler();

		// given
		setupMockStorageConfigForNonLocal(null);
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_DEFAULT_LOCAL_CACHE, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES, "");
		assertThat(Config.getParameter(PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES)).isEmpty();

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());

		try
		{
			// when
			testMediaCacheCleanupService.init();

			// then
			assertThat(testMediaCacheCleanupService.isSchedulerRunning()).isTrue();
			verify(testMediaCacheCleanupService, atLeastOnce()).startScheduler(Duration.ofMinutes(DEFAULT_PERIOD_IN_MINUTES));
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldNotStartSchedulerWhenCleanUpPropertyIsDisabled()
	{
		stopDefaultScheduler();

		// given
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_DEFAULT_LOCAL_CACHE, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "false");

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());

		// when
		testMediaCacheCleanupService.init();

		// then
		assertThat(testMediaCacheCleanupService.isSchedulerRunning()).isFalse();
		verify(testMediaCacheCleanupService, never()).startScheduler(any(Duration.class));
	}

	@Test
	public void shouldLogWarningWhenCleanupIsDisabledAndCleanupPeriodIsSet()
	{
		stopDefaultScheduler();

		// given
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_DEFAULT_LOCAL_CACHE, "true");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES, "false");
		bulkPropertyConfigSwitcher.switchToValue(PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES, "10");

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = createMediaCacheCleanupService();

		try
		{
			// when
			testMediaCacheCleanupService.init();

			// then
			final String expectedLogMessage = "Property '" + PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES
					+ "' should not be used when '" + PROPERTY_MEDIA_CACHE_CLEANUP_EVICTED_FILES + "' is false. Property '"
					+ PROPERTY_MEDIA_CACHE_CLEANUP_PERIOD_MINUTES + "' will be ignored and scheduler will not be started.";
			TestLogListenerAssert.assertThat(testLogListener)
			                     .hasLog()
			                     .withMessageContaining(expectedLogMessage)
			                     .loggedFrom(DefaultMediaCacheCleanupService.class)
			                     .withLogLevel(TestLogListenerAssert.LogLevel.WARN)
			                     .occurrences(1);
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldRemoveEvictedFilesFromDefaultCacheFolder() throws IOException
	{
		stopDefaultScheduler();

		// given
		setUpMockStorageConfigForDefault();
		given(suspendResumeService.getSystemStatus()).willReturn(SystemStatus.RUNNING);

		final Path dir1 = Path.of(defaultCachePath + "/folder1");
		Files.createDirectories(dir1);
		final Path dir2 = Path.of(defaultCachePath + "/folder2");
		Files.createDirectories(dir2);

		final Path singleCachedFile = Files.createTempFile(dir1, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path cachedFile = Files.createTempFile(dir1, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path evictionMarker = Files.createFile(Path.of(cachedFile + EVICTED_FILE_MARKER));
		final Path singleEvictionMarker = Files.createTempFile(dir2, randomAlphabetic(10),
				BIN_FILE_EXTENSION + EVICTED_FILE_MARKER);
		assertThat(cachedFilesOnDisk(defaultCachePath)).hasSize(2);
		assertThat(evictionMarkerFilesOnDisk(defaultCachePath)).hasSize(2);

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());
		doReturn(true).when(testMediaCacheCleanupService).isCacheEnabledForDefault();

		try
		{
			// when
			testMediaCacheCleanupService.startScheduler(Duration.ofMillis(5));

			// then
			Awaitility.await().atMost(200, TimeUnit.MILLISECONDS).pollInSameThread().until(() -> {
				final boolean onlyOneCachedFile = cachedFilesOnDisk(defaultCachePath).size() == 1;
				final boolean noEvictionMarkers = evictionMarkerFilesOnDisk(defaultCachePath).isEmpty();
				return onlyOneCachedFile && noEvictionMarkers;
			});

			assertThat(singleCachedFile).exists();
			assertThat(cachedFile).doesNotExist();
			assertThat(evictionMarker).doesNotExist();
			assertThat(singleEvictionMarker).doesNotExist();
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldRemoveEvictedFilesFromFolderSpecificCache() throws IOException
	{
		stopDefaultScheduler();

		// given
		given(suspendResumeService.getSystemStatus()).willReturn(SystemStatus.RUNNING);
		final Path dir1 = Path.of(SPECIFIC_FOLDER_CACHE_PATH + "/folder1");
		Files.createDirectories(dir1);
		final Path dir2 = Path.of(SPECIFIC_FOLDER_CACHE_PATH + "/folder2");
		Files.createDirectories(dir2);

		final Path singleCachedFile = Files.createTempFile(dir1, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path singleEvictionMarker = Files.createTempFile(dir1, randomAlphabetic(10),
				BIN_FILE_EXTENSION + EVICTED_FILE_MARKER);
		final Path cachedFile = Files.createTempFile(dir2, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path evictionMarker = Files.createFile(Path.of(cachedFile + EVICTED_FILE_MARKER));
		assertThat(cachedFilesOnDisk(SPECIFIC_FOLDER_CACHE_PATH)).hasSize(2);
		assertThat(evictionMarkerFilesOnDisk(SPECIFIC_FOLDER_CACHE_PATH)).hasSize(2);

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());
		doReturn(false).when(testMediaCacheCleanupService).isCacheEnabledForDefault();
		setUpFolderCacheSettings(testMediaCacheCleanupService);

		try
		{
			// when
			testMediaCacheCleanupService.startScheduler(Duration.ofMillis(5));

			// then
			Awaitility.await().atMost(200, TimeUnit.MILLISECONDS).pollInSameThread().until(() -> {
				final boolean onlyOneCachedFile = cachedFilesOnDisk(SPECIFIC_FOLDER_CACHE_PATH).size() == 1;
				final boolean noEvictionMarkers = evictionMarkerFilesOnDisk(SPECIFIC_FOLDER_CACHE_PATH).isEmpty();
				return onlyOneCachedFile && noEvictionMarkers;
			});

			assertThat(singleCachedFile).exists();
			assertThat(singleEvictionMarker).doesNotExist();
			assertThat(cachedFile).doesNotExist();
			assertThat(evictionMarker).doesNotExist();
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldLogNumberOfRemovedFilesAndFreedUpSpace() throws IOException
	{
		stopDefaultScheduler();

		// given
		given(suspendResumeService.getSystemStatus()).willReturn(SystemStatus.RUNNING);
		create5CachedFilesAnd5EvictionMarkersInFolder(Path.of(SPECIFIC_FOLDER_CACHE_PATH + "/someFolder"));

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());
		doReturn(false).when(testMediaCacheCleanupService).isCacheEnabledForDefault();
		setUpFolderCacheSettings(testMediaCacheCleanupService);

		try
		{
			// when
			testMediaCacheCleanupService.startScheduler(Duration.ofMillis(5));

			// then
			Awaitility.await().atMost(200, TimeUnit.MILLISECONDS).pollInSameThread().until(() -> {
				final boolean noCachedFilesInFolder = cachedFilesOnDisk(SPECIFIC_FOLDER_CACHE_PATH).isEmpty();
				final boolean noEvictionMarkersInFolder = evictionMarkerFilesOnDisk(SPECIFIC_FOLDER_CACHE_PATH).isEmpty();
				return noCachedFilesInFolder && noEvictionMarkersInFolder;
			});

			final String expectedLogMessage = "Removed 5 cached files and 5 eviction markers from '"
					+ SPECIFIC_FOLDER_CACHE_PATH + "' directory. Freed space: 5 KB.";

			TestLogListenerAssert.assertThat(testLogListener)
			                     .hasLog()
			                     .withMessageContaining(expectedLogMessage)
			                     .loggedFrom(DefaultMediaCacheCleanupService.class)
			                     .withLogLevel(TestLogListenerAssert.LogLevel.INFO)
			                     .occurrences(1);
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldRemoveFilesOnlyFromFolderSpecificCache() throws IOException
	{
		stopDefaultScheduler();

		// given
		final Path dir1 = Path.of(defaultCachePath + "/folder1");
		Files.createDirectories(dir1);
		final Path dir2 = Path.of(SPECIFIC_FOLDER_CACHE_PATH + "/folder2");
		Files.createDirectories(dir2);

		final Path cachedFileDefaultCache = Files.createTempFile(dir1, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path evictionMarkerDefaultCache = Files.createFile(Path.of(cachedFileDefaultCache + EVICTED_FILE_MARKER));
		assertThat(cachedFileDefaultCache).exists();
		assertThat(evictionMarkerDefaultCache).exists();

		final Path cachedFileFolderSpecific = Files.createTempFile(dir2, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path evictionMarkerFolderSpecific = Files.createFile(Path.of(cachedFileFolderSpecific + EVICTED_FILE_MARKER));
		assertThat(cachedFileFolderSpecific).exists();
		assertThat(evictionMarkerFolderSpecific).exists();

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());
		doReturn(false).when(testMediaCacheCleanupService).isCacheEnabledForDefault();
		setUpFolderCacheSettings(testMediaCacheCleanupService);

		try
		{
			// when
			testMediaCacheCleanupService.removeEvictedFiles();

			// then
			assertThat(cachedFileDefaultCache).exists();
			assertThat(evictionMarkerDefaultCache).exists();
			assertThat(cachedFileFolderSpecific).doesNotExist();
			assertThat(evictionMarkerFolderSpecific).doesNotExist();
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldNotRemoveFilesWhenLocalCachingIsNotEnabled() throws IOException
	{
		stopDefaultScheduler();

		// given
		final Path dir1 = Path.of(defaultCachePath + "/folder1");
		Files.createDirectories(dir1);

		final Path cachedFile = Files.createTempFile(dir1, randomAlphabetic(10), BIN_FILE_EXTENSION);
		final Path evictionMarker = Files.createFile(Path.of(cachedFile + EVICTED_FILE_MARKER));
		assertThat(cachedFile).exists();
		assertThat(evictionMarker).exists();

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());
		doReturn(false).when(testMediaCacheCleanupService).isCacheEnabledForDefault();

		try
		{
			// when
			testMediaCacheCleanupService.removeEvictedFiles();

			// then
			assertThat(cachedFile).exists();
			assertThat(evictionMarker).exists();

			final String expectedLogMessage = "Local file caching is not configured for either the default strategy " +
					"or any folder-specific configuration. Cache cleanup will not be started";
			TestLogListenerAssert.assertThat(testLogListener)
			                     .hasLog()
			                     .withMessageContaining(expectedLogMessage)
			                     .loggedFrom(DefaultMediaCacheCleanupService.class)
			                     .withLogLevel(TestLogListenerAssert.LogLevel.WARN)
			                     .occurrences(1);
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	@Test
	public void shouldLogFileRemovalException() throws IOException
	{
		// given
		stopDefaultScheduler();

		create5CachedFilesAnd5EvictionMarkersInFolder(defaultCachePath);

		final DefaultMediaCacheCleanupService testMediaCacheCleanupService = spy(createMediaCacheCleanupService());
		doReturn(true).when(testMediaCacheCleanupService).isCacheEnabledForDefault();
		setUpMockStorageConfigForDefault();

		try (final MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class))
		{
			final String exceptionMessage = "Test exception";
			final IOException exception = new IOException(exceptionMessage);

			mockedStatic.when(() -> FileUtils.delete(any())).thenThrow(exception);

			// when
			testMediaCacheCleanupService.removeEvictedFiles();

			// then
			TestLogListenerAssert.assertThat(testLogListener)
			                     .withMessageContaining(String.valueOf(exception.getMessage()))
			                     .withLogLevel(TestLogListenerAssert.LogLevel.ERROR)
			                     .occurrences(10);
		}
		finally
		{
			testMediaCacheCleanupService.stopScheduler();
		}
	}

	private DefaultMediaCacheCleanupService createMediaCacheCleanupService()
	{
		return new DefaultMediaCacheCleanupService(storageRegistry, storageConfigService, LOCAL_STORAGE_DATA_DIR,
				suspendResumeService);
	}

	private void setupMockStorageConfigForNonLocal(final Boolean localCache)
	{
		given(storageConfigService.getDefaultStrategyId()).willReturn("someNonLocalStrategy");
		given(storageRegistry.getStorageStrategies()).willReturn(Map.of("someNonLocalStrategy", new TestMediaStorageStrategy()));

		if (localCache == null)
		{
			final DefaultMediaStorageConfig emptyGlobalSettings = new DefaultMediaStorageConfig(Map.of());
			given(storageConfigService.getGlobalSettingsForStrategy("someNonLocalStrategy")).willReturn(emptyGlobalSettings);
		}
		else
		{
			final DefaultMediaStorageConfig globalSettings = new DefaultMediaStorageConfig(Map.of("local.cache", localCache));
			given(storageConfigService.getGlobalSettingsForStrategy("someNonLocalStrategy")).willReturn(globalSettings);
		}
	}

	private void setUpMockStorageConfigForDefault()
	{
		given(storageConfigService.getDefaultCacheFolderName()).willReturn(defaultCache);
		given(storageConfigService.getDefaultCacheBlockSize()).willReturn(4096);
	}

	private void setUpFolderCacheSettings(final DefaultMediaCacheCleanupService mediaCacheCleanupService)
	{
		given(folderConfig.getParameter(DefaultMediaStorageConfigService.DefaultSettingKeys.LOCAL_CACHE_BLOCK_SIZE.getKey(),
				Integer.class)).willReturn(1024);
		given(mediaCacheCleanupService.getRemoteFolderConfigs()).willReturn(Map.of(FOLDER_SPECIFIC_CACHE, folderConfig));
	}

	private void create5CachedFilesAnd5EvictionMarkersInFolder(final Path path) throws IOException
	{
		Files.createDirectories(path);
		for (int i = 0; i < 5; i++)
		{
			final Path cachedFile = Files.createTempFile(path, randomAlphabetic(10), BIN_FILE_EXTENSION);
			Files.write(cachedFile, new byte[1024]);
			Files.createFile(Path.of(cachedFile + EVICTED_FILE_MARKER));
		}

		assertThat(cachedFilesOnDisk(path)).hasSize(5);
		assertThat(evictionMarkerFilesOnDisk(path)).hasSize(5);
	}

	private List<Path> cachedFilesOnDisk(final Path cacheFolderPath) throws IOException
	{
		try (final Stream<Path> paths = Files.walk(cacheFolderPath))
		{
			return paths.filter(path -> path.toString().endsWith(BIN_FILE_EXTENSION)).toList();
		}
	}

	private List<Path> evictionMarkerFilesOnDisk(final Path cacheFolderPath) throws IOException
	{
		try (final Stream<Path> paths = Files.walk(cacheFolderPath))
		{
			return paths.filter(path -> path.toString().endsWith(BIN_FILE_EXTENSION + EVICTED_FILE_MARKER)).toList();
		}
	}

	private void cleanUpCache()
	{
		try
		{
			FileUtils.deleteDirectory(defaultCachePath.toFile());
			FileUtils.deleteDirectory(SPECIFIC_FOLDER_CACHE_PATH.toFile());
			assertThat(defaultCachePath).doesNotExist();
			assertThat(SPECIFIC_FOLDER_CACHE_PATH).doesNotExist();
		}
		catch (final IOException e)
		{
			LOG.error("Cannot clean out testing cache directory");
		}
	}

	private static class TestMediaStorageStrategy implements MediaStorageStrategy
	{
		@Override
		public StoredMediaData store(final MediaStorageConfigService.MediaFolderConfig config, final String mediaId,
		                             final Map<String, Object> metaData, final InputStream dataStream)
		{
			return null;
		}

		@Override
		public void delete(final MediaStorageConfigService.MediaFolderConfig config, final String location)
		{

		}

		@Override
		public InputStream getAsStream(final MediaStorageConfigService.MediaFolderConfig config, final String location)
		{
			return null;
		}

		@Override
		public File getAsFile(final MediaStorageConfigService.MediaFolderConfig config, final String location)
		{
			return null;
		}

		@Override
		public long getSize(final MediaStorageConfigService.MediaFolderConfig config, final String location)
		{
			return 0;
		}
	}
}
