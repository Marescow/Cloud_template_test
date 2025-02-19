/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.audit.provider.internal.resolver.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.audit.internal.config.AbstractTypedAttribute;
import de.hybris.platform.audit.internal.config.RelationAttribute;
import de.hybris.platform.audit.internal.config.Type;
import de.hybris.platform.audit.provider.internal.resolver.ReferencesResolver.ResolveResult;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.type.AttributeDescriptorModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.persistence.audit.gateway.AuditRecord;
import de.hybris.platform.persistence.audit.gateway.AuditSearchQuery;
import de.hybris.platform.persistence.audit.gateway.LinkAuditRecord;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ManyToManyReferencesResolverUnitTest extends AbstractReferencesResolverUnitTest
{
	private static final String BASE_TYPE_CODE = "User";
	private static final String LINK_TYPE_CODE = "PrincipalGroupRelation";
	private static final String REFERENCE_TYPE_CODE = "UserGroup";

	@Mock
	private TypeService typeService;
	@InjectMocks
	private ManyToManyReferencesResolver resolver;

	@Mock
	private LinkAuditRecord linkAuditRecord;
	@Mock
	private ComposedTypeModel baseType;
	@Mock
	private AttributeDescriptorModel attributeDescriptor;

	@Override
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		final SearchResult<Object> emptySearchResult = mockEmptySearchResult();
		when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(emptySearchResult);
		when(baseAuditRecord.getType()).thenReturn(BASE_TYPE_CODE);
		when(converter.toLinkAuditRecord(any(), any(), any())).thenReturn(linkAuditRecord);
		when(linkAuditRecord.getChildPk()).thenReturn(mock(PK.class));
		when(baseType.getCode()).thenReturn(BASE_TYPE_CODE);
		when(attributeDescriptor.getAttributeType()).thenReturn(baseType);
		when(typeService.getTypeForCode(any())).thenReturn(baseType);
		when(typeService.getAttributeDescriptor(nullable(String.class), nullable(String.class))).thenReturn(attributeDescriptor);
		auditTypeContextMap = Map.of(getAttribute(), auditTypeContext);
	}

	@Test
	public void shouldReturnCurrentRecordAndNotFetchHistoricalRecords()
	{
		// given
		when(config.isReportOnlyLastState()).thenReturn(true);

		// when
		final Collection<ResolveResult> result = resolver.resolve(config, auditTypeContextMap, baseRecords);

		// then
		final List<ResolveResult> resultList = result.stream().toList();
		assertThat(resultList).hasSize(2);
		assertThat(resultList.get(0).getRecords()).containsExactly(linkAuditRecord);
		assertThat(resultList.get(1).getRecords()).containsExactly(currentAuditRecord);
		verifyNoInteractions(readAuditGateway);
	}

	@Test
	public void shouldReturnHistoricalAndCurrentRecords()
	{
		// given
		final LinkAuditRecord historicalLinkAuditRecord = mock(LinkAuditRecord.class);
		when(historicalLinkAuditRecord.getChildPk()).thenReturn(mock(PK.class));
		final AuditRecord historicalTargetAuditRecord = mockAuditRecordWithPk(pk);
		when(config.isReportOnlyLastState()).thenReturn(false);

		when(readAuditGateway.search(any())).thenAnswer(invocationOnMock -> {
			final AuditSearchQuery argument = invocationOnMock.getArgument(0);
			if (LINK_TYPE_CODE.equals(argument.getTypeCode()))
			{
				return Stream.of(historicalLinkAuditRecord);
			}
			else if (REFERENCE_TYPE_CODE.equals(argument.getTypeCode()))
			{
				return Stream.of(historicalTargetAuditRecord);
			}
			else
			{
				throw new IllegalStateException("Unexpected search query");
			}
		});

		// when
		final Collection<ResolveResult> result = resolver.resolve(config, auditTypeContextMap, baseRecords);

		// then
		final List<ResolveResult> resultList = result.stream().toList();
		assertThat(resultList).hasSize(3);
		assertThat(resultList.get(0).getTypeCode()).isEqualTo(LINK_TYPE_CODE);
		assertThat(resultList.get(0).getRecords()).containsExactly(linkAuditRecord);
		assertThat(resultList.get(1).getTypeCode()).isEqualTo(LINK_TYPE_CODE);
		assertThat(resultList.get(1).getRecords()).containsExactly(historicalLinkAuditRecord);
		assertThat(resultList.get(2).getTypeCode()).isEqualTo(REFERENCE_TYPE_CODE);
		assertThat(resultList.get(2).getRecords()).containsExactly(currentAuditRecord, historicalTargetAuditRecord);
	}

	AbstractTypedAttribute getAttribute()
	{
		return RelationAttribute.builder()
		                        .withRelation(LINK_TYPE_CODE)
		                        .withTargetType(Type.builder().withCode(REFERENCE_TYPE_CODE).build())
		                        .build();
	}

}

