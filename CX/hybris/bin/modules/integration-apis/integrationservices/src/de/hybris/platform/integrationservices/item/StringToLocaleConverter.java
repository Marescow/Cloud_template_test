/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices.item;

import java.util.Locale;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts {@link String} presentation of a locale to a {@link Locale}. This implementation ignores any parts of the {@code Locale}
 * besides language and country because that is the level of locale support inside the ECP. Variant and extension parts won't make
 * any difference in the application.
 */
public class StringToLocaleConverter implements Converter<String, Locale>
{
	private static final Pattern TAG_PART_EXTRACTOR = Pattern.compile("[-_]");

	@Override
	public Locale convert(@Nullable final String tag)
	{
		if (tag != null)
		{
			final String[] parts = extractTagParts(tag);

			final String language = parts[0];
			final String country = parts.length > 1 ? parts[1] : StringUtils.EMPTY;

			return new Locale(language, country);
		}
		return null;
	}

	private String[] extractTagParts(final String input)
	{
		return TAG_PART_EXTRACTOR.splitAsStream(input).toArray(String[]::new);
	}
}