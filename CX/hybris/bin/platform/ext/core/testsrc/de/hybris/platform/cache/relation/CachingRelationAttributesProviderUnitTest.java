/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cache.relation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.PK;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;

@UnitTest
public class CachingRelationAttributesProviderUnitTest
{

	@Mock
	private Function<PK, RelationAttributes> pkToAttributes;

	@Mock
	private Function<TypeId, Collection<PK>> typeIdToPKs;
	private MockitoSession mockitoSession;

	@Before
	public void setUp() throws Exception
	{
		mockitoSession = Mockito.mockitoSession().initMocks(this).startMocking();
	}

	@After
	public void tearDown() throws Exception
	{
		mockitoSession.finishMocking();
	}

	private Set<Integer> typeCodes(final Integer... typeCodes)
	{
		return Set.of(typeCodes);
	}

	@Test
	public void shouldCacheResultForSinglePkWithoutUsingExtractor()
	{
		final PK givenPk = PK.createFixedCounterPK(123, 1);
		final RelationAttributes givenAttributes = mock(RelationAttributes.class);

		when(pkToAttributes.apply(givenPk)).thenReturn(givenAttributes);
		when(typeIdToPKs.apply(TypeId.fromTypeCode(123))).thenReturn(Set.of(givenPk));
		final CachingRelationAttributesProvider provider = givenProvider(pkToAttributes, typeIdToPKs, typeCodes(123));

		provider.initCache();

		verify(pkToAttributes).apply(givenPk);
		verify(typeIdToPKs).apply(TypeId.fromTypeCode(123));

		final List<RelationAttributes> attributes = provider.getRelationAttributes(TypeId.fromTypePk(givenPk)).toList();
		assertThat(attributes).isNotEmpty().containsExactly(givenAttributes);

		final List<RelationAttributes> attributes2 = provider.getRelationAttributes(TypeId.fromTypePk(givenPk)).toList();
		assertThat(attributes2).isNotEmpty().containsExactly(givenAttributes);

		verifyNoMoreInteractions(pkToAttributes);
		verifyNoMoreInteractions(typeIdToPKs);
	}

	@Test
	public void shouldCacheResultForSinglePkExtractedFromTypeName()
	{
		final PK givenPk = PK.createFixedCounterPK(123, 1);
		final TypeId givenTypeId = TypeId.fromTypeName("test");
		final RelationAttributes givenAttributes = mock(RelationAttributes.class);

		when(pkToAttributes.apply(givenPk)).thenReturn(givenAttributes);
		when(typeIdToPKs.apply(givenTypeId)).thenReturn(Set.of(givenPk));
		when(typeIdToPKs.apply(TypeId.fromTypeCode(123))).thenReturn(Set.of(givenPk));
		final CachingRelationAttributesProvider provider = givenProvider(pkToAttributes, typeIdToPKs, typeCodes(123), Set.of("test"));

		provider.initCache();

		verify(pkToAttributes).apply(givenPk);
		verify(typeIdToPKs).apply(givenTypeId);
		verify(typeIdToPKs).apply(TypeId.fromTypeCode(123));

		final List<RelationAttributes> attributes = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes).isNotEmpty().containsExactly(givenAttributes);

