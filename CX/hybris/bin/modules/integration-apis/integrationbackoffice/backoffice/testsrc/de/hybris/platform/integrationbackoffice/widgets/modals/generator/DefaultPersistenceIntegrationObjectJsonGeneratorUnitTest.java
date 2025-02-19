/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.integrationservices.util.JsonObject;
import de.hybris.platform.servicelayer.model.ModelService;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@UnitTest
public class DefaultPersistenceIntegrationObjectJsonGeneratorUnitTest
		extends DefaultAbstractIntegrationObjectJsonGeneratorTestTemplate
{
	private static final ItemModel NEW_ITEM = new ItemModel();

	@Mock
	private ModelService modelService;
	@InjectMocks
	private DefaultPersistenceIntegrationObjectJsonGenerator generator;

	@Override
	protected DefaultAbstractIntegrationObjectJsonGenerator generator()
	{
		return generator;
	}

	@Override
	public void setup()
	{
		super.setup();
		doReturn(NEW_ITEM).when(modelService).create(anyString());
	}

	@Test
	public void generatesJsonWithoutReadOnlyAttribute()
	{
		final TypeAttributeDescriptor readOnly = readOnlyAttribute();
		final TypeDescriptor rootItem = rootType(readOnly);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.exists(path(readOnly))).isFalse();
	}

	@Test
	public void generatesJsonWithoutNotSettablePrimitiveAttribute()
	{
		final TypeAttributeDescriptor readOnly = readOnly(primitiveAttribute("pk", String.class));
		final TypeDescriptor root = rootType(readOnly);
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.exists(path(readOnly))).isFalse();
	}
}
