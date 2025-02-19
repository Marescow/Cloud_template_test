package de.hybris.platform.task.impl.gateways;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.MessageFormatUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;


@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class OracleTasksQueueGatewayUnitTest
{
	private static final String PROPERTY_DISABLE_PARALLEL_DML = "task.oracle.parallelism.disableParallelDML";
	private static final String INSERT_TASK_ROW = "INSERT INTO {0} (PK, RANGE_VALUE, NODE_ID, NODE_GROUP, EXECUTION_TIME) SELECT * FROM ({1}) A WHERE NOT EXISTS (SELECT 1 FROM {0}  B WHERE A.PK = B.PK)";
	private static final String INSERT_TASK_ROW_WITH_DISABLE_PARALLEL_DML_HINT = "INSERT /*+ DISABLE_PARALLEL_DML */ INTO {0} (PK, RANGE_VALUE, NODE_ID, NODE_GROUP, EXECUTION_TIME) SELECT * FROM ({1}) A WHERE NOT EXISTS (SELECT 1 FROM {0}  B WHERE A.PK = B.PK)";

	private static final String SUBQUERY = "subquery";
	private static final String TABLE = "tasks_aux_queue";
	private static final String TENANT_ID = "123456789";

	private OracleTasksQueueGateway oracleTasksQueueGateway;

	@Mock
	private JdbcTemplate jdbcTemplate;
	@Mock
	private TypeService typeService;
	@Mock
	private Tenant tenant;

	private MockedStatic<MessageFormatUtils> messageFormatUtilsMockedStatic;
	private MockedStatic<Config> configMockedStatic;
	private MockedStatic<Registry> registryMockedStatic;

	@Before
	public void setUp() throws Exception
	{
		oracleTasksQueueGateway = new OracleTasksQueueGateway(jdbcTemplate, typeService);
		messageFormatUtilsMockedStatic = mockStatic(MessageFormatUtils.class);
		configMockedStatic = mockStatic(Config.class);
		registryMockedStatic = mockStatic(Registry.class);
	}

	@After
	public void cleanUp()
	{
		messageFormatUtilsMockedStatic.close();
		configMockedStatic.close();
		registryMockedStatic.close();
	}

	@Test
	public void shouldReturnQueryWithHintWhenDisableParallelDMLIsTrue()
	{
		//given
		configMockedStatic.when(() -> Config.getBoolean(PROPERTY_DISABLE_PARALLEL_DML, false)).thenReturn(true);
		registryMockedStatic.when(Registry::getCurrentTenant).thenReturn(tenant);
		given(tenant.getTenantID()).willReturn(TENANT_ID);
		given(oracleTasksQueueGateway.getTableName()).willReturn(TABLE);
		messageFormatUtilsMockedStatic.when(
						() -> MessageFormatUtils.format(eq(INSERT_TASK_ROW_WITH_DISABLE_PARALLEL_DML_HINT), anyString(), eq(SUBQUERY)))
				.thenReturn(INSERT_TASK_ROW_WITH_DISABLE_PARALLEL_DML_HINT);

		//when
		final String result = oracleTasksQueueGateway.getInsertTaskRowStatement(SUBQUERY);

		//then
		assertEquals(INSERT_TASK_ROW_WITH_DISABLE_PARALLEL_DML_HINT, result);
	}

	@Test
	public void shouldReturnQueryWithoutHintWhenDisableParallelDMLIsFalse()
	{
		//given
		configMockedStatic.when(() -> Config.getBoolean(PROPERTY_DISABLE_PARALLEL_DML, false)).thenReturn(false);
		registryMockedStatic.when(Registry::getCurrentTenant).thenReturn(tenant);
		given(tenant.getTenantID()).willReturn(TENANT_ID);
		given(oracleTasksQueueGateway.getTableName()).willReturn(TABLE);
		messageFormatUtilsMockedStatic.when(() -> MessageFormatUtils.format(eq(INSERT_TASK_ROW), anyString(), eq(SUBQUERY)))
				.thenReturn(INSERT_TASK_ROW);

		//when
		final String result = oracleTasksQueueGateway.getInsertTaskRowStatement(SUBQUERY);

		//then
		assertEquals(INSERT_TASK_ROW, result);
	}
}