		final List<RelationAttributes> attributes2 = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes2).isNotEmpty().containsExactly(givenAttributes);

		final List<RelationAttributes> attributes3 = provider.getRelationAttributes(TypeId.fromTypePk(givenPk)).toList();
		assertThat(attributes3).isNotEmpty().containsExactly(givenAttributes);

		verifyNoMoreInteractions(pkToAttributes);
		verifyNoMoreInteractions(typeIdToPKs);
	}

	@Test
	public void shouldCacheResultForSinglePkExtractedFromTypeCode()
	{
		final PK givenPk = PK.createFixedCounterPK(123, 1);
		final TypeId givenTypeId = TypeId.fromTypeCode(123);
		final RelationAttributes givenAttributes = mock(RelationAttributes.class);

		when(pkToAttributes.apply(givenPk)).thenReturn(givenAttributes);
		when(typeIdToPKs.apply(givenTypeId)).thenReturn(Set.of(givenPk));
		final CachingRelationAttributesProvider provider = givenProvider(pkToAttributes, typeIdToPKs, typeCodes(123));

		provider.initCache();

		verify(pkToAttributes, times(1)).apply(givenPk);
		verify(typeIdToPKs, times(1)).apply(givenTypeId);

		final List<RelationAttributes> attributes = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes).isNotEmpty().containsExactly(givenAttributes);

		final List<RelationAttributes> attributes2 = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes2).isNotEmpty().containsExactly(givenAttributes);

		final List<RelationAttributes> attributes3 = provider.getRelationAttributes(TypeId.fromTypePk(givenPk)).toList();
		assertThat(attributes3).isNotEmpty().containsExactly(givenAttributes);
		verifyNoMoreInteractions(pkToAttributes);
		verifyNoMoreInteractions(typeIdToPKs);
	}

	@Test
	public void shouldCacheResultsForMultiplePKsExtractedFromTypeCode()
	{
		final PK givenPk1 = PK.createFixedCounterPK(123, 1);
		final PK givenPk2 = PK.createFixedCounterPK(123, 2);
		final TypeId givenTypeId = TypeId.fromTypeCode(123);
		final RelationAttributes givenAttributes1 = mock(RelationAttributes.class);
		final RelationAttributes givenAttributes2 = mock(RelationAttributes.class);

		when(pkToAttributes.apply(givenPk1)).thenReturn(givenAttributes1);
		when(pkToAttributes.apply(givenPk2)).thenReturn(givenAttributes2);
		when(typeIdToPKs.apply(givenTypeId)).thenReturn(Set.of(givenPk1, givenPk2));
		final CachingRelationAttributesProvider provider = givenProvider(pkToAttributes, typeIdToPKs, typeCodes(123));

		provider.initCache();
		verify(pkToAttributes).apply(givenPk1);
		verify(pkToAttributes).apply(givenPk2);
		verify(typeIdToPKs).apply(givenTypeId);

		final List<RelationAttributes> attributes = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes).isNotEmpty().containsExactlyInAnyOrder(givenAttributes1, givenAttributes2);

		final List<RelationAttributes> attributes2 = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes2).isNotEmpty().containsExactlyInAnyOrder(givenAttributes1, givenAttributes2);

		final List<RelationAttributes> attributes3 = provider.getRelationAttributes(TypeId.fromTypePk(givenPk1)).toList();
		assertThat(attributes3).isNotEmpty().containsExactly(givenAttributes1);

		verifyNoMoreInteractions(pkToAttributes);
		verifyNoMoreInteractions(typeIdToPKs);
	}

	@Test
	public void shouldCacheEmptyAttributesWhenEmptyAttributesAreProvided()
	{
		final PK givenPk = PK.createFixedCounterPK(123, 1);
		final RelationAttributes givenAttributes = RelationAttributes.empty();

		when(pkToAttributes.apply(givenPk)).thenReturn(givenAttributes);
		when(typeIdToPKs.apply(TypeId.fromTypeCode(123))).thenReturn(Set.of(givenPk));
		final CachingRelationAttributesProvider provider = givenProvider(pkToAttributes, typeIdToPKs, typeCodes(123));

		provider.initCache();

		verify(pkToAttributes).apply(givenPk);
		verify(typeIdToPKs).apply(TypeId.fromTypeCode(123));

		final List<RelationAttributes> attributes = provider.getRelationAttributes(TypeId.fromTypePk(givenPk)).toList();
		assertThat(attributes).isNotEmpty().containsExactly(givenAttributes);

		final List<RelationAttributes> attributes2 = provider.getRelationAttributes(TypeId.fromTypePk(givenPk)).toList();
		assertThat(attributes2).isNotEmpty().containsExactly(givenAttributes);

		verifyNoMoreInteractions(pkToAttributes);
		verifyNoMoreInteractions(typeIdToPKs);
	}

	@Test
	public void shouldCacheEmptyStreamWhenNoPKsAreExtracted()
	{
		final TypeId givenTypeId = TypeId.fromTypeCode(123);


		when(typeIdToPKs.apply(givenTypeId)).thenReturn(Set.of());
		final CachingRelationAttributesProvider provider = givenProvider(pkToAttributes, typeIdToPKs, typeCodes(123));

		provider.initCache();

		verify(typeIdToPKs, times(1)).apply(givenTypeId);

		final List<RelationAttributes> attributes = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes).isEmpty();

		final List<RelationAttributes> attributes2 = provider.getRelationAttributes(givenTypeId).toList();
		assertThat(attributes2).isEmpty();

		verifyNoMoreInteractions(typeIdToPKs);
		verifyNoInteractions(pkToAttributes);
	}

	private CachingRelationAttributesProvider givenProvider(final Function<PK, RelationAttributes> pkToAttributes,
	                                                        final Function<TypeId, Collection<PK>> typeIdToPKs,
	                                                        final Set<Integer> typeCodes)
	{
		return givenProvider(pkToAttributes, typeIdToPKs, typeCodes, Set.of());
	}


	private CachingRelationAttributesProvider givenProvider(final Function<PK, RelationAttributes> pkToAttributes,
	                                                        final Function<TypeId, Collection<PK>> typeIdToPKs,
	                                                        final Set<Integer> typeCodes,
	                                                        final Set<String> typeNames)
	{
		return new CachingRelationAttributesProvider(pkToAttributes, typeIdToPKs,
				() -> Stream.concat(typeCodes.stream().map(TypeId::fromTypeCode), typeNames.stream().map(TypeId::fromTypeName)));
	}

}