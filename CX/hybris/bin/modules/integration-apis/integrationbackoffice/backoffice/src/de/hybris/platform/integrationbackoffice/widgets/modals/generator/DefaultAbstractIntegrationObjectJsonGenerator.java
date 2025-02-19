/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import de.hybris.platform.integrationbackoffice.widgets.modeling.services.ReadService;
import de.hybris.platform.integrationservices.config.ReadOnlyAttributesConfiguration;
import de.hybris.platform.integrationservices.model.DescriptorFactory;
import de.hybris.platform.integrationservices.model.IntegrationObjectDescriptor;
import de.hybris.platform.integrationservices.model.IntegrationObjectModel;
import de.hybris.platform.integrationservices.model.MapDescriptor;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.integrationservices.util.ApplicationBeans;
import de.hybris.platform.integrationservices.util.JsonBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * A base class for JSON generators, which can generate sample integration object payloads for various scenarios.
 */
public abstract class DefaultAbstractIntegrationObjectJsonGenerator implements IntegrationObjectJsonGenerator
{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private DescriptorFactory descriptorFactory;

	/**
	 * Instantiates this generator with required dependencies. This constructor is outdated and should not be used.
	 * Use {@link #DefaultAbstractIntegrationObjectJsonGenerator(DescriptorFactory)}, which wires real required
	 * dependencies.
	 *
	 * @param readService   a services to read metadata for the integration object attribute models.
	 * @param configuration configured attributes that should not be set, i.e. they are read-only, in integration
	 *                      objects.
	 */
	protected DefaultAbstractIntegrationObjectJsonGenerator(final ReadService readService,
	                                                        final ReadOnlyAttributesConfiguration configuration)
	{
		this(null);
	}

	/**
	 * Instantiates this generator with required dependencies.
	 *
	 * @param factory a factory to use for converting an integration object model into an integration object
	 *                descriptor. If {@code null} is passed, then the default implementation that is registered as
	 *                {@code "integrationServicesDescriptorFactory"} Spring bean will be used.
	 */
	protected DefaultAbstractIntegrationObjectJsonGenerator(@Nullable final DescriptorFactory factory)
	{
		descriptorFactory = factory;
	}

	@Override
	public String generateJson(final IntegrationObjectModel integrationObjectModel)
	{
		final IntegrationObjectDescriptor io = getDescriptorFactory().createIntegrationObjectDescriptor(integrationObjectModel);
		return Optional.ofNullable(io)
		               .flatMap(IntegrationObjectDescriptor::getRootItemType)
		               .map(this::constructJson)
		               .map(DefaultAbstractIntegrationObjectJsonGenerator::format)
		               .orElse("");
	}

	private JsonBuilder constructJson(final TypeDescriptor type)
	{
		return new SampleProviders(this::selectAttributes)
				.complexTypeProvider
				.provideValue(null, type);
	}

	abstract Collection<TypeAttributeDescriptor> selectAttributes(TypeDescriptor item);

	private DescriptorFactory getDescriptorFactory()
	{
		if (descriptorFactory == null)
		{
			descriptorFactory = ApplicationBeans.getBean(
					"integrationServicesDescriptorFactory", DescriptorFactory.class);
		}
		return descriptorFactory;
	}

	private static String format(final JsonBuilder json)
	{
		final JsonElement root = JsonParser.parseString(json.build());
		return GSON.toJson(root);
	}

	private static final class SampleProviders
	{
		private final Function<TypeDescriptor, Collection<TypeAttributeDescriptor>> attributeSelector;
		private final UniversalValueProvider universalProvider;
		private final PrimitiveValueProvider primitiveProvider;
		private final MapValueProvider mapProvider;
		private final ComplexTypeValueProvider complexTypeProvider;


		private SampleProviders(final Function<TypeDescriptor, Collection<TypeAttributeDescriptor>> function)
		{
			attributeSelector = function;
			universalProvider = new UniversalValueProvider(this);
			primitiveProvider = new PrimitiveValueProvider();
			complexTypeProvider = new ComplexTypeValueProvider(this);
			mapProvider = new MapValueProvider(this);
		}
	}

