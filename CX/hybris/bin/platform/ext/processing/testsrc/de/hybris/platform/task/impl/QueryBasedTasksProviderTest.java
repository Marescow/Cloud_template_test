/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.task.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.AbstractTenant;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearchDataSourceTest;
import de.hybris.platform.jalo.flexiblesearch.internal.FlexibleSearchExecutor;
import de.hybris.platform.jalo.flexiblesearch.internal.ReadOnlyConditionsHelper;
import de.hybris.platform.jdbcwrapper.HybrisDataSource;
import de.hybris.platform.persistence.flexiblesearch.TranslatedQuery;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.impl.DefaultFlexibleSearchService;
import de.hybris.platform.servicelayer.search.internal.preprocessor.QueryPreprocessorRegistry;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.testframework.PropertyConfigSwitcher;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.verification.VerificationMode;
import org.springframework.test.util.ReflectionTestUtils;

import com.codahale.metrics.MetricRegistry;

@IntegrationTest
public class QueryBasedTasksProviderTest extends ServicelayerBaseTest
{

	private static final String READ_ONLY_DATASOURCE_ID = "f";
	private static final int MAX_ITEMS_TO_SCHEDULE = 3;
	private static final int NUMBER_OF_INVOCATION = 5;
	private final PropertyConfigSwitcher flexibleSearchReadOnlyDataSource = new PropertyConfigSwitcher(
			ReadOnlyConditionsHelper.PARAM_FS_READ_ONLY_DATASOURCE);
	private final PropertyConfigSwitcher readReplicaEnabledOnTaskSwitcher =
			new PropertyConfigSwitcher(QueryBasedTasksProvider.PROPERTY_USE_READ_REPLICA);

	private final PropertyConfigSwitcher fullQueryIntervalSwitcher =
			new PropertyConfigSwitcher(QueryBasedTasksProvider.PROPERTY_FULL_QUERY_INTERVAL);
	private final PropertyConfigSwitcher queueSizeReportingIntervalSwitcher =
			new PropertyConfigSwitcher(QueryBasedTasksProvider.PROPERTY_QUEUE_SIZE_REPORTING_INTERVAL);
	protected AbstractTenant tenant;

	@Resource
	private MetricRegistry metricRegistry;

	@Resource
	private TypeService typeService;

	@Resource
	private SessionService sessionService;

	@Resource
	private ModelService modelService;

	@Resource
	private QueryPreprocessorRegistry queryPreprocessorRegistry;

	private FlexibleSearchExecutor flexibleSearchExecutor;
	private FlexibleSearch flexibleSearch;
	private FlexibleSearchService flexibleSearchService;

	private RuntimeConfigHolder runtimeConfigHolder;

	private TaskEngineParameters taskEngineParameters;

	private QueryBasedTasksProvider queryBasedTasksProvider;

	@Before
	public void setUp() throws Exception
	{
		tenant = Registry.getCurrentTenant();

		flexibleSearchReadOnlyDataSource.switchToValue(READ_ONLY_DATASOURCE_ID);

		this.flexibleSearchExecutor = spy(new FlexibleSearchExecutor(tenant));
		flexibleSearch = new FlexibleSearchDataSourceTest.TestFlexibleSearch(flexibleSearchExecutor);
		flexibleSearchService = new TestFlexibleSearchService(flexibleSearch, sessionService, modelService,
				queryPreprocessorRegistry);

		runtimeConfigHolder = new RuntimeConfigHolder();

		taskEngineParameters = new TaskEngineParameters.ParametersBuilder().withTenant(tenant)
		                                                                   .withClusterGroupsForThisNode(List.of())
		                                                                   .withMaxThreads(1)
		                                                                   .build();

		queryBasedTasksProvider = new QueryBasedTasksProvider(metricRegistry, flexibleSearchService, typeService);
	}

	@Test
	public void getTasksToScheduleWhenReadReplicaDisabled()
	{
		//given
		final HybrisDataSource expectedDs = tenant.getDataSource();

		final HybrisDataSource otherDs = tenant.getAllSlaveDataSources()
		                                       .stream()
		                                       .filter(ds -> READ_ONLY_DATASOURCE_ID.equals(ds.getID()))
		                                       .findFirst()
		                                       .orElseThrow();

		readReplicaEnabledOnTaskSwitcher.switchToValue("false");

		//when
		queryBasedTasksProvider.getTasksToSchedule(runtimeConfigHolder, taskEngineParameters, 3);

		//then
		verifyDataSourceWasUsedOnExecute(expectedDs, 5);
		verifyDataSourcesWereNotUsedOnExecute(otherDs);
	}

