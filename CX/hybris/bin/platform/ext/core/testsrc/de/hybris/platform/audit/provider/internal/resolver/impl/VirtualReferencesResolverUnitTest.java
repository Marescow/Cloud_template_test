/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.audit.provider.internal.resolver.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.audit.internal.config.AbstractTypedAttribute;
import de.hybris.platform.audit.internal.config.ResolvesBy;
import de.hybris.platform.audit.internal.config.Type;
import de.hybris.platform.audit.internal.config.VirtualAttribute;
import de.hybris.platform.audit.provider.internal.resolver.ReferencesResolver;
import de.hybris.platform.audit.provider.internal.resolver.VirtualReferenceExpressionParser;
import de.hybris.platform.persistence.audit.gateway.AuditRecord;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

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
public class VirtualReferencesResolverUnitTest extends AbstractReferencesResolverUnitTest
{

	private static final String TITLE_TYPE_CODE = "Title";
	private static final String TITLE_ATTRIBUTE_CODE = "title";

	@Mock
	private VirtualReferenceExpressionParser virtualReferenceExpressionParser;
	@InjectMocks
	private VirtualReferencesResolver resolver;

	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		final SearchResult<Object> emptySearchResult = mockEmptySearchResult();
		when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(emptySearchResult);
		when(virtualReferenceExpressionParser.getQualifier(TITLE_ATTRIBUTE_CODE)).thenReturn(TITLE_ATTRIBUTE_CODE);
		when(virtualReferenceExpressionParser.getResolver(any())).thenReturn(new PkVirtualReferenceValuesExtractor());
		auditTypeContextMap = Map.of(getAttribute(), auditTypeContext);
	}

	@Test
	public void shouldReturnCurrentRecordAndNotFetchHistoricalRecords()
	{
		//given
		when(config.isReportOnlyLastState()).thenReturn(true);

		//when
		final Collection<ReferencesResolver.ResolveResult> result = resolver.resolve(config, auditTypeContextMap, List.of());

		//then
		final List<ReferencesResolver.ResolveResult> resultList = result.stream().toList();
		assertThat(resultList).hasSize(1);
		assertThat(resultList.get(0).getRecords()).containsExactly(currentAuditRecord);
		verifyNoInteractions(readAuditGateway);
	}

	@Test
	public void shouldReturnHistoricalAndCurrentRecords()
	{
		//given
		final AuditRecord historicalAuditRecord = mockAuditRecordWithPk(pk);
		when(readAuditGateway.search(any())).thenReturn(Stream.of(historicalAuditRecord));
		when(config.isReportOnlyLastState()).thenReturn(false);

		//when
		final Collection<ReferencesResolver.ResolveResult> result = resolver.resolve(config, auditTypeContextMap, List.of());

		//then
		final List<ReferencesResolver.ResolveResult> resultList = result.stream().toList();
		assertThat(resultList).hasSize(1);
		assertThat(resultList.get(0).getRecords()).containsExactly(currentAuditRecord, historicalAuditRecord);
	}

	AbstractTypedAttribute getAttribute()
	{
		return VirtualAttribute.builder()
		                       .withType(Type.builder().withCode(TITLE_TYPE_CODE).build())
		                       .withResolvesBy(ResolvesBy.builder().withExpression(TITLE_ATTRIBUTE_CODE).build())
		                       .build();
	}

}