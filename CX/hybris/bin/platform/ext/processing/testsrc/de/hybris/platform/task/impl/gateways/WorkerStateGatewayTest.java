/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.task.impl.gateways;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.task.impl.AuxiliaryTablesGatewayFactory;
import de.hybris.platform.task.impl.gateways.WorkerStateGateway.WorkerState;
import de.hybris.platform.util.Config;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import org.assertj.core.groups.Tuple;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class WorkerStateGatewayTest extends BaseGatewayTest
{
	@Resource
	private AuxiliaryTablesGatewayFactory auxiliaryTablesGatewayFactory;


	private WorkerStateGateway gateway;

	@Before
	public void setUp() throws Exception
	{
		disableTaskEngine();

		gateway = auxiliaryTablesGatewayFactory.getWorkerStateGateway();

		prepareTable(gateway);
	}

	@After
	public void tearDown() throws Exception
	{
		cleanUpTable(gateway);

		enableTaskEngine();
	}


	@Test
	public void registerAsWorker()
	{
		gateway.registerAsWorker(new WorkerState(0, null, false, null));
	}

	@Test
	public void getInactiveWorkersHealthChecks()
	{
		gateway.getWorkersHealthChecks();
	}

	@Test
	public void getWorkerRangeById()
	{
		gateway.getWorkerRangeById(0);
	}

	@Test
	public void getWorkers()
	{
		final Set<String> nodeGroups = Collections.singleton("testing");
		gateway.registerAsWorker(new WorkerState(0, null, false, nodeGroups));
		final List<WorkerState> workers = gateway.getWorkers();

		assertThat(workers).hasSize(1).extracting(WorkerState::getNodeId, WorkerState::getNodeGroups)
		                   .containsExactly(Tuple.tuple(0, nodeGroups));
	}

	@Test
	public void updateWorkersRanges()
	{
		gateway.updateWorkersRanges(Maps.newHashMap(0, new WorkerStateGateway.WorkerRange(0, 100)));
	}

	@Test
	public void deleteWorkers()
	{
		gateway.deleteWorkers(Lists.newArrayList(0, 1));
	}

	@Test
	public void shouldReturnTrueIfTableExists()
	{
		assertTableExists(gateway.getTableName());

		final boolean r = gateway.doesTableExist();

		assertThat(r).isTrue();
	}

	@Test
	public void shouldReturnFalseIfTableDoesNotExist()
	{
		gateway.dropTable();
		assertTableNotExists(gateway.getTableName());

		final boolean r = gateway.doesTableExist();

		assertThat(r).isFalse();
	}

	@Test
	public void shouldNotAutomaticallyUpdateTimestampColumnWhenRangeIsUpdated()
	{
		Assume.assumeTrue("Specific to MySQL DB only", Config.isMySQLUsed());
		final var workerState = new WorkerState(1, null, false, null);

		// given
		gateway.registerAsWorker(workerState);
		final var timestamp_before = getHealthCheckForWorker(workerState);

		// when
		awaitInMillis(500);
		gateway.updateWorkersRanges(Maps.newHashMap(workerState.getNodeId(), new WorkerStateGateway.WorkerRange(0, 100)));
		final var timestamp_after = getHealthCheckForWorker(workerState);

		// then
		assertThat(timestamp_after).isEqualTo(timestamp_before);
	}

	private Timestamp getHealthCheckForWorker(final WorkerState workerState) {
		final String SELECT_WORKER_HEALTH_CHECK = String.format("SELECT HEALTH_CHECK FROM %s WHERE ID = %d", gateway.getTableName(), workerState.getNodeId());
		return jdbcTemplate.queryForObject(SELECT_WORKER_HEALTH_CHECK, Timestamp.class);
	}

	private static void awaitInMillis(final int millisecondsToWait)
	{
		Awaitility.await().pollDelay(millisecondsToWait, TimeUnit.MILLISECONDS).until(() -> true);
	}

}
