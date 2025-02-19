/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.audit.provider.internal.resolver.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.platform.audit.TypeAuditReportConfig;
import de.hybris.platform.audit.internal.ModelAuditRecordConverter;
import de.hybris.platform.audit.internal.config.AbstractTypedAttribute;
import de.hybris.platform.core.PK;
import de.hybris.platform.directpersistence.cache.SLDDataContainer;
import de.hybris.platform.directpersistence.cache.SLDDataContainerProvider;
import de.hybris.platform.persistence.audit.gateway.AuditRecord;
import de.hybris.platform.persistence.audit.gateway.ReadAuditGateway;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.mockito.Mock;

public abstract class AbstractReferencesResolverUnitTest
{

	@Mock
	ModelAuditRecordConverter converter;
	@Mock
	ReadAuditGateway readAuditGateway;
	@Mock
	SLDDataContainerProvider sldDataContainerProvider;
	@Mock
	FlexibleSearchService flexibleSearchService;

	@Mock
	AuditRecord baseAuditRecord;
	@Mock
	AuditRecord currentAuditRecord;
	@Mock
	PK basePk;
	@Mock
	PK pk;
	@Mock
	TypeAuditReportConfig config;
	@Mock
	AuditTypeContext<AuditRecord> auditTypeContext;
	Map<AbstractTypedAttribute, AuditTypeContext<AuditRecord>> auditTypeContextMap;
	Collection<AuditRecord> baseRecords;

	@Before
	public void setUp() throws Exception
	{
		when(sldDataContainerProvider.getAll(any())).thenReturn(List.of(mock(SLDDataContainer.class)));
		when(converter.toAuditRecord(any(), any(String.class))).thenReturn(currentAuditRecord);

		when(currentAuditRecord.getPk()).thenReturn(pk);
		when(auditTypeContext.getBasePKs()).thenReturn(Set.of(basePk));

		baseRecords = List.of(baseAuditRecord);
	}

	static AuditRecord mockAuditRecordWithPk(final PK pk)
	{
		final AuditRecord auditRecord = mock(AuditRecord.class);
		when(auditRecord.getPk()).thenReturn(pk);
		return auditRecord;
	}

	static SearchResult<Object> mockEmptySearchResult()
	{
		final SearchResult<Object> searchResult = mock(SearchResult.class);
		when(searchResult.getResult()).thenReturn(List.of());
		return searchResult;
	}

}