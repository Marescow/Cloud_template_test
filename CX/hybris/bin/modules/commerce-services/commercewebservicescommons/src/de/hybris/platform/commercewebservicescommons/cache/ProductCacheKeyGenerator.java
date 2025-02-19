/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

 package de.hybris.platform.commercewebservicescommons.cache;

 import de.hybris.platform.core.Registry;
 import de.hybris.platform.servicelayer.session.SessionService;
 import org.assertj.core.util.Lists;
 
 import java.util.List;
 
 public class ProductCacheKeyGenerator extends CommerceCacheKeyGenerator
 {
     private static String previewTicketIdParam = "cmsTicketId";
     private SessionService sessionService;
 
     @Override
     public Object generate(final boolean addUserToKey, final boolean addCurrencyToKey, final Object... params)
     {
        final String previewTicketId = getSessionService().getAttribute(previewTicketIdParam);
        final List<Object> allParams = Lists.newArrayList(params);
        allParams.add(previewTicketId);

        return super.generate(addUserToKey, addCurrencyToKey, allParams.toArray(Object[]::new));
     }
 
     public static Object generateKey(final boolean addUserToKey, final boolean addCurrencyToKey, final Object... params)
     {
         final CommerceCacheKeyGenerator keyGeneratorBean = (CommerceCacheKeyGenerator) Registry.getApplicationContext()
                 .getBean("productCacheKeyGenerator");
         return keyGeneratorBean.generate(addUserToKey, addCurrencyToKey, params);
     }
 
     public SessionService getSessionService()
     {
         return sessionService;
     }
 
     public void setSessionService(final SessionService sessionService)
     {
         this.sessionService = sessionService;
     }
 }
 