	@Test
	public void getTasksToScheduleWhenReadReplicaEnabled()
	{
		//given
		final HybrisDataSource expectedDs = tenant.getAllSlaveDataSources()
		                                          .stream()
		                                          .filter(ds -> READ_ONLY_DATASOURCE_ID.equals(ds.getID()))
		                                          .findFirst()
		                                          .orElseThrow();

		final HybrisDataSource otherDs = tenant.getDataSource();


		readReplicaEnabledOnTaskSwitcher.switchToValue("true");

		//when
		queryBasedTasksProvider.getTasksToSchedule(runtimeConfigHolder, taskEngineParameters, 3);

		//then
		verifyDataSourceWasUsedOnExecute(expectedDs, 5);
		verifyDataSourcesWereNotUsedOnExecute(otherDs);
	}

	@Test
	public void shouldPerformFullQueryEveryTimeWhenIntervalIsZero()
	{
		testFullQueryEveryTime("0");
	}

	private void executeGetTasksToScheduleMultipleTimes(final int x)
	{
		for (int i = 0; i < x; i++)
		{
			queryBasedTasksProvider.getTasksToSchedule(this.runtimeConfigHolder, taskEngineParameters, MAX_ITEMS_TO_SCHEDULE);
		}
	}

	@Test
	public void shouldPerformFullQueryEveryTimeWhenIntervalIsNegative()
	{
		testFullQueryEveryTime("-1");
	}

	@Test
	public void shouldPerformFullQueryEveryTimeWhenIntervalIsEmpty()
	{
		testFullQueryEveryTime("");
	}

	private void testFullQueryEveryTime(final String fullQueryIntervalValue)
	{
		final DefaultTaskServiceQueryProvider taskServiceQueryProvider = spy(new DefaultTaskServiceQueryProvider());
		ReflectionTestUtils.setField(queryBasedTasksProvider, "taskServiceQueryProvider", taskServiceQueryProvider);

		fullQueryIntervalSwitcher.switchToValue(fullQueryIntervalValue);
		queueSizeReportingIntervalSwitcher.switchToValue("-1"); //disable queue size reporting to not interfere with the test

		executeGetTasksToScheduleMultipleTimes(3);

		verify(taskServiceQueryProvider, times(3)).getValidTasksToExecuteQuery(anyList(), anyBoolean(), anyBoolean(), eq(true));
	}

	@Test
	public void shouldPerformFullQueryEveryTimeWhenIntervalIsInvalid()
	{
		testFullQueryEveryTime("NotANumber");
	}


	@Test
	public void shouldNotPerformFullQueryWhenIntervalIsTooHigh()
	{
		final DefaultTaskServiceQueryProvider taskServiceQueryProvider = spy(new DefaultTaskServiceQueryProvider());
		ReflectionTestUtils.setField(queryBasedTasksProvider, "taskServiceQueryProvider", taskServiceQueryProvider);

		fullQueryIntervalSwitcher.switchToValue("4");
		queueSizeReportingIntervalSwitcher.switchToValue("-1"); //disable queue size reporting to not interfere with the test

		executeGetTasksToScheduleMultipleTimes(3);


		final ArgumentCaptor<Boolean> shouldRunFullQueryCaptor = ArgumentCaptor.forClass(Boolean.class);

		verify(taskServiceQueryProvider, times(3)).getValidTasksToExecuteQuery(anyList(), anyBoolean(), anyBoolean(),
				shouldRunFullQueryCaptor.capture());
		assertThat(shouldRunFullQueryCaptor.getAllValues()).containsExactly(false, false, false);
	}

	@Test
	public void shouldPerformFullQueryForNthCallWhenIntervalIsSet()
	{
		final DefaultTaskServiceQueryProvider taskServiceQueryProvider = spy(new DefaultTaskServiceQueryProvider());
		ReflectionTestUtils.setField(queryBasedTasksProvider, "taskServiceQueryProvider", taskServiceQueryProvider);

		fullQueryIntervalSwitcher.switchToValue("3");
		queueSizeReportingIntervalSwitcher.switchToValue("-1"); //disable queue size reporting to not interfere with the test

		executeGetTasksToScheduleMultipleTimes(4);


		final ArgumentCaptor<Boolean> shouldRunFullQueryCaptor = ArgumentCaptor.forClass(Boolean.class);
		verify(taskServiceQueryProvider, times(4)).getValidTasksToExecuteQuery(anyList(), anyBoolean(), anyBoolean(),
				shouldRunFullQueryCaptor.capture());
		assertThat(shouldRunFullQueryCaptor.getAllValues()).containsExactly(false, false, true, false);
	}

