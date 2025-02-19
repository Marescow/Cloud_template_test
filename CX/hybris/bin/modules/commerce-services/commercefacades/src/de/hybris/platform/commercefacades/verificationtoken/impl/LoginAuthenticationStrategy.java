/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken.impl;

import de.hybris.platform.commercefacades.verificationtoken.AuthenticationStrategy;
import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.hybris.platform.webservicescommons.util.YSanitizer.sanitize;


/**
 * Create verification token request authentication strategy for login scenario
 */
public class LoginAuthenticationStrategy extends AuthenticationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAuthenticationStrategy.class);

    private UserService userService;

    @Override
    public boolean authenticate(CreateVerificationTokenInputData input) {
        try {
            UserModel userModel = userService.getUserForUID(input.getLoginId());
            String password = input.getPassword();
            return authenticate(userModel, password);
        } catch (UnknownIdentifierException e) {
            LOG.warn("User with loginId [{}] not found", sanitize(input.getLoginId()));
            return false;
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
