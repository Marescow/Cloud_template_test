/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.webhookservices.cache

import de.hybris.bootstrap.annotations.IntegrationTest
import de.hybris.platform.core.model.c2l.CurrencyModel
import de.hybris.platform.core.model.order.OrderModel
import de.hybris.platform.core.model.user.CustomerModel
import de.hybris.platform.integrationservices.IntegrationObjectModelBuilder
import de.hybris.platform.integrationservices.util.IntegrationTestUtil
import de.hybris.platform.integrationservices.util.Log
import de.hybris.platform.integrationservices.util.impex.ModuleEssentialData
import de.hybris.platform.outboundservices.ConsumedDestinationBuilder
import de.hybris.platform.payment.model.PaymentTransactionModel
import de.hybris.platform.servicelayer.ServicelayerSpockSpecification
import de.hybris.platform.servicelayer.model.ModelService
import de.hybris.platform.webhookservices.event.ItemSavedEvent
import de.hybris.platform.webhookservices.util.WebhookServicesEssentialData
import org.apache.commons.collections4.CollectionUtils
import org.junit.ClassRule
import org.junit.Test
import spock.lang.AutoCleanup
import spock.lang.Issue
import spock.lang.Shared

import javax.annotation.Resource

import static de.hybris.platform.integrationservices.IntegrationObjectItemAttributeModelBuilder.integrationObjectItemAttribute
import static de.hybris.platform.integrationservices.IntegrationObjectItemModelBuilder.integrationObjectItem
import static de.hybris.platform.integrationservices.IntegrationObjectModelBuilder.integrationObject
import static de.hybris.platform.outboundservices.ConsumedDestinationBuilder.consumedDestinationBuilder
import static de.hybris.platform.webhookservices.WebhookConfigurationBuilder.webhookConfiguration

@IntegrationTest
@Issue('https://jira.tools.sap/browse/CXEC-31882')
class WebhookCacheServiceIntegrationTest extends ServicelayerSpockSpecification {
    private static final def LOG = Log.getLogger(WebhookCacheServiceIntegrationTest)
    private static final def TEST_NAME = 'WebhookCacheServiceIntegrationTest'
    private static final def IO_CODE = "${TEST_NAME}_IO"
    private static final def ORDER_CODE = "${TEST_NAME}_ORDER"
    private static final def CUSTOMER_UID = "${TEST_NAME}_CUSTOMER".toLowerCase()
    private static final def CURRENCY = "EUR"
    private static final def DESTINATION = "${TEST_NAME}_Orders"
    private static final def TRANSACTION_CODE = "${TEST_NAME}_Transaction"

    @Resource
    private ModelService modelService
    @Resource
    private WebhookCacheService webhookCacheService

    @AutoCleanup('cleanup')
    def webhookBuilder = webhookConfiguration().withDestination(defaultDestinationBuilder())

    @Shared
    @ClassRule
    IntegrationObjectModelBuilder io =
            integrationObject().withCode(IO_CODE)
                    .withItem(integrationObjectItem().withCode('Customer').root()
                            .withAttribute(integrationObjectItemAttribute().withName('uid').unique())
                            .withAttribute(integrationObjectItemAttribute().withName('orders').withReturnItem('Order')))
                    .withItem(integrationObjectItem().withCode('Order'))

    @Shared
    @ClassRule
    ModuleEssentialData essentialData = WebhookServicesEssentialData.webhookServicesEssentialData()

    def setupSpec() {
        IntegrationTestUtil.importImpEx(
                'INSERT_UPDATE Currency; isocode[unique = true]',
                "                      ; $CURRENCY             ")
    }

    def cleanup() {
        IntegrationTestUtil.remove(PaymentTransactionModel) { it.code == TRANSACTION_CODE }
        IntegrationTestUtil.remove(OrderModel) { it.code == ORDER_CODE }
        IntegrationTestUtil.remove(CustomerModel) { it.uid == CUSTOMER_UID }
    }

    def cleanupSpec() {
        IntegrationTestUtil.remove(CurrencyModel) { it.isocode == CURRENCY }
    }

    @Test
    void "refreshes the item before caching to remove references to an already deleted partOf child"() {
        given:
        webhookBuilder.withIntegrationObject(IO_CODE)
                .withEvent(ItemSavedEvent)
                .build()

        def customer = createCustomer()
        def order = customer.orders[0]
        def transaction = order.paymentTransactions[0]

        and: 'partOf child item is removed'
        modelService.remove(transaction)

        expect: 'item have stale reference to partOf child before caching'
        CollectionUtils.isNotEmpty order.paymentTransactions

        when: 'item is deleted'
        modelService.remove(order)

        then: 'updated item was cached'
        def savedOrder = webhookCacheService.findItem(order.pk).get() as OrderModel
        CollectionUtils.isEmpty savedOrder.paymentTransactions

        when:
        modelService.remove(customer)

        then:
        def savedCustomer = webhookCacheService.findItem(customer.pk).get() as CustomerModel
        CollectionUtils.isEmpty savedCustomer.orders
    }

    private static ConsumedDestinationBuilder defaultDestinationBuilder(final String destinationId = DESTINATION) {
        consumedDestinationBuilder()
                .withId(destinationId)
                .withUrl('https://path/to/webhooks')
                .withDestinationTarget('webhookServices')
    }

    private static CustomerModel createCustomer() {
        IntegrationTestUtil.importImpEx(
                'INSERT_UPDATE Customer; uid[unique = true]; name',
                "                      ; $CUSTOMER_UID     ; $CUSTOMER_UID",
                'INSERT_UPDATE Order; code[unique = true]; user(uid)        ; currency(isocode); date[dateformat=MM/dd/yyyy]',
                "                          ; $ORDER_CODE        ; $CUSTOMER_UID    ; $CURRENCY        ; 09/02/2021",
                'INSERT_UPDATE PaymentTransaction; code[unique = true]; order(code);',
                "                                ; $TRANSACTION_CODE    ; $ORDER_CODE")

        IntegrationTestUtil.findAny(CustomerModel, { it.uid == CUSTOMER_UID }).orElse(null)
                .tap { LOG.info 'Created {}', it }
    }
}
