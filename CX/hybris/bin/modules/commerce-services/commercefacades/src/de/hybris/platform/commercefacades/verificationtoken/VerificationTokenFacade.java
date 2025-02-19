/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken;

import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;

/**
 * Facade for verification token management
 */
public interface VerificationTokenFacade {

    /**
     * Authenticate create verification token request
     * @param input data input for creating a verification token
     * @return true if request is successfully authenticated
     */
    boolean isAuthenticated(CreateVerificationTokenInputData input);

    /**
     * Authenticate create verification token request, then create verification token and publish token created event.
     * When request authentication failed, a dummy verification token will be created, and no event published.
     * When request authentication passed, a verification token will be created, publish token create event.
     * @param input data input for creating an verification token
     * @return tokenId
     */
    String createVerificationToken(CreateVerificationTokenInputData input);

}
