/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.azure.media.storage;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.ManualTest;
import de.hybris.platform.media.exceptions.ExternalStorageServiceException;
import de.hybris.platform.media.exceptions.MediaNotFoundException;
import de.hybris.platform.media.exceptions.MediaRemovalException;
import de.hybris.platform.media.storage.MediaMetaData;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.impl.StoredMediaData;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * This test requires manual settings for existing Azure Blob Storage.
 */
@ManualTest
public class WindowsAzureBlobStorageStrategyManualTest extends ServicelayerBaseTest
{
	private static final String INVALID_CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=invalidAccount;AccountKey=invalidKey;EndpointSuffix=core.windows.net";

	@Resource(name = "windowsAzureBlobStorageStrategy")
	private WindowsAzureBlobStorageStrategy strategy;
	@Resource
	private MediaStorageConfigService mediaStorageConfigService;

	@Test
	public void shouldReturnProperMediaSize()
	{
		// given
		final MediaStorageConfigService.MediaFolderConfig rootFolderConfig = mediaStorageConfigService.getConfigForFolder("root");
		final String fileName = RandomStringUtils.randomAlphabetic(10);
		final String mime = "image/jpeg";
		final String mediaId = "test-stuff";
		final byte[] bytes = RandomStringUtils.random(10).getBytes();
		final ByteArrayInputStream mediaStream = new ByteArrayInputStream(bytes);
		final Map<String, Object> metaData = buildMediaMetaData(mime, fileName, StringUtils.EMPTY, (long) bytes.length);

		// when
		final StoredMediaData result = strategy.store(rootFolderConfig, mediaId, metaData, mediaStream);
		assertThat(result).isNotNull();
		final long obtainedSize = strategy.getSize(rootFolderConfig, result.getLocation());
		strategy.delete(rootFolderConfig, result.getLocation());
		final Throwable getSizeEx = catchThrowable(() -> strategy.getSize(rootFolderConfig, result.getLocation()));
		strategy.delete(rootFolderConfig, result.getLocation());

		// then
		assertThat(obtainedSize).isEqualTo(bytes.length);
		assertThat(getSizeEx).isInstanceOf(MediaNotFoundException.class);
	}

	@Test(expected = ExternalStorageServiceException.class)
	public void shouldThrowExternalStorageServiceExceptionOnInvalidStorageConnection()
	{
		try
		{
			final MediaStorageConfigService.GlobalMediaStorageConfig settingsMock = mock(
					MediaStorageConfigService.GlobalMediaStorageConfig.class);
			when(settingsMock.getParameter(WindowsAzureBlobStorageStrategy.CONNECTION_STRING_KEY))
					.thenReturn(INVALID_CONNECTION_STRING);

			final MediaStorageConfigService mediaStorageConfigServiceSpy = Mockito.spy(mediaStorageConfigService);
			when(mediaStorageConfigServiceSpy.getGlobalSettingsForStrategy(Mockito.anyString())).thenReturn(settingsMock);
			strategy.setStorageConfigService(mediaStorageConfigServiceSpy);

			strategy.checkStorageConnection();
		}
		finally
		{
			strategy.setStorageConfigService(mediaStorageConfigService);
		}
	}

	private Map<String, Object> buildMediaMetaData(final String mime, final String originalName, final String folderPath,
	                                               final Long size)
	{
		final Map<String, Object> metaData = new HashMap<>();
		metaData.put(MediaMetaData.MIME, mime);
		metaData.put(MediaMetaData.FILE_NAME, originalName);
		metaData.put(MediaMetaData.FOLDER_PATH, folderPath);
		metaData.put(MediaMetaData.SIZE, size);
		return metaData;
	}
}
