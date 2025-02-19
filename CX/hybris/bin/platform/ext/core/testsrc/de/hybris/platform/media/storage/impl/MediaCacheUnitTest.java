package de.hybris.platform.media.storage.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.impl.DefaultLocalMediaFileCacheService.MediaCacheUnit;

import java.io.File;

import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;

@UnitTest
public class MediaCacheUnitTest
{

	private MockitoSession mockitoSession;

	@Before
	public void setUp() throws Exception
	{
		mockitoSession = Mockito.mockitoSession().initMocks(this).strictness(Strictness.LENIENT).startMocking();
	}

	@After
	public void tearDown() throws Exception
	{
		mockitoSession.finishMocking();
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileIsZero()
	{
		testMediaCacheUnitWeightCalculation(0, 1);
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileIsSmallerThanBlockSize()
	{
		final long blockSize = MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
		testMediaCacheUnitWeightCalculation(blockSize - 1, 1);
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileHasBlockSize()
	{
		final long blockSize = MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
		testMediaCacheUnitWeightCalculation(blockSize, 1);
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileIsLargerThanBlockSize()
	{
		final long blockSize = MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
		testMediaCacheUnitWeightCalculation(blockSize + 1, 2);
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileEqualsManyTimesBlockSize()
	{
		final long blockSize = MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize, numberOfBlocks);
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileIsSmallerThanManyTimesBlockSize()
	{
		final long blockSize = MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize - 1, numberOfBlocks);
	}

	@Test
	public void shouldCalculateWeightWithDefaultBlockSizeWhenFileIsLargerThanManyTimesBlockSize()
	{
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		final long blockSize = MediaStorageConfigService.DEFAULT_BLOCK_SIZE;
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize + 1, numberOfBlocks + 1);
	}

	private static void testMediaCacheUnitWeightCalculation(final long fileSize, final int expectedWeight)
	{
		final File file = mockFileWithSize(fileSize);

		final MediaCacheUnit mediaCacheUnit = new MediaCacheUnit(file);
		assertThat(mediaCacheUnit.getWeight()).as("Checking file with size %d and blockSize %d", fileSize,
				MediaStorageConfigService.DEFAULT_BLOCK_SIZE).isEqualTo(expectedWeight);
	}

	@Test
	public void shouldCalculateWeightWith4KBlockSizeWhenFileIsLargerThanBlockSize()
	{
		final int blockSize = 4 * 1024;
		testMediaCacheUnitWeightCalculation(blockSize + 1, blockSize, 2);
	}

	@Test
	public void shouldCalculateWeightWith4KBlockSizeWhenFileIsSmallerThanBlockSize()
	{
		final int blockSize = 4 * 1024;
		testMediaCacheUnitWeightCalculation(blockSize - 1, blockSize, 1);
	}

	@Test
	public void shouldCalculateWeightWith4KBlockSizeWhenFileEqualsBlockSize()
	{
		final int blockSize = 4 * 1024;
		testMediaCacheUnitWeightCalculation(blockSize, blockSize, 1);
	}

	@Test
	public void shouldCalculateWeightWith4KBlockSizeWhenFileEqualsManyTimesBlockSize()
	{
		final int blockSize = 4 * 1024;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize, blockSize, numberOfBlocks);
	}

	@Test
	public void shouldCalculateWeightWith4KBlockSizeWhenFileIsSmallerThanManyTimesBlockSize()
	{
		final int blockSize = 4 * 1024;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize - 1, blockSize, numberOfBlocks);
	}

	@Test
	public void shouldCalculateWeightWith4KBlockSizeWhenFileIsLargerThanManyTimesBlockSize()
	{
		final int blockSize = 4 * 1024;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize + 1, blockSize, numberOfBlocks + 1);
	}

	@Test
	public void shouldCalculateWeightWith8KBlockSizeWhenFileIsLargerThanBlockSize()
	{
		final int blockSize = 8 * 1024;
		testMediaCacheUnitWeightCalculation(blockSize + 1, blockSize, 2);
	}

	@Test
	public void shouldCalculateWeightWith8KBlockSizeWhenFileIsSmallerThanBlockSize()
	{
		final int blockSize = 8 * 1024;
		testMediaCacheUnitWeightCalculation(blockSize - 1, blockSize, 1);
	}

	@Test
	public void shouldCalculateWeightWith8KBlockSizeWhenFileEqualsBlockSize()
	{
		final int blockSize = 8 * 1024;
		testMediaCacheUnitWeightCalculation(blockSize, blockSize, 1);
	}

	@Test
	public void shouldCalculateWeightWith8KBlockSizeWhenFileEqualsManyTimesBlockSize()
	{
		final int blockSize = 8 * 1024;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize, blockSize, numberOfBlocks);
	}

	@Test
	public void shouldCalculateWeightWith8KBlockSizeWhenFileIsSmallerThanManyTimesBlockSize()
	{
		final int blockSize = 8 * 1024;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize - 1, blockSize, numberOfBlocks);
	}

	@Test
	public void shouldCalculateWeightWith8KBlockSizeWhenFileIsLargerThanManyTimesBlockSize()
	{
		final int blockSize = 8 * 1024;
		final int numberOfBlocks = RandomUtils.nextInt(4, 20);
		testMediaCacheUnitWeightCalculation(numberOfBlocks * blockSize + 1, blockSize, numberOfBlocks + 1);
	}

	private static void testMediaCacheUnitWeightCalculation(final int fileSize, final int blockSize, final int expectedWeight)
	{
		final File file = mockFileWithSize(fileSize);

		final MediaCacheUnit mediaCacheUnit = new MediaCacheUnit(file, blockSize);
		assertThat((mediaCacheUnit.getWeight())).as("Checking file with size %d and blockSize %d", fileSize, blockSize)
		                                        .isEqualTo(expectedWeight);
	}

	@Test
	public void shouldAssignDefaultBlockSizeWhenCreatingMediaCacheUnit()
	{
		final File file = mockFileWithSize(1);
		final MediaCacheUnit mediaCacheUnit = new MediaCacheUnit(file);
		assertThat(mediaCacheUnit).extracting("blockSize").contains(MediaStorageConfigService.DEFAULT_BLOCK_SIZE);
	}


	@Test
	public void shouldFailCreatingMediaCacheUnitWithBlockSizeSetToZero()
	{
		final File file = mockFileWithSize(1);
		Assertions.assertThatThrownBy(() -> new MediaCacheUnit(file, 0))
		          .isInstanceOf(IllegalArgumentException.class)
		          .hasMessage("Block size must be greater than zero");
	}

	private static File mockFileWithSize(final long t)
	{
		final File mock = mock(File.class);
		when(mock.length()).thenReturn(t);
		when(mock.exists()).thenReturn(true);
		return mock;
	}
}
