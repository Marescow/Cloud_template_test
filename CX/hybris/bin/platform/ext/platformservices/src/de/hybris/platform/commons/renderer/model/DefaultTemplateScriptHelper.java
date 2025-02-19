/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.commons.renderer.model;

import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.servicelayer.model.AbstractItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;

import java.util.Optional;

public final class DefaultTemplateScriptHelper
{
	private static final String CONTEXT_DEFAULT_TEMPLATE_SCRIPT_NAME = "context_defaultTemplateScript";

	private DefaultTemplateScriptHelper()
	{
	}

	public static Optional<String> getDefaultTemplateScriptFromInternalContext(final AbstractItemModel model)
	{
		try
		{
			final String defaultTemplateScriptFromInternalContext = ((ItemModelContextImpl) model.getItemModelContext()).getPropertyValue(
				CONTEXT_DEFAULT_TEMPLATE_SCRIPT_NAME);
			if (defaultTemplateScriptFromInternalContext == null)
			{
				return Optional.empty();
			}
			return Optional.of(defaultTemplateScriptFromInternalContext);
		}
		catch (final JaloSystemException e)
		{
			return Optional.empty();
		}
	}

	public static void setDefaultTemplateScriptToInternalContext(final AbstractItemModel model, final String templateValue)
	{
		((ItemModelContextImpl) model.getItemModelContext()).setPropertyValue(
			CONTEXT_DEFAULT_TEMPLATE_SCRIPT_NAME, templateValue);
	}

	public static void unloadContextDefaultTemplateScriptAttribute(final AbstractItemModel model)
	{
		((ItemModelContextImpl) model.getItemModelContext()).unloadAttribute(
			CONTEXT_DEFAULT_TEMPLATE_SCRIPT_NAME);
	}
}
