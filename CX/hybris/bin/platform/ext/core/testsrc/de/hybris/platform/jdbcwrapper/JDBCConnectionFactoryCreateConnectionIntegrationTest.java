/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.jdbcwrapper;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.DataSourceFactory;
import de.hybris.platform.core.Registry;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@IntegrationTest
public class JDBCConnectionFactoryCreateConnectionIntegrationTest
{
	private JDBCConnectionFactory spyJdbcConnectionFactory;
	private DataSourceFactory spyDataSourceFactory;
	private HybrisDataSource spyDataSource;

	@Before
	public void setUp() {
		spyDataSource = spy(Registry.getCurrentTenant().getDataSource());
		spyDataSourceFactory = spy(spyDataSource.getDataSourceFactory());
		spyJdbcConnectionFactory = spy(new JDBCConnectionFactory(spyDataSource));
	}

	@Test
	public void test_createConnection_withSQLException_shouldNotInvokeReturnToPoolOnWrappedConnection() throws SQLException
	{
		final Connection connection = spyJdbcConnectionFactory.createConnection();
		final ConnectionImpl wrappedConnection = (ConnectionImpl) spyDataSourceFactory.wrapConnection(spyDataSource, connection);
		final ConnectionImpl spyWrappedConnection = spy(wrappedConnection);

		doReturn(spyDataSourceFactory).when(spyDataSource).getDataSourceFactory();
		doReturn(spyWrappedConnection).when(spyDataSourceFactory).wrapConnection(any(), any());
		doThrow(new SQLException()).when(spyWrappedConnection).isReadOnly();

		assertThrows(SQLException.class, () -> spyJdbcConnectionFactory.makeObject());
		verify(spyDataSource, never()).returnToPool(spyWrappedConnection);
	}

	@After
	public void tearDown() {
		reset(spyDataSource, spyDataSourceFactory, spyJdbcConnectionFactory);
	}
}
