/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.commons.renderer.interceptors;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.commons.model.renderer.RendererTemplateModel;
import de.hybris.platform.commons.renderer.model.DefaultTemplateScriptHelper;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * {@link PrepareInterceptor} for the {@link RendererTemplateModel}.
 */
public class DefaultTemplateScriptAttributeInterceptor implements PrepareInterceptor<RendererTemplateModel>
{
	private static final String DEFAULT_TEMPLATE_SCRIPT_NAME = "{0}_defaultTemplateScript";
	private ModelService modelService;
	private MediaService mediaService;

	@Override
	public void onPrepare(final RendererTemplateModel rendererTemplateModel, final InterceptorContext ctx)
		throws InterceptorException
	{
		final Optional<String> contextDefaultTemplateScript = DefaultTemplateScriptHelper.getDefaultTemplateScriptFromInternalContext(
			rendererTemplateModel);
		contextDefaultTemplateScript.ifPresent(value -> updateDefaultContentAndUnloadFromContext(rendererTemplateModel, value));
	}

	private void updateDefaultContentAndUnloadFromContext(final RendererTemplateModel rendererTemplateModel,
														  final String templateValue)
	{
		updateDefaultContent(rendererTemplateModel, templateValue);
		DefaultTemplateScriptHelper.unloadContextDefaultTemplateScriptAttribute(rendererTemplateModel);
	}

	private void updateDefaultContent(final RendererTemplateModel rendererTemplateModel, final String templateValue)
	{
		MediaModel media = rendererTemplateModel.getDefaultContent();

		if (StringUtils.isNotEmpty(templateValue))
		{
			final InputStream is = IOUtils.toInputStream(templateValue, StandardCharsets.UTF_8);

			if (media == null)
			{
				media = createMedia(rendererTemplateModel.getCode());
				rendererTemplateModel.setDefaultContent(media);
			}

			mediaService.setStreamForMedia(media, is);
		}
		else if (media != null)
		{
			mediaService.removeDataFromMedia(media);
		}
	}

	private MediaModel createMedia(final String templateCode)
	{
		final String mediaCode = MessageFormat.format(DEFAULT_TEMPLATE_SCRIPT_NAME, templateCode);

		final CatalogUnawareMediaModel media = modelService.create(CatalogUnawareMediaModel.class);
		media.setCode(mediaCode);
		media.setRealFileName(mediaCode);
		media.setMime("plain/text");

		modelService.save(media);

		return media;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}
}
