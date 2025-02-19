/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.hac.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.hac.data.dto.ExportDataAndMediaResult;
import de.hybris.platform.hac.data.dto.ExportDataResult;
import de.hybris.platform.hac.data.dto.ImportDataResult;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.impex.ExportConfig;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.impex.ExportService;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.media.MediaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class HacImpexFacadeTest
{
	private static final String DATA_ZIP_NAME = "FooBar";
	private static final String DATA_ZIP_PATH = "foo/bar/baz.zip";
	private static final String MEDIA_ZIP_NAME = "Media";
	private static final String MEDIA_ZIP_PATH = "mediaexport_00000.zip";
	@InjectMocks
	private final HacImpexFacade facade = new HacImpexFacade();
	@Mock
	private ImportConfig importConfig;
	@Mock
	private ImportService importService;
	@Mock
	private ImportResult importResult;
	@Mock
	private MediaService mediaService;
	@Mock
	private ImpExMediaModel impexMedia;
	@Mock
	private ImpExMediaModel exportedData;
	@Mock
	private ImpExMediaModel exportedMedia;
	@Mock
	private ExportResult exportResult;
	@Mock
	private ExportConfig exportConfig;
	@Mock
	private ExportService exportService;

	private final byte[] bytes = new byte[]
			{ (byte) 100 >>> 24, (byte) 100 >>> 16, (byte) 100 >>> 8, };


	/**
	 * Test method for {@link HacImpexFacade#importData(de.hybris.platform.servicelayer.impex.ImportConfig)} .
	 */
	@Test
	public void shouldCreateImportDataResultWithSuccessWhenImportWasSuccessfullyFinished()
	{
		// given
		given(importService.importData(importConfig)).willReturn(importResult);
		given(Boolean.valueOf(importResult.isSuccessful())).willReturn(Boolean.TRUE);
		given(Boolean.valueOf(importResult.hasUnresolvedLines())).willReturn(Boolean.FALSE);

		// when
		final ImportDataResult importDataResult = facade.importData(importConfig);

		// then
		assertThat(importDataResult).isNotNull();
		assertThat(importDataResult.isSuccesss()).isTrue();
		assertThat(importDataResult.isUnresolvedLines()).isFalse();
		assertThat(importDataResult.getUnresolvedData()).isEmpty();
	}

	/**
	 * Test method for {@link HacImpexFacade#importData(de.hybris.platform.servicelayer.impex.ImportConfig)} .
	 */
	@Test
	public void shouldCreateImportDataResultWithUnSuccessWhenImportWasNotSuccessfullyFinished()
	{
		// given
		given(importService.importData(importConfig)).willReturn(importResult);
		given(Boolean.valueOf(importResult.isSuccessful())).willReturn(Boolean.FALSE);
		given(Boolean.valueOf(importResult.hasUnresolvedLines())).willReturn(Boolean.TRUE);
		given(importResult.getUnresolvedLines()).willReturn(impexMedia);
		given(mediaService.getDataFromMedia(impexMedia)).willReturn(bytes);

		// when
		final ImportDataResult importDataResult = facade.importData(importConfig);

		// then
		assertThat(importDataResult).isNotNull();
		assertThat(importDataResult.isSuccesss()).isFalse();
		assertThat(importDataResult.isUnresolvedLines()).isTrue();
		assertThat(importDataResult.getUnresolvedData()).isNotNull();
	}

	/**
	 * Test method for {@link HacImpexFacade#exportData(de.hybris.platform.servicelayer.impex.ExportConfig)} .
	 */
	@Test
	public void shouldCreateExportDataResultWithSuccessWhenExportWasSuccessfullyFinished()
	{
		// given
		given(exportService.exportData(exportConfig)).willReturn(exportResult);
		given(Boolean.valueOf(exportResult.isSuccessful())).willReturn(Boolean.TRUE);
		given(exportResult.getExportedData()).willReturn(impexMedia);
		given(impexMedia.getRealFileName()).willReturn(DATA_ZIP_NAME);
		given(impexMedia.getDownloadURL()).willReturn(DATA_ZIP_PATH);

		// when
		final ExportDataResult exportDataResult = facade.exportData(exportConfig);

		// then
		assertThat(exportDataResult).isNotNull();
		assertThat(exportDataResult.isSuccess()).isTrue();
		assertThat(exportDataResult.getExportDataName()).isEqualTo(DATA_ZIP_NAME);
		assertThat(exportDataResult.getDownloadUrl()).isEqualTo(DATA_ZIP_PATH);
	}

	/**
	 * Test method for {@link HacImpexFacade#exportDataAndMedia(de.hybris.platform.servicelayer.impex.ExportConfig)} .
	 */
	@Test
	public void shouldCreateExportDataAndMediaResultWithSuccessWhenExportWasSuccessfullyFinished()
	{
		// given
		given(exportService.exportData(exportConfig)).willReturn(exportResult);
		given(Boolean.valueOf(exportResult.isSuccessful())).willReturn(Boolean.TRUE);
		given(exportResult.getExportedData()).willReturn(exportedData);
		given(exportResult.getExportedMedia()).willReturn(exportedMedia);
		given(exportedData.getRealFileName()).willReturn(DATA_ZIP_NAME);
		given(exportedData.getDownloadURL()).willReturn(DATA_ZIP_PATH);
		given(exportedMedia.getRealFileName()).willReturn(MEDIA_ZIP_NAME);
		given(exportedMedia.getDownloadURL()).willReturn(MEDIA_ZIP_PATH);


		// when
		final ExportDataAndMediaResult exportDataAndMediaResult = facade.exportDataAndMedia(exportConfig);

		// then
		assertThat(exportDataAndMediaResult).isNotNull();
		assertThat(exportDataAndMediaResult.isSuccess()).isTrue();
		assertThat(exportDataAndMediaResult.getExportDataName()).isEqualTo(DATA_ZIP_NAME);
		assertThat(exportDataAndMediaResult.getDataDownloadUrl()).isEqualTo(DATA_ZIP_PATH);
		assertThat(exportDataAndMediaResult.getExportMediaName()).isEqualTo(MEDIA_ZIP_NAME);
		assertThat(exportDataAndMediaResult.getMediaDownloadUrl()).isEqualTo(MEDIA_ZIP_PATH);
	}

	/**
	 * Test method for {@link HacImpexFacade#exportData(de.hybris.platform.servicelayer.impex.ExportConfig)} .
	 */
	@Test
	public void shouldCreateExportDataResultWithUnSuccessWhenExportWasNotSuccessfullyFinished()
	{
		// given
		given(exportService.exportData(exportConfig)).willReturn(exportResult);
		given(Boolean.valueOf(exportResult.isSuccessful())).willReturn(Boolean.FALSE);
		given(exportResult.getExportedData()).willReturn(null);

		// when
		final ExportDataResult exportDataResult = facade.exportData(exportConfig);

		// then
		assertThat(exportDataResult).isNotNull();
		assertThat(exportDataResult.isSuccess()).isFalse();
		assertThat(exportDataResult.getExportDataName()).isEqualTo("");
		assertThat(exportDataResult.getDownloadUrl()).isEqualTo("");
	}

	/**
	 * Test method for {@link HacImpexFacade#exportData(de.hybris.platform.servicelayer.impex.ExportConfig)} .
	 */
	@Test
	public void shouldCreateExportDataAndMediaResultWithUnSuccessWhenExportWasNotSuccessfullyFinished()
	{
		// given
		given(exportService.exportData(exportConfig)).willReturn(exportResult);
		given(Boolean.valueOf(exportResult.isSuccessful())).willReturn(Boolean.FALSE);
		given(exportResult.getExportedData()).willReturn(null);
		given(exportResult.getExportedMedia()).willReturn(null);

		// when
		final ExportDataAndMediaResult exportDataAndMediaResult = facade.exportDataAndMedia(exportConfig);

		// then
		assertThat(exportDataAndMediaResult).isNotNull();
		assertThat(exportDataAndMediaResult.isSuccess()).isFalse();
		assertThat(exportDataAndMediaResult.getExportDataName()).isEmpty();
		assertThat(exportDataAndMediaResult.getExportMediaName()).isEmpty();
		assertThat(exportDataAndMediaResult.getDataDownloadUrl()).isEmpty();
		assertThat(exportDataAndMediaResult.getMediaDownloadUrl()).isEmpty();
	}
}
