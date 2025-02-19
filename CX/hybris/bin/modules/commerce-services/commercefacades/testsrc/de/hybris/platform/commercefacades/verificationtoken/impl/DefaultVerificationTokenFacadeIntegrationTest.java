/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.verificationtoken.data.CreateVerificationTokenInputData;
import de.hybris.platform.commerceservices.event.VerificationTokenCreatedEvent;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.user.UserVerificationTokenService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationEventPublisher;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@IntegrationTest
public class DefaultVerificationTokenFacadeIntegrationTest extends ServicelayerTest {
    @Resource
    private BaseSiteService baseSiteService;
    @Resource
    private EventService eventService;
    @Resource
    private UserVerificationTokenService userVerificationTokenService;
    @Resource
    private DefaultVerificationTokenFacade verificationTokenFacade;
    @Resource(name = "userAuthenticationEventPublisher")
    private AuthenticationEventPublisher authenticationEventPublisher;
    @Resource
    private LoginAuthenticationStrategy loginAuthenticationStrategy;

    UserVerificationTokenService spyUserVerificationTokenService;
    AuthenticationEventPublisher spyAuthenticationEventPublisher;
    EventService spyEventService;

    @Before
    public void setUp() throws Exception
    {
        // importing test data
        importCsv("/commercefacades/test/testVerificationToken.csv", "utf-8");

        spyAuthenticationEventPublisher = spy(authenticationEventPublisher);
        spyUserVerificationTokenService = spy(userVerificationTokenService);
        spyEventService = spy(eventService);
        loginAuthenticationStrategy.setAuthenticationEventPublisher(spyAuthenticationEventPublisher);
        verificationTokenFacade.setUserVerificationTokenService(spyUserVerificationTokenService);
        verificationTokenFacade.setEventService(spyEventService);
    }

    @Test
    public void shouldCreateVerificationToken() {
        // given
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setLoginId("verificationcodeuser@sap.com");
        input.setPassword("1234");
        input.setPurpose("LOGIN");
        // when
        // set current site to a none-standalone site
        baseSiteService.setCurrentBaseSite("test-site", false);
        String tokenId = verificationTokenFacade.createVerificationToken(input);
        // then
        assertNotNull(tokenId);
        assertTrue(tokenId.contains("LGN"));
        verify(spyUserVerificationTokenService).create(SAPUserVerificationPurpose.LOGIN, "verificationcodeuser@sap.com");
        verify(spyEventService).publishEvent(argThat((VerificationTokenCreatedEvent event) ->
             StringUtils.isNotEmpty(event.getTokenCode()) &&
                    event.getBaseStore() != null &&
                    event.getSite() != null &&
                    event.getLanguage() != null &&
                    event.getTarget().equals("verificationcodeuser@sap.com") &&
                    event.getPurpose().equals("LOGIN")
        ));
    }

//    @Test
//    public void shouldCreateVerificationTokenForStandaloneSiteCustomer() {
//        // given
//        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
//        input.setLoginId("standaloneverificationcodeuser@sap.com");
//        input.setPassword("1234");
//        input.setPurpose("LOGIN");
//        // when
//        // set current site to a standalone site
//        baseSiteService.setCurrentBaseSite("test-standalone-site", false);
//        String tokenId = verificationTokenFacade.createVerificationToken(input);
//        // then
//        assertNotNull(tokenId);
//        assertTrue(tokenId.contains("LGN"));
//        verify(spyUserVerificationTokenService).create(SAPUserVerificationPurpose.LOGIN, "standaloneverificationcodeuser@sap.com|test-standalone-site");
//        verify(spyEventService).publishEvent(argThat((VerificationTokenCreatedEvent event) ->
//                StringUtils.isNotEmpty(event.getTokenCode()) &&
//                        event.getBaseStore() != null &&
//                        event.getSite() != null &&
//                        event.getLanguage() != null &&
//                        event.getTarget().equals("standaloneverificationcodeuser@sap.com") &&
//                        event.getPurpose().equals("LOGIN")
//        ));
//    }

    @Test
    public void shouldCreateFakeVerificationTokenWhenLoginIdNotExists() {
        // given
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setLoginId("noneExistsUser@sap.com");
        input.setPassword("1234");
        input.setPurpose("LOGIN");
        // when
        String tokenId = verificationTokenFacade.createVerificationToken(input);
        // then
        assertNotNull(tokenId);
        assertTrue(tokenId.contains("LGN"));
        verify(spyUserVerificationTokenService).getFakeTokenId(SAPUserVerificationPurpose.LOGIN);
        verify(spyEventService, never()).publishEvent(any());
    }

    @Test
    public void shouldCreateFakeVerificationTokenWhenPasswordIsIncorrect() {
        // given
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setLoginId("verificationcodeuser@sap.com");
        input.setPassword("incorrectPassword");
        input.setPurpose("LOGIN");
        // when
        String tokenId = verificationTokenFacade.createVerificationToken(input);
        // then
        assertNotNull(tokenId);
        assertTrue(tokenId.contains("LGN"));
        verify(spyUserVerificationTokenService).getFakeTokenId(SAPUserVerificationPurpose.LOGIN);
        verify(spyEventService, never()).publishEvent(any());
        verify(spyAuthenticationEventPublisher).publishAuthenticationFailure(any(), any());
    }

    @Test
    public void shouldCreateFakeVerificationTokenWhenLoginIsDisabled() {
        // given
        CreateVerificationTokenInputData input = new CreateVerificationTokenInputData();
        input.setLoginId("disabledverificationcodeuser@sap.com");
        input.setPassword("incorrectPassword");
        input.setPurpose("LOGIN");

        // when
        String tokenId = verificationTokenFacade.createVerificationToken(input);
        // then
        assertNotNull(tokenId);
        assertTrue(tokenId.contains("LGN"));
        verify(spyUserVerificationTokenService).getFakeTokenId(SAPUserVerificationPurpose.LOGIN);
        verify(spyEventService, never()).publishEvent(any());
        verify(spyAuthenticationEventPublisher).publishAuthenticationFailure(any(), any());
    }

}
