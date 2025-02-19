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
import de.hybris.platform.audit.internal.config.ReferenceAttribute;
import de.hybris.platform.audit.internal.config.ResolvesBy;
import de.hybris.platform.audit.internal.config.Type;
import de.hybris.platform.audit.provider.internal.resolver.ReferencesResolver;
import de.hybris.platform.persistence.audit.gateway.AuditRecord;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class TypeReferencesResolverUnitTest extends AbstractReferencesResolverUnitTest
{

	private static final String ADDRESS_TYPE_CODE = "Address";
	private static final String DEFAULT_ADDRESS_ATTRIBUTE_CODE = "defaultAddress";

	@InjectMocks
	private TypeReferencesResolver resolver;

	@Override
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		when(baseAuditRecord.getPk()).thenReturn(basePk);
		when(baseAuditRecord.getAttributeBeforeOperation(any())).thenReturn(pk);
		auditTypeContextMap = Map.of(getAttribute(), auditTypeContext);
	}

	@Test
	public void shouldReturnCurrentRecordAndNotFetchHistoricalRecords()
	{
		// given
		when(config.isReportOnlyLastState()).thenReturn(true);

		// when
		final Collection<ReferencesResolver.ResolveResult> result = resolver.resolve(config, auditTypeContextMap, baseRecords);

		// then
		final List<ReferencesResolver.ResolveResult> resultList = result.stream().toList();
		assertThat(resultList).hasSize(1);
		assertThat(resultList.get(0).getRecords()).containsExactly(currentAuditRecord);
		verifyNoInteractions(readAuditGateway);
	}

	@Test
	public void shouldReturnHistoricalAndCurrentRecords()
	{
		// given
		final AuditRecord historicalRecord = mockAuditRecordWithPk(pk);
		when(readAuditGateway.search(any())).thenReturn(Stream.of(historicalRecord));
		when(config.isReportOnlyLastState()).thenReturn(false);

		// when
		final Collection<ReferencesResolver.ResolveResult> result = resolver.resolve(config, auditTypeContextMap, baseRecords);

		// then
		final List<ReferencesResolver.ResolveResult> resultList = result.stream().toList();
		assertThat(resultList).hasSize(1);
		assertThat(resultList.get(0).getRecords()).containsExactly(currentAuditRecord, historicalRecord);
	}

	AbstractTypedAttribute getAttribute()
	{
		return ReferenceAttribute.builder()
		                         .withType(Type.builder().withCode(ADDRESS_TYPE_CODE).build())
		                         .withResolvesBy(ResolvesBy.builder().withExpression(DEFAULT_ADDRESS_ATTRIBUTE_CODE).build())
		                         .build();
	}
}