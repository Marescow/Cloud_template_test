/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.cache;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ProductCacheKeyGeneratorTest
{
    private static String previewTicketIdParam = "cmsTicketId";
    @Mock
    private SessionService sessionService;

    @Mock
    private BaseSiteService baseSiteService;

    @Mock
    private CommonI18NService commonI18NService;

    @Mock
    private UserService userService;

    private final ProductCacheKeyGenerator keyGenerator = new ProductCacheKeyGenerator();

    @Before
    public void setUp() throws Exception
    {
        keyGenerator.setSessionService(sessionService);
        keyGenerator.setBaseSiteService(baseSiteService);
        keyGenerator.setCommonI18NService(commonI18NService);
        keyGenerator.setUserService(userService);
    }

    @Test
    public void testDifferentCMSTicketId()
    {
        given(sessionService.getAttribute(previewTicketIdParam)).willReturn("ticketId1");
        final Object key1 = keyGenerator.generate(true, true, "23355");
        given(sessionService.getAttribute(previewTicketIdParam)).willReturn("ticketId2");
        final Object key2 = keyGenerator.generate(true, true, "23355");

        assertNotEquals(((ArrayList)key1).get(5), ((ArrayList)key2).get(5));
    }

    @Test
    public void testSameCMSTicketId()
    {
        given(sessionService.getAttribute(previewTicketIdParam)).willReturn("ticketId");
        final Object key1 = keyGenerator.generate(true, true, "23355", "name");
        final Object key2 = keyGenerator.generate(true, true, "23355", "name");

        assertArrayEquals(((ArrayList)key1).toArray(), ((ArrayList)key2).toArray());
    }

    @Test
    public void testSameCMSTicketIdDifferentFields()
    {
        given(sessionService.getAttribute(previewTicketIdParam)).willReturn("ticketId");
        final Object key1 = keyGenerator.generate(true, true, "23355", "name");
        final Object key2 = keyGenerator.generate(true, true, "23355", "multidimensional");

        assertNotEquals(((ArrayList)key1).get(5), ((ArrayList)key2).get(5));
    }
}
