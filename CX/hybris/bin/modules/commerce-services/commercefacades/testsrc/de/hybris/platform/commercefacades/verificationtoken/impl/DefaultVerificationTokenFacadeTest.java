/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.verificationtoken.AuthenticationStrategy;
import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.core.servicelayer.user.UserVerificationTokenData;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.user.UserVerificationTokenService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultVerificationTokenFacadeTest {
    @Mock
    private UserService userService;
    @Mock
    private EventService eventService;
    @Mock
    private BaseStoreService baseStoreService;
    @Mock
    private BaseSiteService baseSiteService;
    @Mock
    private CommonI18NService commonI18NService;
    @Mock
    private UserVerificationTokenService userVerificationTokenService;
    @Mock
    private LoginAuthenticationStrategy loginAuthenticationStrategy;
    @InjectMocks
    private DefaultVerificationTokenFacade defaultVerificationTokenFacade;
    private Map<String, AuthenticationStrategy> strategyMap;

    @Before
    public void setUp() {
        strategyMap = new HashMap<>();
        strategyMap.put("LOGIN", loginAuthenticationStrategy);
        defaultVerificationTokenFacade.setStrategyMap(strategyMap);

        UserVerificationTokenData data = new UserVerificationTokenData();
        data.setTokenCode("mockedTokenCode");
        data.setTokenId("mockedTokenId");
        when(userVerificationTokenService.create(eq(SAPUserVerificationPurpose.LOGIN), any())).thenReturn(data);
        when(userVerificationTokenService.getFakeTokenId(SAPUserVerificationPurpose.LOGIN)).thenReturn("fakeTokenId");
    }

    @Test
    public void shouldReturnTrueWhenIsAuthenticated() {
        // given
        CreateVerificationTokenInputData input = createInputData();
        // when
        when(loginAuthenticationStrategy.authenticate(input)).thenReturn(true);
        boolean result = defaultVerificationTokenFacade.isAuthenticated(input);
        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenNotAuthenticated() {
        // given
        CreateVerificationTokenInputData input = createInputData();
        // when
        when(loginAuthenticationStrategy.authenticate(input)).thenReturn(false);
        boolean result = defaultVerificationTokenFacade.isAuthenticated(input);
        // then
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenNoStrategyConfigured() {
        // given
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setPurpose("NONE_EXISTS_PURPOSE");
        // when
        boolean result = defaultVerificationTokenFacade.isAuthenticated(input);
        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFakeTokenIdWhenNotAuthenticated() {
        // given
        CreateVerificationTokenInputData input = createInputData();
        // when
        when(loginAuthenticationStrategy.authenticate(input)).thenReturn(false);
        String result = defaultVerificationTokenFacade.createVerificationToken(input);
        // then
        assertEquals("fakeTokenId", result);
    }

    @Test
    public void shouldReturnTokenIdWhenUserIsAuthenticated() {
        // given
        CreateVerificationTokenInputData input = createInputData();
        CustomerModel customerModel = mock(CustomerModel.class);
        when(customerModel.getUid()).thenReturn("test@sap.com|siteId");
        when(customerModel.getOriginalUid()).thenReturn("test@sap.com");
        // when
        when(loginAuthenticationStrategy.authenticate(input)).thenReturn(true);
        when(userService.getUserForUID("test@sap.com")).thenReturn(customerModel);
        String result = defaultVerificationTokenFacade.createVerificationToken(input);
        // then
        verify(userVerificationTokenService).create(SAPUserVerificationPurpose.LOGIN, "test@sap.com|siteId");
        verify(eventService).publishEvent(any());
        assertEquals("mockedTokenId", result);
    }

    @Test
    public void shouldReturnTokenIdWhenUserIsNotCustomer() {
        // given
        CreateVerificationTokenInputData input = createInputData();
        UserModel userModel = mock(UserModel.class);
        when(userModel.getUid()).thenReturn("test@sap.com|siteId");
        // when
        when(loginAuthenticationStrategy.authenticate(input)).thenReturn(true);
        when(userService.getUserForUID("test@sap.com")).thenReturn(userModel);
        String result = defaultVerificationTokenFacade.createVerificationToken(input);
        // then
        verify(userVerificationTokenService).create(SAPUserVerificationPurpose.LOGIN, "test@sap.com|siteId");
        verify(eventService).publishEvent(any());
        assertEquals("mockedTokenId", result);
    }
    private CreateVerificationTokenInputData createInputData() {
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setPurpose("LOGIN");
        input.setLoginId("test@sap.com");
        input.setPassword("password");
        return input;
    }


}
