/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.media.interceptors;

import static de.hybris.platform.media.interceptors.PreventDirectFolderChangeForNonEmptyMediaValidateInterceptor.ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME;
import static de.hybris.platform.media.interceptors.PreventDirectFolderChangeForNonEmptyMediaValidateInterceptor.MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.servicelayer.session.SessionService;

import org.apache.commons.configuration.Configuration;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class PreventDirectFolderChangeForNonEmptyMediaValidateInterceptorTest
{

	@InjectMocks
	private PreventDirectFolderChangeForNonEmptyMediaValidateInterceptor testee;

	@Mock
	private MediaService mediaService;

	@Mock
	private InterceptorContext interceptorContext;

	@Mock
	private ConfigurationService configurationService;

	@Mock
	private Configuration configuration;

	@Mock
	private SessionService sessionService;

	@Before
	public void setUp()
	{
		when(configurationService.getConfiguration())
				.thenReturn(configuration);
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsDisabled()
	{
		// given
		final MediaModel mediaModel = new MediaModel();
		when(configuration.getBoolean(anyString()))
				.thenReturn(false);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsNotSet()
	{
		// given
		final MediaModel mediaModel = new MediaModel();
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(null);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsSetToTrue()
	{
		// given
		final MediaModel mediaModel = new MediaModel();
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(Boolean.TRUE);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsSetToFalseAndMediaModelIsNew()
	{
		// given
		final MediaModel mediaModel = new MediaModel();
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(Boolean.FALSE);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsSetToFalseAndMediaModelIsNotNewAndMediaFolderIsNotModified()
	{
		// given
		final MediaModel mediaModel = new MediaModel();
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(Boolean.FALSE);
		when(interceptorContext.isModified(any(), anyString()))
				.thenReturn(false);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
		verify(interceptorContext).isModified(mediaModel, MediaModel.FOLDER);
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsSetToFalseAndMediaModelIsNotNewAndMediaFolderIsModifiedButIsTheSame()
	{
		// given
		final MediaFolderModel mediaFolderModel = new MediaFolderModel();
		final MediaModel mediaModel = mock(MediaModel.class);
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(Boolean.FALSE);
		when(interceptorContext.isModified(any(), anyString()))
				.thenReturn(true);
		final ItemModelContext itemModelContext = mock(ItemModelContext.class);
		when(mediaModel.getItemModelContext())
				.thenReturn(itemModelContext);
		when(itemModelContext.getOriginalValue(anyString()))
				.thenReturn(mediaFolderModel);
		when(mediaModel.getFolder())
				.thenReturn(mediaFolderModel);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
		verify(interceptorContext).isModified(mediaModel, MediaModel.FOLDER);
		verify(mediaModel).getItemModelContext();
		verify(itemModelContext).getOriginalValue(MediaModel.FOLDER);
		verify(mediaModel).getFolder();
	}

	@Test
	public void shouldDoNothingWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsSetToFalseAndMediaModelIsNotNewAndMediaFolderIsModifiedAndIsTheSameAndMediaDoesNotHaveData()
	{
		// given
		final MediaFolderModel mediaFolderModelOld = new MediaFolderModel();
		final MediaFolderModel mediaFolderModelNew = new MediaFolderModel();
		final MediaModel mediaModel = mock(MediaModel.class);
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(Boolean.FALSE);
		when(interceptorContext.isModified(any(), anyString()))
				.thenReturn(true);
		final ItemModelContext itemModelContext = mock(ItemModelContext.class);
		when(mediaModel.getItemModelContext())
				.thenReturn(itemModelContext);
		when(itemModelContext.getOriginalValue(anyString()))
				.thenReturn(mediaFolderModelOld);
		when(mediaModel.getFolder())
				.thenReturn(mediaFolderModelNew);
		when(mediaService.hasData(any()))
				.thenReturn(false);

		// when
		//final Executable executable = () -> testee.onValidate(mediaModel, interceptorContext);
		// then
		//assertDoesNotThrow(executable);
		assertNoException(() -> testee.onValidate(mediaModel, interceptorContext));

		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
		verify(interceptorContext).isModified(mediaModel, MediaModel.FOLDER);
		verify(mediaModel).getItemModelContext();
		verify(itemModelContext).getOriginalValue(MediaModel.FOLDER);
		verify(mediaModel).getFolder();
		verify(mediaService).hasData(mediaModel);
	}

	@Test
	public void shouldThrowInterceptorExceptionWhenDirectFolderChangeGuardIsEnabledAndAllowDirectMediaFolderChangeSessionAttrIsSetToFalseAndMediaModelIsNotNewAndMediaFolderIsModifiedAndIsTheSameAndMediaHasData()
	{
		// given
		final MediaFolderModel mediaFolderModelOld = new MediaFolderModel();
		final MediaFolderModel mediaFolderModelNew = new MediaFolderModel();
		final MediaModel mediaModel = mock(MediaModel.class);
		when(configuration.getBoolean(anyString()))
				.thenReturn(true);
		when(sessionService.getAttribute(anyString()))
				.thenReturn(Boolean.FALSE);
		when(interceptorContext.isModified(any(), anyString()))
				.thenReturn(true);
		final ItemModelContext itemModelContext = mock(ItemModelContext.class);
		when(mediaModel.getItemModelContext())
				.thenReturn(itemModelContext);
		when(itemModelContext.getOriginalValue(anyString()))
				.thenReturn(mediaFolderModelOld);
		when(mediaModel.getFolder())
				.thenReturn(mediaFolderModelNew);
		when(mediaService.hasData(any()))
				.thenReturn(true);

		// when
		final ThrowableAssert.ThrowingCallable throwingCallable = () -> testee.onValidate(mediaModel, interceptorContext);

		// then
		assertThatThrownBy(throwingCallable)
				.isInstanceOf(InterceptorException.class)
				.hasMessageEndingWith("Cannot change directly folder for media with associated data!");
		verify(configuration).getBoolean(MEDIA_FOLDER_DIRECT_FOLDER_CHANGE_GUARD_ENABLED_CONFIG_PROP_KEY);
		verify(sessionService).getAttribute(ALLOW_DIRECT_MEDIA_FOLDER_CHANGE_SESSION_ATTR_NAME);
		verify(interceptorContext).isModified(mediaModel, MediaModel.FOLDER);
		verify(mediaModel).getItemModelContext();
		verify(itemModelContext).getOriginalValue(MediaModel.FOLDER);
		verify(mediaModel).getFolder();
		verify(mediaService).hasData(mediaModel);
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
