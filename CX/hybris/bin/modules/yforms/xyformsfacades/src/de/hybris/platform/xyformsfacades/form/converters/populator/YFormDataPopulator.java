/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.xyformsfacades.form.converters.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.xyformsfacades.data.YFormDataData;
import de.hybris.platform.xyformsfacades.data.YFormDefinitionData;
import de.hybris.platform.xyformsservices.model.YFormDataModel;
import de.hybris.platform.xyformsservices.model.YFormDefinitionModel;

import javax.annotation.Resource;

import org.springframework.util.Assert;


/**
 * Converter implementation for {@link YFormDataModel} as source and {@link YFormDataData} as target type.
 */
public class YFormDataPopulator<SOURCE extends YFormDataModel, TARGET extends YFormDataData> implements Populator<SOURCE, TARGET>
{
	@Resource
	private Converter<YFormDefinitionModel, YFormDefinitionData> yformDefinitionConverter;

	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param source
	 *           the source object
	 * @param target
	 *           the target to fill
	 * @throws ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setContent(source.getContent());
		target.setId(source.getId());
		target.setType(source.getType());
		target.setRefId(source.getRefId());
		target.setCreatedDate(source.getCreationtime());
		target.setLastModifiedDate(source.getModifiedtime());
		final YFormDefinitionData yFormDefinitionData = getYFormDefinitionConverter().convert(source.getFormDefinition());
		target.setFormDefinition(yFormDefinitionData);
	}

	protected Converter<YFormDefinitionModel, YFormDefinitionData> getYFormDefinitionConverter()
	{
		return yformDefinitionConverter;
	}

	public void setYFormDefinitionConverter(final Converter<YFormDefinitionModel, YFormDefinitionData> yformDefinitionConverter)
	{
		this.yformDefinitionConverter = yformDefinitionConverter;
	}
}
