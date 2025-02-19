/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.webhookservices.cache

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.core.PK
import de.hybris.platform.core.model.ItemModel
import de.hybris.platform.integrationservices.cache.IntegrationCache
import de.hybris.platform.integrationservices.util.LoggerProbe
import de.hybris.platform.jalo.JaloInternalException
import de.hybris.platform.servicelayer.exceptions.ModelLoadingException
import de.hybris.platform.servicelayer.interceptor.InterceptorContext
import de.hybris.platform.servicelayer.model.ModelService
import de.hybris.platform.servicelayer.tenant.MockTenant
import de.hybris.platform.testframework.JUnitPlatformSpecification
import de.hybris.platform.tx.Transaction
import de.hybris.platform.webhookservices.dto.WebhookItemPayload
import de.hybris.platform.webhookservices.event.ItemDeletedEvent
import de.hybris.platform.webhookservices.event.ItemUpdatedEvent
import de.hybris.platform.webhookservices.model.WebhookConfigurationModel
import de.hybris.platform.webhookservices.service.WebhookConfigurationService
import org.junit.Rule
import org.junit.Test
import org.springframework.core.convert.converter.Converter
import spock.lang.Shared

@UnitTest
class DefaultWebhookCacheServiceUnitTest extends JUnitPlatformSpecification {

    private static final def JUNIT_TENANT = "junit"
    private static final def ITEM_PK = PK.fromLong(1234)
    private static final def CACHE_KEY = webhookDeletedItemCacheKey()
    private static final def CONFIGURATION = new WebhookConfigurationModel()

    @Rule
    LoggerProbe probe = LoggerProbe.create DefaultWebhookCacheService

    @Shared
    def DELETED_ITEM = Stub(ItemModel) { getPk() >> ITEM_PK }

    def payloadsCache = Mock(IntegrationCache)
    def deletedItemCache = Mock(IntegrationCache)
    def transaction = Stub(Transaction)
    def configurationService = Stub(WebhookConfigurationService)
    def interceptor = Stub(InterceptorContext) {
        getModelService() >> Stub(ModelService) {
            get(ITEM_PK) >> DELETED_ITEM
            clone(DELETED_ITEM) >> DELETED_ITEM
        }
    }
    def payloadConverter = Stub(DefaultWebhookItemPayloadConverter)

    def cacheService = new DefaultWebhookCacheService(payloadsCache, deletedItemCache, configurationService, payloadConverter)

    @Test
    void "retrieve cached value #valueInCache for cache key #cacheKey from the deleted item Cache"() {
        given:
        deletedItemCache.get(cacheKey) >> valueInCache

        when:
        def result = cacheService.findItem(ITEM_PK)

        then:
        result.isPresent() == isPresent
        times * deletedItemCache.remove(cacheKey)

        where:
        cacheKey  | valueInCache | times | isPresent
        CACHE_KEY | DELETED_ITEM | 1     | true
        CACHE_KEY | null         | 0     | false
        null      | null         | 0     | false
    }

    @Test
    void "retrieve cached value #valueInCache for cache key #cacheKey from the itemPayload Cache"() {
        given:
        payloadsCache.get(cacheKey) >> valueInCache

        when:
        def result = cacheService.findItemPayloads(ITEM_PK)

        then:
        result.isPresent() == isPresent
        times * payloadsCache.remove(cacheKey)

        where:
        cacheKey  | valueInCache             | times | isPresent
        CACHE_KEY | Stub(WebhookItemPayload) | 1     | true
        CACHE_KEY | null                     | 0     | false
        null      | null                     | 0     | false
    }

    @Test
    void "cached deleted item when webhook configuration found"() {
        given:
        configurationService.findByEventAndItemMatchingAnyItem(_ as ItemUpdatedEvent, DELETED_ITEM) >> findUpdateEventConfig
        configurationService.getWebhookConfigurationsByEventAndItemModel(_ as ItemDeletedEvent, DELETED_ITEM) >> findDeleteEventConfig

        when:
        cacheService.cacheDeletedItem(DELETED_ITEM, interceptor)

        then:
        putToDeleteCacheTimes * deletedItemCache.put(CACHE_KEY, DELETED_ITEM)
        putToPayloadCacheTimes * payloadsCache.put(CACHE_KEY, _)

        where:
        findUpdateEventConfig | findDeleteEventConfig | putToDeleteCacheTimes | putToPayloadCacheTimes
        [CONFIGURATION]       | [CONFIGURATION]       | 1                     | 1
        []                    | [CONFIGURATION]       | 1                     | 1
        [CONFIGURATION]       | []                    | 1                     | 0
        []                    | []                    | 0                     | 0
    }

