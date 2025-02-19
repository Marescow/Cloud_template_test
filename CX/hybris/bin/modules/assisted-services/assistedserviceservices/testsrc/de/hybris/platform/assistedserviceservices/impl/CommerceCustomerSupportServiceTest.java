/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedserviceservices.impl;

import de.hybris.bootstrap.annotations.UnitTest;

import de.hybris.platform.assistedserviceservices.AssistedServiceService;
import de.hybris.platform.assistedserviceservices.utils.AssistedServiceSession;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.easymock.EasyMock.eq;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class CommerceCustomerSupportServiceTest
{
    @InjectMocks
    private DefaultCommerceCustomerSupportService service;

    @Mock
    private AssistedServiceService assistedServiceService;

    @Mock
    private UserService userService;

    @Mock
    private SessionService sessionService;

    @Mock
    private Session session;

    private CustomerModel customer;

    private EmployeeModel agent;

    private static final String USER_ID = "User 1";
    private static final String AGENT_ID = "Agent 1";


    @Before
    public void setup()
    {
        when(sessionService.getCurrentSession()).thenReturn(session);
        when(session.getAttribute(eq(DefaultAssistedServiceService.ACTING_USER_UID))).thenReturn(USER_ID);
        customer = new CustomerModel();
        agent = new EmployeeModel();
        customer.setUid(USER_ID);
        agent.setUid(AGENT_ID);
        when(userService.getCurrentUser()).thenReturn(customer);
        when(userService.getUserForUID(eq(AGENT_ID))).thenReturn(agent);
    }

    
    @Test
    public void shouldReturnAgentAndCustomerForCurrentSessionForStorefront()
    {
        final AssistedServiceSession serviceSessionMock = Mockito.mock(AssistedServiceSession.class);
        final CustomerModel customerModel = Mockito.mock(CustomerModel.class);
        final EmployeeModel employeeModel = Mockito.mock(EmployeeModel.class);

        when(serviceSessionMock.getAgent()).thenReturn(employeeModel);
        Assert.assertEquals(null, service.getAgent());
        Assert.assertEquals(null, service.getEmulatedCustomer());
        Assert.assertFalse( service.isCustomerSupportAgentActive());
        when(assistedServiceService.getAsmSession()).thenReturn(serviceSessionMock);
        Assert.assertTrue( service.isCustomerSupportAgentActive());
        when(serviceSessionMock.getEmulatedCustomer()).thenReturn(customerModel);

        Assert.assertEquals(customerModel, service.getEmulatedCustomer());
        Assert.assertEquals(employeeModel, service.getAgent());
    }

    @Test
    public void shouldReturnAgentAndCustomerForCurrentSessionForOCCASMMode()
    {
        final AssistedServiceSession serviceSessionMock = Mockito.mock(AssistedServiceSession.class);
        // no asm session
        when(assistedServiceService.getAsmSession()).thenReturn(null);
        when(session.getAttribute(eq(DefaultAssistedServiceService.ACTING_USER_UID))).thenReturn(AGENT_ID);

        Assert.assertTrue(service.isCustomerSupportAgentActive());

        Assert.assertEquals(customer, service.getEmulatedCustomer());
        Assert.assertEquals(agent, service.getAgent());
    }
}