	/**
	 * An abstraction for a class capable providing sample values of a certain type.
	 */
	private interface ValueProvider
	{
		/**
		 * Provides value for the given attribute. This method is used when attribute type can be unambiguously derived
		 * from the attribute.
		 *
		 * @param attr an attribute to generate a sample value for.
		 * @return a sample value for the attribute.
		 */
		default Object provideValue(TypeAttributeDescriptor attr)
		{
			return provideValue(attr, attr.getAttributeType());
		}

		/**
		 * Provides value for the given attribute. This method is used when attribute type is ambiguous. For example,
		 * for a map attribute.
		 *
		 * @param attr an attribute to generate a sample value for.
		 * @param type a type of the value to generate.
		 * @return a sample value for the attribute.
		 */
		Object provideValue(TypeAttributeDescriptor attr, TypeDescriptor type);
	}

	private static final class UniversalValueProvider implements ValueProvider
	{
		private final SampleProviders sample;

		private UniversalValueProvider(final SampleProviders providers)
		{
			sample = providers;
		}

		@Override
		public Object provideValue(final TypeAttributeDescriptor attr, final TypeDescriptor type)
		{
			if (type.isPrimitive())
			{
				return sample.primitiveProvider.provideValue(attr, type);
			}
			if (type.isMap() || attr.isLocalized())
			{
				return sample.mapProvider.provideValue(attr, type);
			}
			final JsonBuilder value = sample.complexTypeProvider.provideValue(attr, type);
			return attr.isCollection()
					? List.of(value)
					: value;

		}
	}

	private static class PrimitiveValueProvider implements ValueProvider
	{
		private static final List<Integer> INT_SAMPLES = List.of(123, 34);
		private static final List<String> CHAR_SAMPLES = List.of("a", "z");
		private static final List<String> FLOAT_SAMPLES = List.of("123.0", "34");
		private static final List<Boolean> BOOLEAN_SAMPLES = List.of(true, false);
		private static final List<String> LONG_SAMPLES = List.of("12345", "67890");
		private static final List<String> SHORT_SAMPLES = List.of("123", "34");
		private static final List<Integer> BYTE_SAMPLES = List.of(255, 0);
		private static final List<String> BIG_INT_SAMPLES = List.of("123456", "234567");
		private static final List<String> BIG_DECIMAL_SAMPLES = List.of("123456.00", "234567.00");
		private static final List<String> DATE_SAMPLES = List.of("/Date(1577836800000)/", "/Date(1577836800000)/");
		private static final Map<String, List<?>> PRIMITIVE_SAMPLES = new HashMap<>();

		static
		{
			PRIMITIVE_SAMPLES.put(Integer.class.getName(), INT_SAMPLES);
			PRIMITIVE_SAMPLES.put(Character.class.getName(), CHAR_SAMPLES);
			PRIMITIVE_SAMPLES.put(Double.class.getName(), FLOAT_SAMPLES);
			PRIMITIVE_SAMPLES.put(Boolean.class.getName(), BOOLEAN_SAMPLES);
			PRIMITIVE_SAMPLES.put(Long.class.getName(), LONG_SAMPLES);
			PRIMITIVE_SAMPLES.put(Short.class.getName(), SHORT_SAMPLES);
			PRIMITIVE_SAMPLES.put(Byte.class.getName(), BYTE_SAMPLES);
			PRIMITIVE_SAMPLES.put(Float.class.getName(), FLOAT_SAMPLES);
			PRIMITIVE_SAMPLES.put(BigInteger.class.getName(), BIG_INT_SAMPLES);
			PRIMITIVE_SAMPLES.put(BigDecimal.class.getName(), BIG_DECIMAL_SAMPLES);
			PRIMITIVE_SAMPLES.put(Date.class.getName(), DATE_SAMPLES);
		}

		@Override
		public Object provideValue(final TypeAttributeDescriptor attr, final TypeDescriptor type)
		{
			final String classType = type.getItemCode();
			return String.class.getName().equals(type.getItemCode())
					? getStringSample(attr)
					: getSample(attr, classType);
		}

		private static Object getStringSample(final TypeAttributeDescriptor attr)
		{
			final String sample = "Test_" + attr.getAttributeName();
			return attr.isCollection()
					? asListEntries(List.of(sample + "_1", sample + "_2"))
					: sample;
		}

