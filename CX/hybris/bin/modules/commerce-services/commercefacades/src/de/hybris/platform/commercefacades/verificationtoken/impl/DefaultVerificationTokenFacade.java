/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken.impl;

import de.hybris.platform.commercefacades.verificationtoken.AuthenticationStrategy;
import de.hybris.platform.commercefacades.verificationtoken.VerificationTokenFacade;
import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;
import de.hybris.platform.commerceservices.event.VerificationTokenCreatedEvent;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.core.servicelayer.user.UserVerificationTokenData;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.user.UserVerificationTokenService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Map;


/**
 * Default implementation of {@link de.hybris.platform.commercefacades.verificationtoken.VerificationTokenFacade}
 */
public class DefaultVerificationTokenFacade implements VerificationTokenFacade
{
    private UserService userService;
    private EventService eventService;
    private BaseStoreService baseStoreService;
    private BaseSiteService baseSiteService;
    private CommonI18NService commonI18NService;
    private UserVerificationTokenService userVerificationTokenService;

    private Map<String, AuthenticationStrategy> strategyMap;

    @Override
    public boolean isAuthenticated(final CreateVerificationTokenInputData input)
    {
        AuthenticationStrategy authenticationStrategy = getAuthenticationStrategyByPurpose(input.getPurpose());

        // authentication is not necessary, no authentication strategy configured for current purpose.
        if (authenticationStrategy == null)
        {
            return true;
        }

        return authenticationStrategy.authenticate(input);
    }

    @Override
    public String createVerificationToken(final CreateVerificationTokenInputData input)
    {
        final SAPUserVerificationPurpose purpose = SAPUserVerificationPurpose.valueOf(input.getPurpose());
        final UserModel userModel = getUserModel(input.getLoginId());
        if (isAuthenticated(input))
        {
            final UserVerificationTokenData tokenData = userVerificationTokenService.create(purpose,
                    userModel != null ? userModel.getUid() : input.getLoginId());
            // If using mobile number to log in, the verification code should be sent to corresponding email rather than mobile phone.
            final String email = getEmailForUser(userModel, input.getLoginId());
            publishVerificationTokenCreatedEvent(input, tokenData, email);
            return tokenData.getTokenId();
        }
        else
        {
            return userVerificationTokenService.getFakeTokenId(purpose);
        }
    }

    private void publishVerificationTokenCreatedEvent(final CreateVerificationTokenInputData input,
            final UserVerificationTokenData token, final String email)
    {
        VerificationTokenCreatedEvent event = new VerificationTokenCreatedEvent();
        event.setBaseStore(baseStoreService.getCurrentBaseStore());
        event.setSite(baseSiteService.getCurrentBaseSite());
        event.setLanguage(commonI18NService.getCurrentLanguage());
        event.setTarget(email);
        event.setPurpose(input.getPurpose());
        event.setTokenCode(token.getTokenCode());

        eventService.publishEvent(event);
    }

    private UserModel getUserModel(final String loginId)
    {
        try
        {
            return userService.getUserForUID(loginId);
        }
        catch (UnknownIdentifierException e)
        {
            return null;
        }
    }

    private String getEmailForUser(final UserModel user, String loginId)
    {
        String email = loginId;
        if (user instanceof CustomerModel customer)
        {
            email = customer.getOriginalUid() != null ? customer.getOriginalUid() : customer.getUid();
        }
        return email;
    }

    /**
     * Get authentication strategy by purpose
     *
     * @param purpose purpose to create a verification token
     * @return strategy for specified purpose
     */
    public AuthenticationStrategy getAuthenticationStrategyByPurpose(final String purpose)
    {
        return strategyMap.get(purpose);
    }

    public Map<String, AuthenticationStrategy> getStrategyMap()
    {
        return strategyMap;
    }

    public void setStrategyMap(final Map<String, AuthenticationStrategy> strategyMap)
    {
        this.strategyMap = strategyMap;
    }

    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    public void setEventService(final EventService eventService)
    {
        this.eventService = eventService;
    }

    public void setUserVerificationTokenService(final UserVerificationTokenService userVerificationTokenService)
    {
        this.userVerificationTokenService = userVerificationTokenService;
    }

    public void setBaseStoreService(final BaseStoreService baseStoreService)
    {
        this.baseStoreService = baseStoreService;
    }

    public void setBaseSiteService(final BaseSiteService baseSiteService)
    {
        this.baseSiteService = baseSiteService;
    }

    public void setCommonI18NService(final CommonI18NService commonI18NService)
    {
        this.commonI18NService = commonI18NService;
    }
}
