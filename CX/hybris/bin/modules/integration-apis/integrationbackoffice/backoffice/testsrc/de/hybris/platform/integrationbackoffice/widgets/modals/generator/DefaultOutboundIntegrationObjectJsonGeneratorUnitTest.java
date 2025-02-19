/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.integrationservices.util.JsonObject;

import org.junit.Test;
import org.mockito.InjectMocks;

@UnitTest
public class DefaultOutboundIntegrationObjectJsonGeneratorUnitTest
		extends DefaultAbstractIntegrationObjectJsonGeneratorTestTemplate
{
	@InjectMocks
	private DefaultOutboundIntegrationObjectJsonGenerator generator;

	@Override
	protected DefaultAbstractIntegrationObjectJsonGenerator generator()
	{
		return generator;
	}

	@Test
	public void generatesJsonForReadOnlyAttribute()
	{
		final TypeAttributeDescriptor readOnly = readOnlyAttribute();
		final TypeDescriptor rootItem = rootType(readOnly);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator.generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(readOnly))).isEqualTo(sampleStringValue(readOnly));
		assertThat(json.getString(JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
	}

	@Test
	public void generatesJsonForNotSettablePrimitiveAttribute()
	{
		final TypeAttributeDescriptor readOnly = readOnly(primitiveAttribute("pk", String.class));
		final TypeDescriptor root = rootType(readOnly);
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.exists(path(readOnly))).isTrue();
	}
}
