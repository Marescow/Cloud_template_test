/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.xyformsfacades.form.converters.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.xyformsfacades.data.YFormDefinitionData;
import de.hybris.platform.xyformsservices.model.YFormDefinitionModel;

import org.springframework.util.Assert;


/**
 * Converter implementation for {@link YFormDefinitionModel} as source and {@link YFormDefinitionData} as target type.
 */
public class YFormDefinitionPopulator<SOURCE extends YFormDefinitionModel, TARGET extends YFormDefinitionData>
		implements Populator<SOURCE, TARGET>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param source
	 *           the YFormDefinitionModel
	 * @param target
	 *           the YFormDefinitionData to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setContent(source.getContent());
		target.setApplicationId(source.getApplicationId());
		target.setDescription(source.getDescription());
		target.setTitle(source.getTitle());
		target.setFormId(source.getFormId());
		target.setVersion(source.getVersion());
		target.setLastModifiedDate(source.getModifiedtime());
	}
}
