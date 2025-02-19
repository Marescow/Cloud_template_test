/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedserviceservices.impl;

import de.hybris.platform.assistedserviceservices.AssistedServiceService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customersupport.CommerceCustomerSupportService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;


/**
 * This implementation returns actual data from {@link de.hybris.platform.assistedserviceservices.impl.DefaultAssistedServiceService}.
 *
 */
public class DefaultCommerceCustomerSupportService implements CommerceCustomerSupportService
{
    private AssistedServiceService assistedServiceService;

    private UserService userService;
    private SessionService sessionService;

    @Override
    public boolean isCustomerSupportAgentActive()
    {
        return getAgent() != null;
    }

    @Override
    public UserModel getEmulatedCustomer()
    {
        if (getAssistedServiceService().getAsmSession() != null)
        {
            return getAssistedServiceService().getAsmSession().getEmulatedCustomer();
        }
        else if (getActingUserFromSessionContext() != null)
        {
            return userService.getCurrentUser();
        }
        return null;
    }

    @Override
    public UserModel getAgent()
    {
        return getAssistedServiceService().getAsmSession() != null ? getAssistedServiceService().getAsmSession().getAgent() : getActingUserFromSessionContext();
    }

    /**
     * get that acting user, and if acting user is there and different from user.
     * we return it.
     *
     * @return userModel if it's agent, or return null
     */
    protected UserModel getActingUserFromSessionContext()
    {
        final String actingUserUid = sessionService.getCurrentSession()
              .getAttribute(DefaultAssistedServiceService.ACTING_USER_UID);
        final String userId = userService.getCurrentUser().getUid();
        if (actingUserUid != null && !userId.equals(actingUserUid))
        {
            return userService.getUserForUID(actingUserUid);
        }
        return null;
    }

    protected AssistedServiceService getAssistedServiceService()
    {
        return assistedServiceService;
    }


    @Required
    public void setAssistedServiceService(final AssistedServiceService assistedServiceService)
    {
        this.assistedServiceService = assistedServiceService;
    }

    public SessionService getSessionService()
    {
        return this.sessionService;
    }
    public void setSessionService(final SessionService sessionService)
    {
        this.sessionService = sessionService;
    }

    public UserService getUserService()
    {
        return this.userService;
    }
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }
}
