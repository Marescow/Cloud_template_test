/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.audit.provider.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.audit.TypeAuditReportConfig;
import de.hybris.platform.audit.internal.ModelAuditRecordConverter;
import de.hybris.platform.audit.internal.config.AuditReportConfig;
import de.hybris.platform.audit.internal.config.Type;
import de.hybris.platform.core.PK;
import de.hybris.platform.directpersistence.cache.SLDDataContainer;
import de.hybris.platform.directpersistence.cache.SLDDataContainerProvider;
import de.hybris.platform.persistence.audit.AuditType;
import de.hybris.platform.persistence.audit.gateway.AuditRecord;
import de.hybris.platform.persistence.audit.gateway.ReadAuditGateway;
import de.hybris.platform.persistence.audit.internal.AuditEnablementService;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultAuditRecordsProviderUnitTest
{

	private static final AuditReportConfig AUDIT_REPORT_CONFIG = AuditReportConfig.builder()
	                                                                              .withGivenRootType(
			                                                                              Type.builder().withCode("User").build())
	                                                                              .build();

	@Mock
	private ReadAuditGateway readAuditGateway;
	@Mock
	private SLDDataContainerProvider sldDataContainerProvider;
	@Mock
	private ModelAuditRecordConverter converter;
	@Mock
	private AuditEnablementService auditEnablementService;
	@InjectMocks
	private DefaultAuditRecordsProvider provider;

	@Mock
	private AuditRecord currentAuditRecord;

	@Before
	public void setUp() throws Exception
	{
		when(sldDataContainerProvider.getAll(any())).thenReturn(List.of(mock(SLDDataContainer.class)));
		when(converter.toAuditRecord(any(), any(Type.class))).thenReturn(currentAuditRecord);
	}

	@Test
	public void shouldReturnCurrentRecordAndNotFetchHistoricalRecords()
	{
		// given
		final TypeAuditReportConfig config = TypeAuditReportConfig.builder()
		                                                          .withRootTypePk(mock(PK.class))
		                                                          .withConfig(AUDIT_REPORT_CONFIG)
		                                                          .build();

		// when
		final Stream<AuditRecord> result = provider.getRecords(config);

		// then
		assertThat(result).containsExactly(currentAuditRecord);
		verifyNoInteractions(readAuditGateway);
	}

	@Test
	public void shouldReturnHistoricalAndCurrentRecords()
	{
		// given
		final TypeAuditReportConfig config = TypeAuditReportConfig.builder()
		                                                          .withRootTypePk(mock(PK.class))
		                                                          .withFullReport()
		                                                          .withConfig(AUDIT_REPORT_CONFIG)
		                                                          .build();
		final AuditRecord historicalAuditRecord = mock(AuditRecord.class);
		when(currentAuditRecord.getAuditType()).thenReturn(AuditType.CURRENT);
		when(readAuditGateway.search(any())).thenReturn(Stream.of(historicalAuditRecord));
		when(auditEnablementService.isAuditEnabledForType(any(String.class))).thenReturn(true);

		// when
		final Stream<AuditRecord> result = provider.getRecords(config);

		// then
		assertThat(result).containsExactly(historicalAuditRecord, currentAuditRecord);
	}

}
