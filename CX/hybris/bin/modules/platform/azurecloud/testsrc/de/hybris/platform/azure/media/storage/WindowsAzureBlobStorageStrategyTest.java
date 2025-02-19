/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.azure.media.storage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.media.exceptions.MediaNotFoundException;
import de.hybris.platform.media.exceptions.MediaRemovalException;
import de.hybris.platform.media.exceptions.MediaStoreException;
import de.hybris.platform.media.services.MediaLocationHashService;
import de.hybris.platform.media.services.impl.HierarchicalMediaPathBuilder;
import de.hybris.platform.media.storage.MediaMetaData;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.MediaStorageConfigService.MediaFolderConfig;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class WindowsAzureBlobStorageStrategyTest
{
	private static final String MEDIA_ID = "123456";
	private static final Long MEDIA_SIZE = 123456L;
	private static final String TEST_LOCATION = "testLocation";

	@Mock
	private MediaLocationHashService locationHashService;
	@Mock
	private MediaFolderConfig folderConfig;
	@Mock
	private MediaStorageConfigService configService;
	@Mock
	private InputStream dataStream;
	private WindowsAzureBlobStorageStrategy strategy;

	@Before
	public void setUp() throws Exception
	{
		strategy = new WindowsAzureBlobStorageStrategy();
		strategy.setLocationHashService(locationHashService);
		strategy.setStorageConfigService(configService);
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenFolderConfigIsNullOnStoringMedia()
	{
		// given
		final MediaFolderConfig folderConfig = null;

		try
		{
			// when
			strategy.store(folderConfig, MEDIA_ID, Collections.EMPTY_MAP, dataStream);
			fail("should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// then
			assertThat(e).hasMessage("folder config is required!");
		}
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenMediaIdIsNullOnStoringMedia()
	{
		// given
		final String mediaId = null;

		try
		{
			// when
			strategy.store(folderConfig, mediaId, Collections.EMPTY_MAP, dataStream);
			fail("should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// then
			assertThat(e).hasMessage("mediaId is required!");
		}
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenMetaDataIsNullOnStoringMedia()
	{
		// given
		final Map<String, Object> metaData = null;

		try
		{
			// when
			strategy.store(folderConfig, MEDIA_ID, metaData, dataStream);
			fail("should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// then
			assertThat(e).hasMessage("metaData is required!");
		}
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenDataStreamIsNullOnStoringMedia()
	{
		// given
		final InputStream dataStream = null;

		try
		{
			// when
			strategy.store(folderConfig, MEDIA_ID, Collections.EMPTY_MAP, dataStream);
			fail("should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// then
			assertThat(e).hasMessage("dataStream is required!");
		}
	}

	@Test
	public void shouldThrowMediaNotFoundExceptionWhenAskingForSizeForNonExistentObject() throws Exception
	{
		// given
		final String mediaLocation = "NON_EXISISTENT";

		try
		{
			// when
			strategy.getSize(folderConfig, mediaLocation);
			fail("Should throw MediaNotFoundException");

		}
		catch (final MediaNotFoundException e)
		{
			// then fine
		}
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowUnsupportedOperationExceptionOnGettingMediaAsFile()
	{
		strategy.getAsFile(folderConfig, MEDIA_ID);
	}

	@Test
	public void shouldNotStripIsoControlCharactersFromValidFileName()
	{
		// given
		final String mediaId = "testMedia";
		final String validFileName = "zażółćFußgängerübergängeนาฬิกาติดผนัง\\a\\b";
		final String expectedFileName = "zażółćFußgängerübergängeนาฬิกาติดผนัง/a/b";
		final HierarchicalMediaPathBuilder pathBuilder = HierarchicalMediaPathBuilder.forDepth(2);
		final String location = (pathBuilder.buildPath(null, mediaId) + mediaId);

		// when
		final String result = strategy.assembleLocation(mediaId, validFileName);

		// then
		assertThat(result.chars().anyMatch(Character::isISOControl)).isFalse();
		assertThat(result).isEqualTo(location + "/" + expectedFileName);
	}

	@Test
	public void shouldStripIsoControlCharactersFromInValidFileName()
	{
		// given
		final String mediaId = "testMedia";
		final HierarchicalMediaPathBuilder pathBuilder = HierarchicalMediaPathBuilder.forDepth(2);
		final String location = (pathBuilder.buildPath(null, mediaId) + mediaId);
		final String validFileName = "zażółćFußgängerübergängeนาฬิกาติดผนัง";
		final String invalidFileName = validFileName + "\u009e\u0099\u009c\u0089\u0084";

		// when
		final String result = strategy.assembleLocation("testMedia", invalidFileName);

		// then
		assertThat(result.chars().anyMatch(Character::isISOControl)).isFalse();
		assertThat(result).isEqualTo(location + "/" + validFileName);
	}

	@Test
	public void shouldAssembleLocationWhenFileNameIsBlank()
	{
		// given
		final String mediaId = MEDIA_ID;
		final HierarchicalMediaPathBuilder pathBuilder = HierarchicalMediaPathBuilder.forDepth(2);
		final String expectedLocation = (pathBuilder.buildPath(null, mediaId) + mediaId);

		// when
		final String nullFileNameResult = strategy.assembleLocation(mediaId, null);
		final String blankFileNameResult = strategy.assembleLocation(mediaId, " ");

		// then
		List.of(nullFileNameResult, blankFileNameResult).forEach(result -> assertThat(result).isEqualTo(expectedLocation));
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenSizeIsNotLongForOnStoringMedia()
	{
		// given
		final Map<String, Object> metaData = Map.of(MediaMetaData.SIZE, "123");

		// when
		final Throwable throwable = catchThrowable(() -> strategy.store(folderConfig, MEDIA_ID, metaData, dataStream));

		// then
		assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
		                     .hasMessage(
				                     "Object size as Long is required to store blobs in Azure Blob Storage. Only local caching allows to get size.");
	}

	@Test
	public void shouldThrowRuntimeExceptionWhenConnectionStringIsBlank()
	{
		// given
		when(folderConfig.getParameter(WindowsAzureBlobStorageStrategy.CONNECTION_STRING_KEY)).thenReturn(" ");
		final Map<String, Object> metaData = Map.of(MediaMetaData.SIZE, MEDIA_SIZE);

		// when
		final var mediaStoreEx = catchThrowable(() -> strategy.store(folderConfig, MEDIA_ID, metaData, dataStream));
		final var mediaRemovalEx = catchThrowable(() -> strategy.delete(folderConfig, TEST_LOCATION));
		final var mediaNotFoundEx1 = catchThrowable(() -> strategy.getAsStream(folderConfig, TEST_LOCATION));
		final var mediaNotFoundEx2 = catchThrowable(() -> strategy.getSize(folderConfig, TEST_LOCATION));

		// then
		assertThat(mediaStoreEx).isInstanceOf(MediaStoreException.class);
		assertThat(mediaRemovalEx).isInstanceOf(MediaRemovalException.class)
		                          .hasMessage("Removal of media: " + TEST_LOCATION + " has failed.");
		List.of(mediaNotFoundEx1, mediaNotFoundEx2)
		    .forEach(err -> assertThat(err).isInstanceOf(MediaNotFoundException.class)
		                                   .hasMessage("Media not found (requested media location: " + TEST_LOCATION + ")"));
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenFolderConfigIsNullOnHasValidMediaFolderName()
	{
		// when
		final var illegalArgErr = catchThrowable(() -> strategy.hasValidMediaFolderName(null));

		//then
		assertThat(illegalArgErr).isInstanceOf(IllegalArgumentException.class)
		                         .hasMessage("folder config is required!");
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenLocationIsNull()
	{
		// when
		final var illegalArgErr1 = catchThrowable(() -> strategy.delete(folderConfig, null));
		final var illegalArgErr2 = catchThrowable(() -> strategy.getAsStream(folderConfig, null));
		final var illegalArgErr3 = catchThrowable(() -> strategy.getSize(folderConfig, null));

		// then
		List.of(illegalArgErr1, illegalArgErr2, illegalArgErr3)
		    .forEach(err -> assertThat(err).isInstanceOf(IllegalArgumentException.class)
		                                   .hasMessage("location is required!"));
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenMediaFolderConfigIsNull()
	{
		// when
		final var illegalArgErr1 = catchThrowable(() -> strategy.delete(null, TEST_LOCATION));
		final var illegalArgErr2 = catchThrowable(() -> strategy.getAsStream(null, TEST_LOCATION));
		final var illegalArgErr3 = catchThrowable(() -> strategy.getSize(null, TEST_LOCATION));

		// then
		List.of(illegalArgErr1, illegalArgErr2, illegalArgErr3)
		    .forEach(err -> assertThat(err).isInstanceOf(IllegalArgumentException.class)
		                                   .hasMessageMatching("(config|folderQualifier) is required!"));
	}
}
