/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.media.interceptors;


import static de.hybris.platform.media.interceptors.PreventDirectFolderChangeForNonEmptyMediaValidateInterceptor.MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.testframework.PropertyConfigSwitcher;

import java.util.Locale;

import javax.annotation.Resource;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;

@IntegrationTest
public class PreventDirectFolderChangeForNonEmptyMediaValidateInterceptorIntegrationTest extends ServicelayerBaseTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private MediaService mediaService;

	private final PropertyConfigSwitcher directFolderChangeGuardEnabledPropertyConfigSwitcher = new PropertyConfigSwitcher(
			MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);

	@Test
	public void shouldSaveMediaModelEvenWhenOnlyFolderHasChangedAndDirectChangeGuardIsDisabled()
	{
		// given
		directFolderChangeGuardEnabledPropertyConfigSwitcher.switchToValue("false");
		final MediaModel mediaModel = createMedia();
		modelService.save(mediaModel);
		mediaService.setDataForMedia(mediaModel, "test".getBytes());

		mediaModel.setFolder(createMediaFolder("test"));

		// when
		//final Executable executable = () -> modelService.save(mediaModel);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> modelService.save(mediaModel));

		directFolderChangeGuardEnabledPropertyConfigSwitcher.switchBackToDefault();
	}

	@Test
	public void shouldSaveMediaModelEvenWhenOnlyFolderHasChangedWhenDirectChangeGuardIsEnabledAndDirectFolderChangeIsDoneViaMediaServiceMoveMediaToFolderMethod()
	{
		// given
		final MediaModel mediaModel = createMedia();
		modelService.save(mediaModel);
		mediaService.setDataForMedia(mediaModel, "test".getBytes());
		final MediaFolderModel mediaFolder = createMediaFolder("test");

		// when
		//final Executable executable = () -> mediaService.moveMediaToFolder(mediaModel, mediaFolder);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> mediaService.moveMediaToFolder(mediaModel, mediaFolder));
	}

	@Test
	public void shouldSaveMediaModelWhenMediaModelIsNewAndDirectChangeGuardIsEnabledAndDirectFolderChangeIsAllowedForSession()
	{
		// given
		final MediaModel mediaModel = createMedia();

		// when
		//final Executable executable = () -> modelService.save(mediaModel);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> modelService.save(mediaModel));
	}

	@Test
	public void shouldSaveMediaModelAfterSettingDataViaMediaServiceAndDirectChangeGuardIsEnabledAndDirectFolderChangeIsAllowedForSession()
	{
		// given
		final MediaModel mediaModel = createMedia();
		modelService.save(mediaModel);

		// when
		//final Executable executable = () -> mediaService.setDataForMedia(mediaModel, "test".getBytes());
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> mediaService.setDataForMedia(mediaModel, "test".getBytes()));
	}

	@Test
	public void shouldAllowChangingMediaFolderDirectlyWhenMediaModelDoesNotHaveAssociatedDataAndDirectChangeGuardIsEnabledAndDirectFolderChangeIsAllowedForSession()
	{
		// given
		final MediaModel mediaModel = createMedia();
		modelService.save(mediaModel);
		mediaModel.setFolder(createMediaFolder("test"));

		// when
		//final Executable executable = () -> modelService.save(mediaModel);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> modelService.save(mediaModel));
	}

	@Test
	public void shouldThrowModelSavingExceptionWhenTryingToChangeMediaFolderDirectlyWhenMediaModelHasAssociatedDataAndDirectChangeGuardIsEnabledAndDirectFolderChangeIsAllowedForSession()
	{
		// given
		final MediaModel mediaModel = createMedia();
		modelService.save(mediaModel);
		mediaService.setDataForMedia(mediaModel, "test".getBytes());
		final MediaFolderModel folder = mediaModel.getFolder();

		final MediaFolderModel newFolder = modelService.create(MediaFolderModel.class);
		newFolder.setQualifier("test2");
		newFolder.setPath("other");
		modelService.save(newFolder);
		mediaService.moveMediaToFolder(mediaModel, newFolder);

		final String dataString = new String(mediaService.getDataFromMedia(mediaModel));

		mediaModel.setFolder(createMediaFolder("test"));

		// when
		final ThrowableAssert.ThrowingCallable throwingCallable = () -> modelService.save(mediaModel);

		// then
		assertThatThrownBy(throwingCallable)
				.isInstanceOf(ModelSavingException.class)
				.hasMessageContaining("Cannot change directly folder for media with associated data!");
		assertThat(folder.getQualifier()).isEqualTo("root");
		assertThat(dataString).isEqualTo("test");
	}

	@Test
	public void shouldAllowChangeMediaFolderDirectlyAfterRemovingDataFromMedia() {
		// given
		final MediaModel mediaModel = createMedia();
		modelService.save(mediaModel);
		mediaService.setDataForMedia(mediaModel, "test".getBytes());

		mediaModel.setFolder(createMediaFolder("test"));
		assertThatThrownBy(() -> modelService.save(mediaModel))
				.isInstanceOf(ModelSavingException.class);

		// when
		mediaService.removeDataFromMedia(mediaModel);

		// then
		mediaModel.setFolder(createMediaFolder("test2"));
		//assertDoesNotThrow(() -> modelService.save(mediaModel));
		assertNoException(() -> modelService.save(mediaModel));
	}

	private MediaModel createMedia()
	{
		final MediaModel mediaModel = new MediaModel();
		mediaModel.setCatalogVersion(createCatalogVersion());
		mediaModel.setCode("test");
		return mediaModel;
	}

	private CatalogVersionModel createCatalogVersion()
	{
		final CatalogVersionModel catalogVersionModel = new CatalogVersionModel();
		catalogVersionModel.setVersion("1");
		catalogVersionModel.setCatalog(createCatalog());
		return catalogVersionModel;
	}

	private CatalogModel createCatalog()
	{
		final CatalogModel catalogModel = new CatalogModel();
		catalogModel.setId("test");
		catalogModel.setName("test", Locale.ENGLISH);
		return catalogModel;
	}

	private MediaFolderModel createMediaFolder(final String qualifier)
	{
		final MediaFolderModel mediaFolderModel = new MediaFolderModel();
		mediaFolderModel.setQualifier(qualifier);
		mediaFolderModel.setPath("/");
		return mediaFolderModel;
	}

	@FunctionalInterface
	private interface Runable
	{
		void run() throws Exception;
	}

	private void assertNoException(final Runable runable)
	{
		try
		{
			runable.run();
		} catch(Exception ex)
		{
			fail("Exception is not expected.");
		}
	}
}
