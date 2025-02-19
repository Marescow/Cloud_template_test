package de.hybris.platform.warehousing.cancellation.strategy.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.OrderCancelState;
import de.hybris.platform.consignmenttrackingservices.daos.ConsignmentDao;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import junit.framework.TestCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Unit test for {@link WarehousingOrderCancelStateMappingStrategy} class
 */
@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class WarehousingOrderCancelStateMappingStrategyTest extends TestCase
{
	@InjectMocks
	private WarehousingOrderCancelStateMappingStrategy strategy;
	@Mock
	private ConsignmentDao consignmentDao;

	@Before
	public void setup()
	{
		strategy.setConfirmedConsignmentStatus(List.of(ConsignmentStatus.SHIPPED, ConsignmentStatus.PICKUP_COMPLETE));
	}

	@Test
	public void shouldReturnCancelImpossibleWhenOrderStatusIsCancelled() {
		OrderModel order = new OrderModel();
		order.setStatus(OrderStatus.CANCELLED);
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.CANCELIMPOSSIBLE);
	}

	@Test
	public void shouldReturnCancelImpossibleWhenOrderStatusIsCompleted() {
		OrderModel order = new OrderModel();
		order.setStatus(OrderStatus.COMPLETED);
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.CANCELIMPOSSIBLE);
	}

	@Test
	public void shouldReturnPendingOrHoldingAreaWhenConsignmentIsNull() {
		OrderModel order = new OrderModel();
		order.setStatus(OrderStatus.READY);
		order.setCode("orderCode");
		when(consignmentDao.findConsignmentsByOrder(any(String.class))).thenReturn(Collections.emptyList());
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.PENDINGORHOLDINGAREA);
	}

	@Test
	public void shouldReturnSentToWarehouseWhenConsignmentStatusIsREADY() {
		OrderModel order = new OrderModel();
		order.setStatus(OrderStatus.READY);
		order.setCode("orderCode");
		ConsignmentModel consignment = new ConsignmentModel();
		consignment.setStatus(ConsignmentStatus.READY);

		when(consignmentDao.findConsignmentsByOrder(any(String.class))).thenReturn(List.of(consignment));
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.SENTTOWAREHOUSE);
	}

	@Test
	public void shouldReturnCancelImpossibleAfterCheckConsignments() {
		OrderEntryModel orderEntry = mock(OrderEntryModel.class);
		when(orderEntry.getQuantityUnallocated()).thenReturn(0L);
		OrderModel order = new OrderModel();
		order.setEntries(List.of(orderEntry));
		order.setStatus(OrderStatus.READY);
		order.setCode("orderCode");
		ConsignmentModel consignment = new ConsignmentModel();
		consignment.setStatus(ConsignmentStatus.SHIPPED);
		consignment.setOrder(order);
		when(consignmentDao.findConsignmentsByOrder(any(String.class))).thenReturn(List.of(consignment));
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.CANCELIMPOSSIBLE);
	}

	@Test
	public void shouldReturnPartiallyShippedAfterCheckConsignments() {
		OrderEntryModel orderEntry = mock(OrderEntryModel.class);
		when(orderEntry.getQuantityUnallocated()).thenReturn(0L);
		OrderModel order = new OrderModel();
		order.setEntries(List.of(orderEntry));
		order.setStatus(OrderStatus.READY);
		order.setCode("orderCode");
		ConsignmentModel consignment1 = new ConsignmentModel();
		consignment1.setStatus(ConsignmentStatus.SHIPPED);
		consignment1.setOrder(order);
		ConsignmentModel consignment2 = new ConsignmentModel();
		consignment2.setStatus(ConsignmentStatus.READY);
		consignment2.setOrder(order);
		when(consignmentDao.findConsignmentsByOrder(any(String.class))).thenReturn(List.of(consignment1, consignment2));
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.PARTIALLYSHIPPED);
	}

	@Test
	public void shouldReturnSentToWarehouseAfterCheckConsignments() {
		OrderEntryModel orderEntry = mock(OrderEntryModel.class);
		OrderModel order = new OrderModel();
		order.setEntries(List.of(orderEntry));
		order.setStatus(OrderStatus.READY);
		order.setCode("orderCode");
		ConsignmentModel consignment = new ConsignmentModel();
		consignment.setStatus(ConsignmentStatus.READY);
		when(consignmentDao.findConsignmentsByOrder(any(String.class))).thenReturn(List.of(consignment));
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.SENTTOWAREHOUSE);
	}

	@Test
	public void shouldReturnShippingAfterCheckConsignments() {
		OrderEntryModel orderEntry = mock(OrderEntryModel.class);
		when(orderEntry.getQuantityUnallocated()).thenReturn(0L);
		OrderModel order = new OrderModel();
		order.setEntries(List.of(orderEntry));
		order.setStatus(OrderStatus.READY);
		order.setCode("orderCode");
		ConsignmentModel consignment = new ConsignmentModel();
		consignment.setStatus(ConsignmentStatus.PICKPACK);
		consignment.setOrder(order);
		when(consignmentDao.findConsignmentsByOrder(any(String.class))).thenReturn(List.of(consignment));
		assertEquals(strategy.getOrderCancelState(order), OrderCancelState.SHIPPING);
	}
}
