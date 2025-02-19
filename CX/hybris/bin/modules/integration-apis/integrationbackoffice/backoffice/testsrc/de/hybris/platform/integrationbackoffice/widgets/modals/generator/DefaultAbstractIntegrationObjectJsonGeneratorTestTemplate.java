/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationservices.config.ReadOnlyAttributesConfiguration;
import de.hybris.platform.integrationservices.model.DescriptorFactory;
import de.hybris.platform.integrationservices.model.IntegrationObjectDescriptor;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemAttributeModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectModel;
import de.hybris.platform.integrationservices.model.MapDescriptor;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.integrationservices.util.JsonObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.data.Index;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
abstract class DefaultAbstractIntegrationObjectJsonGeneratorTestTemplate
{
	protected static final String JSON_KEY_ATTRIBUTE = "integrationKey";
	protected static final IntegrationObjectModel IO = new IntegrationObjectModel();
	protected static final String SAMPLE_INTEGRATION_KEY = "TEST_KEY";
	private static final boolean SAMPLE_BOOLEAN = true;
	private static final int SAMPLE_BYTE = 255;
	private static final String SAMPLE_CHARACTER = "a";
	private static final String SAMPLE_DOUBLE = "123.0";
	private static final int SAMPLE_INT = 123;
	private static final String SAMPLE_LONG = "12345";
	private static final String SAMPLE_SHORT = "123";
	private static final String SAMPLE_BIG_DECIMAL = "123456.00";
	private static final String SAMPLE_BIG_INT = "123456";
	private static final String SAMPLE_DATE = "/Date(1577836800000)/";
	private static final String MAP_KEY = "key";
	private static final String MAP_VALUE = "value";
	private static final String READ_ONLY_ATTRIBUTE = "readOnly";
	private static final String LOCALIZED_ATTRIBUTE = "localizedAttributes";

	@Mock(lenient = true)
	private ReadOnlyAttributesConfiguration attributesConfiguration;
	@Mock(lenient = true)
	private DescriptorFactory descriptorFactory;

	@Before
	public void setup()
	{
		doReturn(Set.of(READ_ONLY_ATTRIBUTE)).when(attributesConfiguration).getReadOnlyAttributes();
		doAnswer(call -> integrationObjectDescriptor((IntegrationObjectModel) call.getArgument(0)))
				.when(descriptorFactory)
				.createIntegrationObjectDescriptor(any(IntegrationObjectModel.class));
	}

	protected abstract DefaultAbstractIntegrationObjectJsonGenerator generator();

	@Test
	public void generatesNoJsonWhenIntegrationObjectHasNoItems()
	{
		givenIntegrationObjectWithTypes();

		final String content = generator().generateJson(IO);

		assertThat(content).isEmpty();
	}

	@Test
	public void generatesNoJsonWhenIntegrationObjectHasNoRootItem()
	{
		final TypeDescriptor nonRootType = nonRootType();
		givenIntegrationObjectWithTypes(nonRootType);

		final String content = generator().generateJson(IO);

		assertThat(content).isEmpty();
	}

	@Test
	public void integrationKeyIsGeneratedEvenWhenRootItemHasNoAttributes()
	{
		final TypeDescriptor root = rootType();
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
	}

