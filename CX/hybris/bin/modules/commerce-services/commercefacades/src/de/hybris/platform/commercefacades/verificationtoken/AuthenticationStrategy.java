/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken;

import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Strategy to authenticate create verification token request
 */
public abstract class AuthenticationStrategy {

    private static final String BAD_CREDENTIALS_MESSAGE = "Bad credentials";

    private PasswordEncoderService passwordEncoderService;

    private AuthenticationEventPublisher authenticationEventPublisher;

    /**
     * Authenticate create verification token request
     * @param input
     * @return true if authenticated
     */
    public abstract boolean authenticate(CreateVerificationTokenInputData input);


    /**
     * Authenticate user with password
     * @param userModel
     * @param password
     * @return true if authenticated
     */
    public boolean authenticate(UserModel userModel, String password) {
        boolean authenticated =  !userModel.isLoginDisabled() && passwordEncoderService.isValid(userModel, password);
        if (!authenticated) {
            UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.unauthenticated(userModel.getUid(), password);
            authenticationEventPublisher.publishAuthenticationFailure(new BadCredentialsException(BAD_CREDENTIALS_MESSAGE), authentication);
        }
        return authenticated;
    }

    public void setPasswordEncoderService(PasswordEncoderService passwordEncoderService) {
        this.passwordEncoderService = passwordEncoderService;
    }

    public void setAuthenticationEventPublisher(AuthenticationEventPublisher authenticationEventPublisher) {
        this.authenticationEventPublisher = authenticationEventPublisher;
    }
}
