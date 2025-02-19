/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.servicelayer.event.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.AbstractTenant;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import java.util.concurrent.Executor;

import org.junit.Test;
import org.mockito.Mockito;

@IntegrationTest
public class PlatformClusterEventSenderTest extends ServicelayerBaseTest
{

	@Test
	public void shouldSendViaThreadExecutorWhenTenantIsStarted()
	{
		//given
		final PlatformClusterEventSender sender = spy(new PlatformClusterEventSender());
		final AbstractTenant tenantMock = mock(AbstractTenant.class);
		final AbstractEvent customEvent = createCustomEvent();
		mockSenderMethods(tenantMock, sender, mock(Executor.class));
		sender.afterTenantStartUp(tenantMock);

		//when
		sender.sendEvent(customEvent);

		//then
		verify(sender, never()).sendEventViaSpring(any());
		verify(sender, times(1)).trySendEventViaThreadExecutor(any());
	}

	@Test
	public void shouldSendViaSpringWhenTenantIsNotStarted()
	{

		//given
		final PlatformClusterEventSender sender = spy(new PlatformClusterEventSender());
		final AbstractTenant tenantMock = mock(AbstractTenant.class);
		final AbstractEvent customEvent = createCustomEvent();
		mockSenderMethods(tenantMock, sender, mock(Executor.class));

		//when
		sender.sendEvent(customEvent);

		//then
		verify(sender, times(1)).trySendEventViaThreadExecutor(any());
		verify(sender, times(1)).sendEventViaSpring(any());
	}

	@Test
	public void shouldSendFirstViaSpringWhenTenantIsNotStartedAndThenViaSpringIfIsStarted()
	{

		//given
		final PlatformClusterEventSender sender = spy(new PlatformClusterEventSender());
		final AbstractTenant tenantMock = mock(AbstractTenant.class);
		final AbstractEvent customEvent = createCustomEvent();
		mockSenderMethods(tenantMock, sender, mock(Executor.class));
		//when
		sender.sendEvent(customEvent);

		//then
		verify(sender, times(1)).trySendEventViaThreadExecutor(any());
		verify(sender, times(1)).sendEventViaSpring(any());

		//given
		sender.afterTenantStartUp(tenantMock);
		//when
		sender.sendEvent(customEvent);

		//when
		verify(sender, times(2)).trySendEventViaThreadExecutor(any());
		verify(sender, times(1)).sendEventViaSpring(any());
	}

	@Test
	public void shouldSendFirstViaThreadWhenTenantIsStartedAndThenViaSpringIfIsClosed()
	{

		//given
		final PlatformClusterEventSender sender = spy(new PlatformClusterEventSender());
		final AbstractTenant tenantMock = mock(AbstractTenant.class);
		final AbstractEvent customEvent = createCustomEvent();
		mockSenderMethods(tenantMock, sender, mock(Executor.class));
		sender.afterTenantStartUp(tenantMock);
		//when
		sender.sendEvent(customEvent);

		//then
		verify(sender, times(1)).trySendEventViaThreadExecutor(any());
		verify(sender, times(0)).sendEventViaSpring(any());

		//given
		sender.beforeTenantShutDown(tenantMock);
		//when
		sender.sendEvent(customEvent);

		//when
		verify(sender, times(2)).trySendEventViaThreadExecutor(any());
		verify(sender, times(1)).sendEventViaSpring(any());
	}

	@Test
	public void shouldSendViaSpringWhenThreadExecutorIsNotSet()
	{

		//given
		final PlatformClusterEventSender sender = spy(new PlatformClusterEventSender());
		final AbstractTenant tenantMock = mock(AbstractTenant.class);
		final AbstractEvent customEvent = createCustomEvent();
		mockSenderMethods(tenantMock, sender, null);

		sender.afterTenantStartUp(tenantMock);
		//when
		sender.sendEvent(customEvent);

		//then
		verify(sender, times(1)).trySendEventViaThreadExecutor(any());
		verify(sender, times(1)).sendEventViaSpring(any());
	}

	private static void mockSenderMethods(final AbstractTenant tenantMock, final PlatformClusterEventSender sender, final Executor mock)
	{
		Mockito.doReturn(tenantMock).when(sender).getCurrentTenant();
		Mockito.doReturn(AbstractTenant.State.STARTED).when(tenantMock).getState();
		Mockito.doReturn(mock).when(sender).getExecutor();
		Mockito.doNothing().when(sender).sendEventViaSpring(any());
	}

	private AbstractEvent createCustomEvent()
	{
		return new AbstractEvent()
		{
		};
	}
}