	@Test
	public void generatesSampleForNotSettableComplexAttribute()
	{
		final TypeAttributeDescriptor id = primitiveAttribute("version", String.class);
		final TypeDescriptor catalogVersion = nonRootType(id);
		final TypeAttributeDescriptor readOnly = readOnly(attribute("catalogVersion", catalogVersion));
		final TypeDescriptor root = rootType(readOnly);
		givenIntegrationObjectWithTypes(root, catalogVersion);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.exists(path(readOnly, id))).isTrue();
	}

	@Test
	public void generatesJsonForStringAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("code", String.class);
		final TypeDescriptor root = rootType(attr);
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(attr))).isEqualTo(sampleStringValue(attr));
		assertThat(json.exists(JSON_KEY_ATTRIBUTE)).isTrue();
		assertThat(json.exists(LOCALIZED_ATTRIBUTE)).isFalse();
	}

	@Test
	public void generatesJsonForStringCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("labels", String.class));
		final TypeDescriptor root = rootType(attr);
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(sampleStringValue(attr) + "_1", sampleStringValue(attr) + "_2");
	}

	@Test
	public void generatesJsonForLocalizedStringAttribute()
	{
		final TypeAttributeDescriptor a1 = localized(primitiveAttribute("name", String.class));
		final TypeAttributeDescriptor a2 = localized(primitiveAttribute("description", String.class));
		final TypeDescriptor root = rootType(a1, a2);
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(a1))).isEqualTo(sampleStringValue(a1));
		assertThat(json.getString(path(a2))).isEqualTo(sampleStringValue(a2));
		assertThat(json.exists(LOCALIZED_ATTRIBUTE)).isTrue();
		final List<Map<String, Object>> localizedAttributes = json.getCollection(LOCALIZED_ATTRIBUTE);
		assertThat(localizedAttributes).hasSize(1)
		                               .contains(Map.of(
				                               "language", "en",
				                               a1.getAttributeName(), sampleStringValue(a1),
				                               a2.getAttributeName(), sampleStringValue(a2)), Index.atIndex(0));
	}

	@Test
	public void doesNotSupportLocalizedReferenceAttribute()
	{
		final TypeAttributeDescriptor code = primitiveAttribute("code", String.class);
		final TypeDescriptor nonPrimitive = nonRootType(code);
		final TypeAttributeDescriptor locAttr = localized(attribute("catalog", nonPrimitive));
		final TypeDescriptor root = rootType(locAttr);
		givenIntegrationObjectWithTypes(root);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(locAttr))).isEqualTo("Invalid map attribute: MapDescriptor is not available");
		assertThat(json.exists(LOCALIZED_ATTRIBUTE)).isTrue();
		final List<Map<String, Object>> localizedAttributes = json.getCollection(LOCALIZED_ATTRIBUTE);
		assertThat(localizedAttributes).hasSize(1)
		                               .contains(Map.of(
						                               "language", "en",
						                               locAttr.getAttributeName(), "Unsupported primitive type"),
				                               Index.atIndex(0));
	}

	@Test
	public void generatesJsonForBooleanAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("locked", Boolean.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_BOOLEAN);
	}

	@Test
	public void generatesJsonForBooleanCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("flags", Boolean.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(true, false);
	}

	@Test
	public void generatesJsonForByteAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("byte", Byte.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_BYTE);
	}

	@Test
	public void generatesJsonForByteCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("bytes", Byte.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_BYTE, 0);
	}

	@Test
	public void generatesJsonForCharAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("character", Character.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(attr))).isEqualTo(SAMPLE_CHARACTER);
	}

	@Test
	public void generatesJsonForCharCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("letters", Character.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_CHARACTER, "z");
	}

	@Test
	public void generatesJsonForDoubleAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("amount", Double.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_DOUBLE);
	}

	@Test
	public void generatesJsonForDoubleCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("amounts", Double.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_DOUBLE, "34");
	}

	@Test
	public void generatesJsonForFloatAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("ratio", Float.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_DOUBLE);
	}

	@Test
	public void generatesJsonForFloatCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("ratios", Float.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_DOUBLE, "34");
	}

	@Test
	public void generatesJsonForIntAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("count", Integer.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_INT);
	}

	@Test
	public void generatesJsonForIntCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("counts", Integer.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_INT, 34);
	}

	@Test
	public void generatesJsonForLongAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("count", Long.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_LONG);
	}

	@Test
	public void generatesJsonForLongCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("counts", Long.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_LONG, "67890");
	}

	@Test
	public void generatesJsonForShortAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("lengthInMillimeters", Short.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_SHORT);
	}

	@Test
	public void generatesJsonForShortCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("lengthInMillimeters", Short.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_SHORT, "34");
	}

	@Test
	public void generatesJsonForBigIntAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("hugeCount", BigInteger.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_BIG_INT);
	}

	@Test
	public void generatesJsonForBigIntCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("hugeCounts", BigInteger.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_BIG_INT, "234567");
	}

	@Test
	public void generatesJsonBigDecimalAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("fraction", BigDecimal.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_BIG_DECIMAL);
	}

	@Test
	public void generatesJsonBigDecimalCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("fractions", BigDecimal.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_BIG_DECIMAL, "234567.00");
	}

	@Test
	public void generatesJsonForDateAttribute()
	{
		final TypeAttributeDescriptor attr = primitiveAttribute("expiredBy", Date.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getObject(path(attr))).isEqualTo(SAMPLE_DATE);
	}

	@Test
	public void generatesJsonForDateCollectionAttribute()
	{
		final TypeAttributeDescriptor attr = collection(primitiveAttribute("testDates", Date.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Object> values = getCollectionValues(json, attr);
		assertThat(values).containsExactly(SAMPLE_DATE, SAMPLE_DATE);
	}

	@Test
	public void generatesJsonForStringToIntMapAttribute()
	{
		final TypeAttributeDescriptor attr = mapAttribute("stringToInt", String.class, Integer.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Map<String, Object>> map = json.getCollection(path(attr));
		assertThat(map).hasSize(1)
		               .contains(Map.of(
				               MAP_KEY, sampleStringValue(attr),
				               MAP_VALUE, SAMPLE_INT), Index.atIndex(0));
	}

	@Test
	public void generatesJsonForIntToStringMapAttribute()
	{
		final TypeAttributeDescriptor attr = mapAttribute("intToString", Integer.class, String.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Map<String, Object>> map = json.getCollection(path(attr));
		assertThat(map).hasSize(1)
		               .contains(Map.of(
				               MAP_KEY, SAMPLE_INT,
				               MAP_VALUE, sampleStringValue(attr)), Index.atIndex(0));
	}

	@Test
	public void doesNotSupportMapOfMapsAttribute()
	{
		final TypeAttributeDescriptor attr = mapAttribute("mapOfMaps", String.class, Map.class);
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(attr))).isEqualTo("Map of map type value is not supported by JSON generation.");
	}

	@Test
	public void doesNotSupportLocalizedMapAttribute()
	{
		final TypeAttributeDescriptor attr = localized(mapAttribute("localizedMap", String.class, Byte.class));
		final TypeDescriptor rootItem = rootType(attr);
		givenIntegrationObjectWithTypes(rootItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(attr))).isEqualTo("Map of map type value is not supported by JSON generation.");
		assertThat(json.exists(LOCALIZED_ATTRIBUTE)).isTrue();
		final List<Map<String, Object>> localizedAttributes = json.getCollection(LOCALIZED_ATTRIBUTE);
		assertThat(localizedAttributes).hasSize(1)
		                               .contains(Map.of(
						                               "language", "en",
						                               attr.getAttributeName(), "Unsupported primitive type"),
				                               Index.atIndex(0));
	}

	@Test
	public void generatesJsonForAnAttributeReferencingAnotherItem()
	{
		final TypeAttributeDescriptor nestedId = primitiveAttribute("id", String.class);
		final TypeDescriptor nestedItem = nonRootType(nestedId);
		final TypeAttributeDescriptor refAttr = attribute("catalog", nestedItem);
		final TypeDescriptor rootItem = rootType(refAttr);
		givenIntegrationObjectWithTypes(rootItem, nestedItem);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(refAttr, nestedId))).isEqualTo(sampleStringValue(nestedId));
	}

	@Test
	public void generatesJsonForMultipleAttributesReferencingOtherItems()
	{
		final TypeAttributeDescriptor unitCode = primitiveAttribute("code", String.class);
		final TypeDescriptor unit = nonRootType(unitCode);
		final TypeAttributeDescriptor catalogId = primitiveAttribute("id", String.class);
		final TypeDescriptor catalog = nonRootType(catalogId);
		final TypeAttributeDescriptor catalogRef = attribute("catalog", catalog);
		final TypeAttributeDescriptor unitRef = attribute("unit", unit);
		final TypeAttributeDescriptor uid = primitiveAttribute("uid", String.class);
		final TypeDescriptor rootItem = rootType(uid, catalogRef, unitRef);
		givenIntegrationObjectWithTypes(rootItem, unit, catalog);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(uid))).isEqualTo(sampleStringValue(uid));
		assertThat(json.getString(path(unitRef, unitCode))).isEqualTo(sampleStringValue(unitCode));
		assertThat(json.getString(path(catalogRef, catalogId))).isEqualTo(sampleStringValue(catalogId));
		assertThat(json.getString(JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
		assertThat(json.getString(path(unitRef) + "." + JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
		assertThat(json.getString(path(catalogRef) + "." + JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
	}

	@Test
	public void generatesJsonForDeeplyNestedAttributesReferencingOtherItems()
	{
		final TypeAttributeDescriptor catalogId = primitiveAttribute("id", String.class);
		final TypeDescriptor catalog = nonRootType(catalogId);
		final TypeAttributeDescriptor version = primitiveAttribute("version", String.class);
		final TypeAttributeDescriptor catalogRef = attribute("catalog", catalog);
		final TypeDescriptor catalogVersion = nonRootType(version, catalogRef);
		final TypeAttributeDescriptor versionRef = attribute("catalogVersion", catalogVersion);
		final TypeAttributeDescriptor code = primitiveAttribute("code", String.class);
		final TypeDescriptor rootItem = rootType(code, versionRef);
		givenIntegrationObjectWithTypes(rootItem, catalogVersion, catalog);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(code))).isEqualTo(sampleStringValue(code));
		assertThat(json.getString(path(versionRef, version))).isEqualTo(sampleStringValue(version));
		assertThat(json.getString(path(versionRef, catalogRef, catalogId))).isEqualTo(sampleStringValue(catalogId));
		assertThat(json.getString(JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
		assertThat(json.getString(path(versionRef) + "." + JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
		assertThat(json.getString(path(versionRef, catalogRef) + "." + JSON_KEY_ATTRIBUTE)).isEqualTo(SAMPLE_INTEGRATION_KEY);
	}

	@Test
	public void generatesJsonForReferenceAttributesFormingCircularDependencyToTypeWithPrimitiveKeyAttribute()
	{
		final TypeDescriptor address = nonRootType();
		final TypeAttributeDescriptor addressRef = attribute("address", address);
		final TypeAttributeDescriptor keyAttr = key(primitiveAttribute("uid", String.class));
		final TypeAttributeDescriptor nonKeyAttr = primitiveAttribute("name", String.class);
		final TypeDescriptor rootItem = rootType(keyAttr, nonKeyAttr, addressRef);
		final TypeAttributeDescriptor addressId = primitiveAttribute("id", String.class);
		final TypeAttributeDescriptor rootRef = attribute("root", rootItem);
		doReturn(Set.of(addressId, rootRef)).when(address).getAttributes();
		givenIntegrationObjectWithTypes(rootItem, address);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(keyAttr))).isEqualTo(sampleStringValue(keyAttr));
		assertThat(json.getString(path(nonKeyAttr))).isEqualTo(sampleStringValue(nonKeyAttr));
		assertThat(json.getString(path(addressRef, addressId))).isEqualTo(sampleStringValue(addressId));
		assertThat(json.getString(path(addressRef, rootRef, keyAttr))).isEqualTo(sampleStringValue(keyAttr));
		assertThat(json.exists(path(addressRef, rootRef, nonKeyAttr))).isFalse();
		assertThat(json.exists(path(addressRef, rootRef, addressRef))).isFalse();
	}

	@Test
	public void generatesJsonForReferenceAttributesFormingCircularDependencyToTypeWithReferenceKeyAttribute()
	{
		final TypeAttributeDescriptor orgId = primitiveAttribute("id", Long.class);
		final TypeDescriptor org = nonRootType(orgId);
		final TypeDescriptor address = nonRootType();
		final TypeAttributeDescriptor addressRef = attribute("address", address);
		final TypeAttributeDescriptor orgRef = key(attribute("organization", org));
		final TypeDescriptor rootItem = rootType(orgRef, addressRef);
		final TypeAttributeDescriptor addressId = primitiveAttribute("id", String.class);
		final TypeAttributeDescriptor rootRef = attribute("root", rootItem);
		doReturn(Set.of(addressId, rootRef)).when(address).getAttributes();
		givenIntegrationObjectWithTypes(rootItem, address);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.getString(path(orgRef, orgId))).isEqualTo(SAMPLE_LONG);
		assertThat(json.getString(path(addressRef, addressId))).isEqualTo(sampleStringValue(addressId));
		assertThat(json.getString(path(addressRef, rootRef, orgRef, orgId))).isEqualTo(SAMPLE_LONG);
		assertThat(json.exists(path(addressRef, rootRef, addressRef))).isFalse();
	}

	@Test
	public void generatesJsonForMultipleAttributesReferencingSameType()
	{
		final TypeAttributeDescriptor regionCode = primitiveAttribute("code", String.class);
		final TypeDescriptor region = nonRootType(regionCode);
		final TypeAttributeDescriptor regionRef = attribute("region", region);
		final TypeDescriptor address = nonRootType(regionRef);
		final TypeAttributeDescriptor billingAddressRef = attribute("billingAddress", address);
		final TypeAttributeDescriptor shippingAddressRef = attribute("shippingAddress", address);
		final TypeDescriptor rootItem = rootType(billingAddressRef, shippingAddressRef);
		givenIntegrationObjectWithTypes(rootItem, address, region);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		assertThat(json.exists(path(billingAddressRef, regionRef, regionCode))).isTrue();
		assertThat(json.exists(path(shippingAddressRef, regionRef, regionCode))).isTrue();
	}

	@Test
	public void generatesJsonForReferenceCollectionAttribute()
	{
		final TypeAttributeDescriptor addressId = primitiveAttribute("id", String.class);
		final TypeDescriptor address = nonRootType(addressId);
		final TypeAttributeDescriptor addressesRef = collection(attribute("addresses", address));
		final TypeDescriptor rootItem = rootType(addressesRef);
		givenIntegrationObjectWithTypes(rootItem, address);

		final String content = generator().generateJson(IO);

		assertThat(content).isNotEmpty();
		final JsonObject json = JsonObject.createFrom(content);
		final List<Map<String, Object>> addresses = json.getCollection(path(addressesRef));
		assertThat(addresses).hasSize(1)
		                     .contains(Map.of(
				                     addressId.getAttributeName(), sampleStringValue(addressId),
				                     JSON_KEY_ATTRIBUTE, SAMPLE_INTEGRATION_KEY), Index.atIndex(0));
	}

	protected static IntegrationObjectDescriptor integrationObjectDescriptor(final IntegrationObjectModel io)
	{
		final Set<TypeDescriptor> typeDescriptors = io.getItems()
				.stream()
				.map(DefaultAbstractIntegrationObjectJsonGeneratorTestTemplate::typeDescriptor)
				.collect(Collectors.toSet());
		return integrationObjectDescriptor(typeDescriptors);
	}

	protected void givenIntegrationObjectWithTypes(final TypeDescriptor... types)
	{
		final IntegrationObjectDescriptor io = integrationObjectDescriptor(Set.of(types));
		doReturn(io).when(descriptorFactory).createIntegrationObjectDescriptor(IO);
	}

	protected static IntegrationObjectDescriptor integrationObjectDescriptor(final Set<TypeDescriptor> types)
	{
		final Optional<TypeDescriptor> root = types.stream()
				.filter(TypeDescriptor::isRoot)
				.findFirst();
		final var ioDesc = mock(IntegrationObjectDescriptor.class);
		doReturn(root).when(ioDesc).getRootItemType();
		return ioDesc;
	}

	private static TypeDescriptor typeDescriptor(final IntegrationObjectItemModel model)
	{
		final var descriptor = mock(TypeDescriptor.class);
		doReturn(Boolean.TRUE.equals(model.getRoot())).when(descriptor).isRoot();
		return descriptor;
	}

	protected static TypeDescriptor rootType(final TypeAttributeDescriptor... attributes)
	{
		final TypeDescriptor item = nonRootType(attributes);
		doReturn(true).when(item).isRoot();
		return item;
	}

	private static TypeDescriptor nonRootType(final TypeAttributeDescriptor... attributes)
	{
		final TypeDescriptor descriptor = mock(TypeDescriptor.class);
		doReturn(Set.of(attributes)).when(descriptor).getAttributes();
		lenient().doReturn("SomeComplexType").when(descriptor).getTypeCode();
		return descriptor;
	}

	private static TypeDescriptor type(final Class<?> type)
	{
		return Map.class == type
				? mapType()
				: primitiveType(type);
	}

	private static TypeDescriptor primitiveType(final Class<?> type)
	{
		final var descriptor = mock(TypeDescriptor.class);
		doReturn(type.getName()).when(descriptor).getItemCode();
		doReturn(true).when(descriptor).isPrimitive();
		return descriptor;
	}

	private static TypeDescriptor mapType()
	{
		final var descriptor = mock(TypeDescriptor.class);
		doReturn(true).when(descriptor).isMap();
		return descriptor;
	}

	protected static TypeAttributeDescriptor readOnlyAttribute()
	{
		return primitiveAttribute(READ_ONLY_ATTRIBUTE, String.class);
	}

	protected static TypeAttributeDescriptor readOnly(final TypeAttributeDescriptor attr)
	{
		lenient().doReturn(false).when(attr).isSettable(any(ItemModel.class));
		return attr;
	}

	protected static TypeAttributeDescriptor primitiveAttribute(final String name, final Class<?> type)
	{
		final TypeDescriptor attributeType = primitiveType(type);
		final TypeAttributeDescriptor attribute = attribute(name, attributeType);
		doReturn(true).when(attribute).isPrimitive();
		return attribute;
	}

	private static TypeAttributeDescriptor localized(final TypeAttributeDescriptor attr)
	{
		doReturn(true).when(attr).isLocalized();
		return attr;
	}

	private static TypeAttributeDescriptor mapAttribute(
			final String name, final Class<?> key, final Class<?> value)
	{
		final TypeDescriptor keyType = type(key);
		final TypeDescriptor valueType = type(value);

		final var map = mock(MapDescriptor.class);
		doReturn(keyType).when(map).getKeyType();
		doReturn(valueType).when(map).getValueType();

		final TypeAttributeDescriptor attribute = attribute(name, mapType());
		doReturn(Optional.of(map)).when(attribute).getMapDescriptor();
		return attribute;
	}

	private static TypeAttributeDescriptor key(final TypeAttributeDescriptor attr)
	{
		doReturn(true).when(attr).isKeyAttribute();
		return attr;
	}

	private static TypeAttributeDescriptor collection(final TypeAttributeDescriptor attr)
	{
		doReturn(true).when(attr).isCollection();
		return attr;
	}

	private static TypeAttributeDescriptor attribute(final String name, final TypeDescriptor type)
	{
		final var attribute = mock(TypeAttributeDescriptor.class);
		doReturn(type).when(attribute).getAttributeType();
		doReturn(name).when(attribute).getAttributeName();
		lenient().doReturn(true).when(attribute).isSettable(any(ItemModel.class));
		return attribute;
	}

	protected static String path(final IntegrationObjectItemAttributeModel... attributes)
	{
		return Stream.of(attributes)
		             .map(IntegrationObjectItemAttributeModel::getAttributeName)
		             .reduce("", (p, n) -> p + "." + n)
		             .substring(1);
	}

	protected static String path(final TypeAttributeDescriptor... attributes)
	{
		return Stream.of(attributes)
		             .map(TypeAttributeDescriptor::getAttributeName)
		             .reduce("", (p, n) -> p + "." + n)
		             .substring(1);
	}

	protected static String sampleStringValue(final TypeAttributeDescriptor attribute)
	{
		return "Test_" + attribute.getAttributeName();
	}

	private static List<Object> getCollectionValues(final JsonObject json, final TypeAttributeDescriptor attr)
	{
		return json.getCollection(path(attr))
		    .stream()
		    .map(m -> m.get("value"))
		    .toList();	}
}
