/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commons.renderer.model;

import de.hybris.platform.commons.model.renderer.RendererTemplateModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


public class DefaultTemplateScriptAttributeHandler implements DynamicAttributeHandler<String, RendererTemplateModel>
{
	private MediaService mediaService;
	private ModelService modelService;

	@Override
	public String get(final RendererTemplateModel templateModel)
	{
		final Optional<String> contextDefaultTemplateScript = DefaultTemplateScriptHelper.getDefaultTemplateScriptFromInternalContext(
			templateModel);
		return contextDefaultTemplateScript.orElseGet(() -> getDefaultContentAsString(templateModel));
	}

	@Override
	public void set(final RendererTemplateModel templateModel, final String templateValue)
	{
		DefaultTemplateScriptHelper.setDefaultTemplateScriptToInternalContext(templateModel,
			templateValue == null ? StringUtils.EMPTY : templateValue);
	}

	private String getDefaultContentAsString(final RendererTemplateModel templateModel)
	{
		final MediaModel defaultContent = templateModel.getDefaultContent();

		if (defaultContent == null || !mediaService.hasData(defaultContent))
		{
			return null;
	}

		try
		{
			return IOUtils.toString(mediaService.getStreamFromMedia(defaultContent), StandardCharsets.UTF_8);
		}
		catch (final IOException ioException)
		{
			throw new SystemException(ioException);
		}
	}

	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
