package de.hybris.platform.media.storage.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.media.storage.LocalMediaFileCacheService;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.MediaStorageRegistry;
import de.hybris.platform.media.storage.impl.DefaultLocalMediaFileCacheService.MediaCacheKey;
import de.hybris.platform.media.storage.impl.DefaultMediaStorageConfigService.DefaultSettingKeys;
import de.hybris.platform.regioncache.DefaultCacheController;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.util.MediaUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

@IntegrationTest
public class LocalMediaCacheIntegrationTest extends ServicelayerBaseTest
{

	private static final String FOLDER_QUALIFIER = "fooBar";
	private final List<Path> filesToDelete = new ArrayList<>();
	private MockitoSession mockitoSession;
	@Mock
	private MediaStorageConfigService.MediaFolderConfig folderConfig;
	@Resource
	private MediaStorageConfigService mediaStorageConfigService;
	@Resource
	private DefaultCacheController cacheController;
	@Resource
	private MediaStorageRegistry mediaStorageRegistry;
	@Resource
	private LocalFileMediaStorageStrategy localFileMediaStorageStrategy;
	private String cacheFolderName;
	private Path mediaDir;
	private Path cacheRoot;

	@Before
	public void setUp() throws Exception
	{
		mockitoSession = Mockito.mockitoSession().initMocks(this).strictness(Strictness.LENIENT).startMocking();

		mediaDir = MediaUtil.getLocalStorageDataDir().toPath();

		cacheFolderName = "testCache_" + RandomStringUtils.randomAlphabetic(5);
		cacheRoot = mediaDir.resolve(cacheFolderName);
		if (Files.notExists(cacheRoot))
		{
			Files.createDirectory(cacheRoot);
		}
	}

	@After
	public void tearDown() throws Exception
	{
		mockitoSession.finishMocking();

		PathUtils.deleteDirectory(cacheRoot);
		filesToDelete.stream().filter(Files::isRegularFile).forEach(path -> {
			try
			{
				Files.deleteIfExists(path);
			}
			catch (final IOException ignore)
			{
			}
		});

	}

