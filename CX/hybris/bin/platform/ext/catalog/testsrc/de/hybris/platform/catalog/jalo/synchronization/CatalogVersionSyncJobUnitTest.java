/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.catalog.jalo.synchronization;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.RelationDescriptor;
import de.hybris.platform.jalo.type.RelationType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.testframework.assertions.assertj.TestLogListenerAssert;
import de.hybris.platform.testframework.log.TestLogListener;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.RelationsInfo;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class CatalogVersionSyncJobUnitTest
{
	private static final String SYNC_MAX_ITEM_PROPERTY = "catalog.sync.partial.max.items";
	private static final int SYNC_MAX_ITEM = 500;
	private static final String CRON_JOB_CODE = "cronJobCode";
	private static final String RUNNING_CRON_JOB_CODE = "runningCronJobCode";

	@Spy
	private CatalogVersionSyncJob catalogVersionSyncJob;

	@Mock
	private CatalogVersionSyncCronJob cronJob;
	@Mock
	private CatalogVersionSyncCronJob runningCronJob;
	@Mock
	private EnumerationValue runningCronJobStatus;
	@Mock
	private SyncSchedule syncSchedule;
	@Mock
	private PK productPk;
	@Mock
	private PK cronJobPk;
	@Mock
	private PK runningCronJobPk;
	@Mock
	private JaloSession jaloSession;
	@Mock
	private Tenant tenant;
	@Mock
	private TypeManager typeManager;
	@Mock
	private ComposedType type;
	@Mock
	private RelationDescriptor relationDescriptor;
	@Mock
	private RelationType relationType;

	private final TestLogListener testLogListener = new TestLogListener();

	@Before
	public void setUp()
	{
		initMocks();
		testLogListener.attach();
	}

	@After
	public void tearDown()
	{
		testLogListener.detach();
	}

	@Test
	public void testCanPerformWhenSchedulesOverlap()
	{
		try (final MockedStatic<JaloSession> jaloSessionStatic = Mockito.mockStatic(JaloSession.class);
		     final MockedStatic<Config> configStatic = Mockito.mockStatic(Config.class);
		     final MockedStatic<Registry> registryStatic = Mockito.mockStatic(Registry.class);
		     final MockedStatic<TypeManager> typeManagerStatic = Mockito.mockStatic(TypeManager.class)
		)
		{
			//given
			jaloSessionStatic.when(() -> JaloSession.getCurrentSession(any())).thenReturn(jaloSession);
			configStatic.when(() -> Config.getInt(SYNC_MAX_ITEM_PROPERTY, SYNC_MAX_ITEM)).thenReturn(SYNC_MAX_ITEM);
			registryStatic.when(Registry::getCurrentTenant).thenReturn(tenant);
			typeManagerStatic.when(TypeManager::getInstance).thenReturn(typeManager);

			mockCronJobAttributes(cronJob, CRON_JOB_CODE, cronJobPk, runningCronJobStatus, true, List.of(syncSchedule));
			mockCronJobAttributes(runningCronJob, RUNNING_CRON_JOB_CODE, runningCronJobPk, runningCronJobStatus, true,
					List.of(syncSchedule));

			final String expectedLogMessage = "Cannot perform partial sync when there are items overlapping from other running cron job '"
					+ RUNNING_CRON_JOB_CODE + "' (pk:" + runningCronJobPk + ")";
			doReturn(List.of(runningCronJob)).when(catalogVersionSyncJob).getRelatedItems(any(RelationsInfo.class));

			//when
			final boolean result = catalogVersionSyncJob.canPerform(cronJob);

			//then
			assertFalse(result);
			TestLogListenerAssert.assertThat(testLogListener)
			                     .hasLog()
			                     .withMessageContaining(expectedLogMessage)
			                     .loggedFrom(CatalogVersionSyncJob.class)
			                     .withLogLevel(TestLogListenerAssert.LogLevel.ERROR)
			                     .occurrences(1);
		}
	}

	private void initMocks()
	{
		when(runningCronJobStatus.getCode()).thenReturn(CronJobStatus.RUNNING.getCode());
		lenient().when(syncSchedule.getSrcPK()).thenReturn(productPk);
		lenient().when(typeManager.getComposedType(any(String.class))).thenReturn(type);
		lenient().when(type.getAttributeDescriptorIncludingPrivate(any())).thenReturn(relationDescriptor);
		lenient().when(relationDescriptor.getRelationType()).thenReturn(relationType);
		lenient().when(relationType.getTargetAttributeDescriptor()).thenReturn(relationDescriptor);
	}

	private void mockCronJobAttributes(final CatalogVersionSyncCronJob mockedCronJob, final String code, final PK pk,
	                                   final EnumerationValue status, final boolean isRunning,
	                                   final List originalSyncScheduleList)
	{
		when(mockedCronJob.getCode()).thenReturn(code);
		when(mockedCronJob.getPK()).thenReturn(pk);
		when(mockedCronJob.getStatus()).thenReturn(status);
		when(mockedCronJob.isRunning()).thenReturn(isRunning);
		when(mockedCronJob.getOriginalSyncScheduleList()).thenReturn(originalSyncScheduleList);
	}
}
