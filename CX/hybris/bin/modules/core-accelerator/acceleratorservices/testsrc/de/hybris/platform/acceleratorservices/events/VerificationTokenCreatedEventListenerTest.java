/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.events;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.VerificationTokenCreatedEvent;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@UnitTest
@RunWith(value = MockitoJUnitRunner.class)
public class VerificationTokenCreatedEventListenerTest
{
	@InjectMocks
	private VerificationTokenCreatedEventListener eventListener;

	@Mock
	private ModelService modelService;

	@Mock
	private BusinessProcessService businessProcessService;

	@Before
	public void setUp()
	{
		Map<String, String> sendVerificationTokenProcessMap = new HashMap<>();
		sendVerificationTokenProcessMap.put("LOGIN", "sendLoginVerificationTokenEmailProcess");
		eventListener.setSendVerificationTokenProcessMap(sendVerificationTokenProcessMap);
		given(businessProcessService.createProcess(anyString(),anyString())).willReturn(new SendVerificationTokenProcessModel());
	}

	@Test
	public void testOnEvent()
	{
		VerificationTokenCreatedEvent event = new VerificationTokenCreatedEvent();
		event.setPurpose("LOGIN");
		event.setSite(new BaseSiteModel());
		event.setLanguage(new LanguageModel());
		event.setBaseStore(new BaseStoreModel());
		event.setTarget("lulu.test@sap.com");
		event.setTokenCode("123456");

		eventListener.onEvent(event);

		verify(modelService).save(any(SendVerificationTokenProcessModel.class));
		verify(businessProcessService).startProcess(any(SendVerificationTokenProcessModel.class));
	}

	@Test
	public void testOnProcessNotFound()
	{
		VerificationTokenCreatedEvent event = new VerificationTokenCreatedEvent();
		event.setPurpose("LOGINXXX");
		eventListener.onEvent(event);

		verify(modelService, never()).save(any(SendVerificationTokenProcessModel.class));
		verify(businessProcessService, never()).startProcess(any(SendVerificationTokenProcessModel.class));
	}
}