		private static Object getSample(final TypeAttributeDescriptor attr, final String classType)
		{
			final List<?> sample = PRIMITIVE_SAMPLES.getOrDefault(classType, List.of("Unsupported primitive type"));
			return attr.isCollection()
					? asListEntries(sample)
					: sample.get(0);
		}

		private static <T> List<Map<String, T>> asListEntries(final List<T> values)
		{
			return values.stream()
			             .map(v -> Map.of("value", v))
			             .toList();
		}
	}

	private static final class ComplexTypeValueProvider implements ValueProvider
	{
		private final Deque<TypeDescriptor> processedTypes;
		private final SampleProviders sample;

		private ComplexTypeValueProvider(final SampleProviders providers)
		{
			sample = providers;
			processedTypes = new ArrayDeque<>();
		}

		@Override
		public JsonBuilder provideValue(final TypeAttributeDescriptor attr, final TypeDescriptor type)
		{
			final Collection<TypeAttributeDescriptor> attributes = selectAttributes(type);
			processedTypes.push(type);

			final JsonBuilder json = JsonBuilder.json();
			json.withField("integrationKey", "TEST_KEY");
			attributes.stream()
			          .filter(TypeAttributeDescriptor::isPrimitive)
			          .forEach(a -> populate(json, a));
			attributes.stream()
			          .filter(Predicate.not(TypeAttributeDescriptor::isPrimitive))
			          .forEach(a -> populate(json, a));
			populateLocalizedAttribute(json, attributes);
			processedTypes.pop();
			return json;
		}

		private Collection<TypeAttributeDescriptor> selectAttributes(final TypeDescriptor type)
		{
			final Predicate<TypeAttributeDescriptor> processedTypeCondition = processedTypes.contains(type)
					? (a -> a.isKeyAttribute() || a.isMap())
					: (a -> true);
			return sample.attributeSelector.apply(type)
			                               .stream()
			                               .filter(processedTypeCondition)
			                               .collect(Collectors.toSet());
		}

		private void populate(final JsonBuilder json, final TypeAttributeDescriptor attr)
		{
			final Object sampleValue = sample.universalProvider.provideValue(attr);
			json.withField(attr.getAttributeName(), sampleValue);
		}

		private void populateLocalizedAttribute(final JsonBuilder json, final Collection<TypeAttributeDescriptor> attributes)
		{
			final Map<String, ?> values = attributes.stream()
			                                        .filter(TypeAttributeDescriptor::isLocalized)
			                                        .collect(Collectors.toMap(
					                                        TypeAttributeDescriptor::getAttributeName,
					                                        sample.primitiveProvider::provideValue));
			if (!values.isEmpty())
			{
				final Map<String, Object> localizedValues = new LinkedHashMap<>();
				localizedValues.put("language", "en");
				localizedValues.putAll(values);
				json.withLocalizedAttributes(localizedValues);
			}
		}
	}

	private static class MapValueProvider implements ValueProvider
	{
		private final SampleProviders sample;

		public MapValueProvider(final SampleProviders providers)
		{
			sample = providers;
		}

		@Override
		public Object provideValue(final TypeAttributeDescriptor attr, final TypeDescriptor type)
		{
			return attr.getMapDescriptor()
			           .map(d -> deriveSample(attr, d))
			           .orElse("Invalid map attribute: MapDescriptor is not available");
		}

		private Object deriveSample(final TypeAttributeDescriptor attr, final MapDescriptor descriptor)
		{
			final TypeDescriptor keyType = descriptor.getKeyType();
			final TypeDescriptor valueType = descriptor.getValueType();
			return !(attr.isLocalized() || keyType.isMap() || valueType.isMap())
					? mapValue(attr, keyType, valueType)
					: "Map of map type value is not supported by JSON generation.";
		}

		private List<Map<String, ?>> mapValue(final TypeAttributeDescriptor attr,
		                                      final TypeDescriptor keyType,
		                                      final TypeDescriptor valueType)
		{
			final Map<String, Object> map = new LinkedHashMap<>();
			map.put("key", sample.universalProvider.provideValue(attr, keyType));
			map.put("value", sample.universalProvider.provideValue(attr, valueType));
			return List.of(map);
		}
	}
}