    @Test
    void "deleted item will be refreshed to removed stale references before caching"() {
        given:
        def staleItem = Stub(ItemModel) { pk >> ITEM_PK }
        configurationService.findByEventAndItemMatchingAnyItem(_ as ItemUpdatedEvent, staleItem) >> findUpdateEventConfig
        configurationService.getWebhookConfigurationsByEventAndItemModel(_ as ItemDeletedEvent, staleItem) >> findDeleteEventConfig

        when:
        cacheService.cacheDeletedItem(staleItem, interceptor)

        then: 'refreshed item is cached'
        putToDeleteCacheTimes * deletedItemCache.put(CACHE_KEY, DELETED_ITEM)

        where:
        findUpdateEventConfig | findDeleteEventConfig | putToDeleteCacheTimes
        [CONFIGURATION]       | [CONFIGURATION]       | 1
        []                    | [CONFIGURATION]       | 1
        [CONFIGURATION]       | []                    | 1
        []                    | []                    | 0
    }

    @Test
    void "cached item will be removed when transaction rollback"() {
        given:
        transaction.executeOnRollback(_ as Transaction.TransactionAwareExecution) >> {
            List args -> (args[0] as Transaction.TransactionAwareExecution).execute(transaction)
        }

        and:
        def cacheServiceSpy = Spy(cacheService)
        cacheServiceSpy.getCurrentTransaction() >> transaction

        configurationService.findByEventAndItemMatchingAnyItem(_ as ItemUpdatedEvent, DELETED_ITEM) >> [CONFIGURATION]
        configurationService.getWebhookConfigurationsByEventAndItemModel(_ as ItemDeletedEvent, DELETED_ITEM) >> [CONFIGURATION]

        when:
        transaction.begin()
        cacheServiceSpy.cacheDeletedItem(DELETED_ITEM, interceptor)
        transaction.rollback()

        then:
        1 * deletedItemCache.remove(CACHE_KEY)
        1 * payloadsCache.remove(CACHE_KEY)
    }

    @Test
    void "instantiation fails because #param cannot be null"() {
        when:
        new DefaultWebhookCacheService(payloadCache, deletedCache, configService, converter)

        then:
        def e = thrown IllegalArgumentException
        e.message == "$param cannot be null"

        where:
        payloadCache           | deletedCache           | configService                     | converter       | param
        null                   | Stub(IntegrationCache) | Stub(WebhookConfigurationService) | Stub(Converter) | "itemPayloadCache"
        Stub(IntegrationCache) | null                   | Stub(WebhookConfigurationService) | Stub(Converter) | "deletedItemCache"
        Stub(IntegrationCache) | Stub(IntegrationCache) | null                              | Stub(Converter) | "WebhookConfigurationService"
        Stub(IntegrationCache) | Stub(IntegrationCache) | Stub(WebhookConfigurationService) | null            | "WebhookItemPayloadConverter"
    }

    @Test
    void "expect log message when modelService cannot clone the model "() {
        given:
        def ctx = Stub(InterceptorContext) {
            getModelService() >> Stub(ModelService) {
                get(ITEM_PK) >> DELETED_ITEM
                clone(DELETED_ITEM) >> { throw Stub(JaloInternalException) }
            }
        }

        and:
        configurationService.findByEventAndItemMatchingAnyItem(_ as ItemUpdatedEvent, DELETED_ITEM) >> [CONFIGURATION]

        when:
        cacheService.cacheDeletedItem(DELETED_ITEM, ctx)

        then:
        with(probe.allLoggedRecords[0].message) {
            contains 'Could not clone the item'
            contains "with PK: [$ITEM_PK]"
        }
    }

    @Test
    void "expect log message when modelService cannot find the model"() {
        given:
        def ctx = Stub(InterceptorContext) {
            getModelService() >> Stub(ModelService) {
                get(ITEM_PK) >> { throw exception }
            }
        }

        and:
        configurationService.findByEventAndItemMatchingAnyItem(_ as ItemUpdatedEvent, DELETED_ITEM) >> [CONFIGURATION]

        when:
        cacheService.cacheDeletedItem(DELETED_ITEM, ctx)

        then:
        with(probe.allLoggedRecords[0].message) {
            contains 'Could not find the item'
            contains "with PK: [$ITEM_PK]"
        }

        where:
        exception << [Stub(ModelLoadingException), Stub(IllegalArgumentException)]
    }

    private static WebhookDeletedItemCacheKey webhookDeletedItemCacheKey() {
        WebhookTenantUtil.setTenant(new MockTenant(JUNIT_TENANT))
        return WebhookDeletedItemCacheKey.from(ITEM_PK)
    }
}