	@Test
	public void shouldPerformFullQueryEveryNthCallWhenIntervalIsSet()
	{
		final DefaultTaskServiceQueryProvider taskServiceQueryProvider = spy(new DefaultTaskServiceQueryProvider());
		ReflectionTestUtils.setField(queryBasedTasksProvider, "taskServiceQueryProvider", taskServiceQueryProvider);

		fullQueryIntervalSwitcher.switchToValue("3");
		queueSizeReportingIntervalSwitcher.switchToValue("-1"); //disable queue size reporting to not interfere with the test

		executeGetTasksToScheduleMultipleTimes(7);

		final ArgumentCaptor<Boolean> shouldRunFullQueryCaptor = ArgumentCaptor.forClass(Boolean.class);

		verify(taskServiceQueryProvider, times(7)).getValidTasksToExecuteQuery(anyList(), anyBoolean(), anyBoolean(),
				shouldRunFullQueryCaptor.capture());
		assertThat(shouldRunFullQueryCaptor.getAllValues()).containsExactly(false, false, true, false, false, true, false);
	}

	@After
	public void tearDown()
	{
		readReplicaEnabledOnTaskSwitcher.switchBackToDefault();
		flexibleSearchReadOnlyDataSource.switchBackToDefault();
		fullQueryIntervalSwitcher.switchBackToDefault();
		queueSizeReportingIntervalSwitcher.switchBackToDefault();
	}

	private void verifyDataSourceWasUsedOnExecute(final HybrisDataSource expectedDS,
	                                              final int times)
	{
		verifyDataSourceWasUsedOnExecute(expectedDS, times(times));
	}

	private void verifyDataSourceWasUsedOnExecute(
			final HybrisDataSource expectedDS, final VerificationMode verificationMode)
	{
		verify(flexibleSearchExecutor, verificationMode)
				.execute(anyInt(), anyInt(), anyBoolean(), any(TranslatedQuery.class), anyList(), anyMap(),
						any(PK.class), anyInt(), anySet(), anyList(), eq(expectedDS));
	}

	private void verifyDataSourcesWereNotUsedOnExecute(final HybrisDataSource... notExpectedDS)
	{

		final ArgumentCaptor<DataSource> dataSourceArgumentCaptor = ArgumentCaptor.forClass(DataSource.class);

		verify(flexibleSearchExecutor, atLeastOnce())
				.execute(anyInt(), anyInt(), anyBoolean(), any(TranslatedQuery.class), anyList(), anyMap(),
						any(PK.class), anyInt(), anySet(), anyList(), dataSourceArgumentCaptor.capture());

		assertThat(dataSourceArgumentCaptor.getAllValues()).doesNotContain(notExpectedDS);
	}

	public static class TestFlexibleSearchService extends DefaultFlexibleSearchService
	{
		private final FlexibleSearch flexibleSearch;
		private final SessionService sessionService;
		private final ModelService modelService;
		private final QueryPreprocessorRegistry queryPreprocessorRegistry;

		public TestFlexibleSearchService(final FlexibleSearch flexibleSearch,
		                                 final SessionService sessionService,
		                                 final ModelService modelService,
		                                 final QueryPreprocessorRegistry queryPreprocessorRegistry)
		{
			this.flexibleSearch = flexibleSearch;
			this.sessionService = sessionService;
			this.modelService = modelService;
			this.queryPreprocessorRegistry = queryPreprocessorRegistry;
		}


		@Override
		protected FlexibleSearch getFlexibleSearchInstance()
		{
			return flexibleSearch;
		}

		@Override
		protected SessionService getSessionService()
		{
			return sessionService;
		}

		@Override
		protected ModelService getModelService()
		{
			return modelService;
		}

		@Override
		public QueryPreprocessorRegistry lookupQueryPreprocessorRegistry()
		{
			return queryPreprocessorRegistry;
		}
	}
}
