/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationEventPublisher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class LoginAuthenticationStrategyTest {

    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoderService passwordEncoderService;
    @Mock
    private AuthenticationEventPublisher authenticationEventPublisher;
    @InjectMocks
    private LoginAuthenticationStrategy loginAuthenticationStrategy;

    @Test
    public void shouldReturnTrueWhenAuthenticate() {
        // given
        UserModel userModel = mock(UserModel.class);
        CreateVerificationTokenInputData input = createInputData();
        when(userService.getUserForUID(input.getLoginId())).thenReturn(userModel);
        when(passwordEncoderService.isValid(userModel, input.getPassword())).thenReturn(true);
        // when
        boolean result = loginAuthenticationStrategy.authenticate(input);
        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenUserNotFound() {
        // given
        CreateVerificationTokenInputData input = createInputData();
        when(userService.getUserForUID(input.getLoginId())).thenThrow(UnknownIdentifierException.class);
        // when
        boolean result = loginAuthenticationStrategy.authenticate(input);
        // then
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenUserIsDisabled() {
        // given
        UserModel userModel = mock(UserModel.class);
        when(userModel.isLoginDisabled()).thenReturn(true);
        CreateVerificationTokenInputData input = createInputData();
        when(userService.getUserForUID(input.getLoginId())).thenReturn(userModel);

        // when
        boolean result = loginAuthenticationStrategy.authenticate(input);
        // then
        assertFalse(result);
        verify(authenticationEventPublisher).publishAuthenticationFailure(any(), any());
    }

    @Test
    public void shouldReturnFalseWhenPasswordIsIncorrect() {
        // given
        UserModel userModel = mock(UserModel.class);
        when(userModel.isLoginDisabled()).thenReturn(false);
        CreateVerificationTokenInputData input = createInputData();
        when(userService.getUserForUID(input.getLoginId())).thenReturn(userModel);
        when(passwordEncoderService.isValid(userModel, input.getPassword())).thenReturn(false);

        // when
        boolean result = loginAuthenticationStrategy.authenticate(input);
        // then
        assertFalse(result);
        verify(authenticationEventPublisher).publishAuthenticationFailure(any(), any());
    }

    private CreateVerificationTokenInputData createInputData() {
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setPurpose("LOGIN");
        input.setLoginId("test@sap.com");
        input.setPassword("password");
        return input;
    }


}