	@Test
	public void shouldTake1CacheUnitIfFileIsSmallerThan4KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(4095, 4096, 1);
	}

	@Test
	public void shouldTake1CacheUnitIfFileIsSmallerThan1KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(1023, 1024, 1);
	}

	@Test
	public void shouldTake1CacheUnitIfFileIsEqualTo4KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(4096, 4096, 1);
	}

	@Test
	public void shouldTake1CacheUnitIfFileIsEqualTo1KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(1024, 1024, 1);
	}

	@Test
	public void shouldTake2CacheUnitsIfFileIsGreaterThan4KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(4097, 4 * 1024, 2);
	}

	@Test
	public void shouldTake2CacheUnitsIfFileIsGreaterThan1KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(1025, 1024, 2);
	}

	@Test
	public void shouldTakeManyCacheUnitsIfFileIsMuchGreaterThan1KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(7 * 1024, 1024, 7);
	}


	@Test
	public void shouldTakeManyCacheUnitsIfFileIsMuchGreaterThan4KbBlockSize() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(13 * 1024, 4096, 4);
	}

	private void testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(final int fileSizeInB,
	                                                                                   final int blockSizeInB,
	                                                                                   final int expectedMediaCacheSize)
			throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(fileSizeInB, 1, blockSizeInB,
				expectedMediaCacheSize);
	}

	private void testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(final int fileSizeInB,
	                                                                                   final int cacheSizeInMB,
	                                                                                   final int blockSizeInB,
	                                                                                   final int expectedMediaCacheSize)
			throws IOException
	{
		//given
		givenFolderConfig(folderConfig, blockSizeInB);

		final MediaCacheRegion mediaCacheRegion = createMediaCacheRegion(cacheFolderName, cacheSizeInMB, blockSizeInB);

		final DefaultLocalMediaFileCacheService localMediaFileCacheService = createLocalMediaFileCacheService(mediaCacheRegion);


		final String mediaLocationInStorage = RandomStringUtils.randomNumeric(5);
		final LocalMediaFileCacheService.StreamGetter streamGetter = mockStreamGetter(mediaLocationInStorage, fileSizeInB);

		final File file = localMediaFileCacheService.storeOrGetAsFile(folderConfig, mediaLocationInStorage, streamGetter);

		assertThat(file).isNotNull();
		assertThat(Files.size(file.toPath())).isEqualTo(fileSizeInB);
		final MediaCacheKey mediaCacheKey = createMediaCacheKey(mediaLocationInStorage);
		assertThat(mediaCacheRegion.containsKey(mediaCacheKey)).isTrue();

		assertThat(mediaCacheRegion).extracting("cacheMap").hasOnlyOneElementSatisfying(cacheMap -> {
			assertThat(cacheMap).isInstanceOf(ConcurrentLinkedHashMap.class);
			assertThat(((ConcurrentLinkedHashMap<?, ?>) cacheMap).weightedSize()).isEqualTo(expectedMediaCacheSize);
		});
	}

	@Test
	public void shouldNotStoreFileInCacheIfSizeExceedsCacheSizeWithBlockSize1Kb() throws IOException
	{
		testFileNotStoredInLocalCacheBecauseIsToBig(1024 * 1024 + 1, 1, 1024, 0);
	}

	@Test
	public void shouldNotStoreFileInCacheIfSizeExceedsCacheSizeWithBlockSize4Kb() throws IOException
	{
		testFileNotStoredInLocalCacheBecauseIsToBig(1024 * 1024 + 1, 1, 4096, 0);
	}

	@Test
	public void shouldNotStoreFileInCacheIfSizeExceedsCacheSizeWithBlockSize512B() throws IOException
	{
		testFileNotStoredInLocalCacheBecauseIsToBig(1024 * 1024 + 1, 1, 512, 0);
	}

	@Test
	public void shouldStoreFileInCacheIfSizeDoesNotExceedCacheSizeWithBlockSize1Kb() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(1024 * 1024 - 1, 1, 1024, 1024);
	}

	@Test
	public void shouldStoreFileInCacheIfSizeDoesNotExceedCacheSizeWithBlockSize4Kb() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(1024 * 1024 - 1, 1, 4096, 256);
	}

	@Test
	public void shouldStoreFileInCacheIfSizeDoesNotExceedCacheSizeWithBlockSize512B() throws IOException
	{
		testFileStoredInLocalCacheShouldHaveAppropriateSizeInMediaCacheRegion(1024 * 1024 - 1, 1, 512, 2048);
	}

	private void testFileNotStoredInLocalCacheBecauseIsToBig(final int fileSizeInB, final int cacheSizeInMB,
	                                                         final int blockSizeInB, final int expectedMediaCacheSize)
			throws IOException
	{
		//given
		givenFolderConfig(folderConfig, blockSizeInB);

		final MediaCacheRegion mediaCacheRegion = createMediaCacheRegion(cacheFolderName, cacheSizeInMB, blockSizeInB);
		final DefaultLocalMediaFileCacheService localMediaFileCacheService = createLocalMediaFileCacheService(mediaCacheRegion);

		final String mediaLocationInStorage = RandomStringUtils.randomNumeric(5);
		final LocalMediaFileCacheService.StreamGetter streamGetter = mockStreamGetter(mediaLocationInStorage, fileSizeInB);
		final File file = localMediaFileCacheService.storeOrGetAsFile(folderConfig, mediaLocationInStorage, streamGetter);


		assertThat(file).isNotNull();
		final MediaCacheKey mediaCacheKey = createMediaCacheKey(mediaLocationInStorage);
		assertThat(mediaCacheRegion.containsKey(mediaCacheKey)).isFalse();

		assertThat(mediaCacheRegion).extracting("cacheMap").hasOnlyOneElementSatisfying(cacheMap -> {
			assertThat(cacheMap).isInstanceOf(ConcurrentLinkedHashMap.class);
			assertThat(((ConcurrentLinkedHashMap<?, ?>) cacheMap).weightedSize()).isEqualTo(expectedMediaCacheSize);
		});
	}

	private void givenFolderConfig(final MediaStorageConfigService.MediaFolderConfig folderConfig, final int blockSizeInB)
	{
		given(folderConfig.getFolderQualifier()).willReturn(FOLDER_QUALIFIER);
		given(folderConfig.getHashingDepth()).willReturn(0);

		final String localCacheBlockSizeKey = DefaultSettingKeys.LOCAL_CACHE_BLOCK_SIZE.getKey();
		given(folderConfig.getParameter(localCacheBlockSizeKey, Integer.class)).willReturn(blockSizeInB);
		given(folderConfig.getParameter(eq(localCacheBlockSizeKey), eq(Integer.class), anyInt())).willReturn(blockSizeInB);

		given(folderConfig.getParameter(eq(DefaultSettingKeys.LOCAL_CACHE_ROOT_FOLDER_KEY.getKey()), eq(String.class),
				anyString())).willReturn(cacheFolderName);
	}

	private MediaCacheKey createMediaCacheKey(final String location)
	{
		return new MediaCacheKey(Registry.getCurrentTenantNoFallback().getTenantID(), cacheFolderName, location);
	}

	private DefaultLocalMediaFileCacheService createLocalMediaFileCacheService(final MediaCacheRegion mediaCacheRegion)
	{
		final DefaultCacheController spyCacheController = createCacheController(mediaCacheRegion);
		final DefaultLocalMediaFileCacheService localMediaFileCacheService = new DefaultLocalMediaFileCacheService();

		localMediaFileCacheService.setCacheController(spyCacheController);
		localMediaFileCacheService.setStorageConfigService(mediaStorageConfigService);
		localMediaFileCacheService.setCacheRecreator(new MediaCacheRecreator(cacheRoot.toFile(), spyCacheController));
		localMediaFileCacheService.setMediaCacheRegion(mediaCacheRegion);
		localMediaFileCacheService.setStorageRegistry(mediaStorageRegistry);
		localMediaFileCacheService.setStorageStrategy(localFileMediaStorageStrategy);
		localMediaFileCacheService.setMainDataDir(mediaDir.toFile());

		localMediaFileCacheService.init();
		return localMediaFileCacheService;
	}

	private DefaultCacheController createCacheController(final MediaCacheRegion mediaCacheRegion)
	{
		final DefaultCacheController spyCacheController = spy(cacheController);

		doReturn(mediaCacheRegion).when(spyCacheController).resolveCacheRegionForAdd(Mockito.any(MediaCacheKey.class));
		return spyCacheController;
	}


	private LocalMediaFileCacheService.StreamGetter mockStreamGetter(final String mediaLocationInStorage, final int fileSize)
			throws IOException
	{
		final LocalMediaFileCacheService.StreamGetter streamGetter = mock(LocalMediaFileCacheService.StreamGetter.class);

		final Path randomFile = createRandomFile(fileSize);

		given(streamGetter.getStream(folderConfig, mediaLocationInStorage)).willAnswer(
				invocationOnMock -> Files.newInputStream(randomFile));
		given(streamGetter.getSize(folderConfig, mediaLocationInStorage)).willReturn((long) fileSize);
		return streamGetter;
	}

	private Path createRandomFile(final int size) throws IOException
	{
		final Path tempFile = Files.createTempFile(null, null);
		filesToDelete.add(tempFile);

		try (final OutputStream bufferedWriter = new BufferedOutputStream(Files.newOutputStream(tempFile)))
		{
			for (int j = 0; j < size; j += 1024)
			{
				bufferedWriter.write(RandomUtils.nextBytes(Math.min(1024, size - j)));
			}
			bufferedWriter.flush();
		}
		assertThat(Files.size(tempFile)).isEqualTo(size);
		return tempFile;
	}


	private static MediaCacheRegion createMediaCacheRegion(final String cacheFolderName, final int sizeInMb, final int blockSize)
	{
		final MediaCacheRegion mediaCacheRegion = new MediaCacheRegion("test", sizeInMb, cacheFolderName, true, blockSize);
		mediaCacheRegion.setHandledTypes(new String[]{ MediaCacheRegion.MEDIA_TYPE });
		return mediaCacheRegion;
	}

}
