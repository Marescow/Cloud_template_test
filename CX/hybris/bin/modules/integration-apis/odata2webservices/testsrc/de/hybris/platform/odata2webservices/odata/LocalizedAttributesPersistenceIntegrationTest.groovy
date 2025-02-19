/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2webservices.odata

import de.hybris.bootstrap.annotations.IntegrationTest
import de.hybris.platform.catalog.model.CatalogModel
import de.hybris.platform.catalog.model.CatalogVersionModel
import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.integrationservices.CatalogVersionBuilder
import de.hybris.platform.integrationservices.IntegrationObjectModelBuilder
import de.hybris.platform.integrationservices.LanguageBuilder
import de.hybris.platform.integrationservices.util.ItemTracker
import de.hybris.platform.integrationservices.util.JsonBuilder
import de.hybris.platform.odata2services.odata.asserts.ODataAssertions
import de.hybris.platform.odata2webservices.odata.builders.ODataRequestBuilder
import de.hybris.platform.servicelayer.ServicelayerSpockSpecification
import org.apache.olingo.odata2.api.commons.HttpStatusCodes
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.springframework.http.MediaType
import spock.lang.AutoCleanup
import spock.lang.Issue
import spock.lang.Shared

import javax.annotation.Resource

import static de.hybris.platform.integrationservices.IntegrationObjectItemAttributeModelBuilder.integrationObjectItemAttribute
import static de.hybris.platform.integrationservices.IntegrationObjectItemModelBuilder.integrationObjectItem
import static de.hybris.platform.integrationservices.util.IntegrationTestUtil.assertModelExists
import static de.hybris.platform.integrationservices.util.JsonBuilder.json
import static de.hybris.platform.odata2webservices.odata.ODataFacadeTestUtils.createContext

@IntegrationTest
class LocalizedAttributesPersistenceIntegrationTest extends ServicelayerSpockSpecification {

    private static final String SERVICE = 'LocalizedAttributesPersistence_IO'
    private static final String PRODUCTS = 'Products'
    private static final String PRODUCT_CODE = 'orange_product'
    private static final String ORIG_EN_PRODUCT_NAME = 'UPhone 11'
    private static final String ORIG_EN_PRODUCT_DESCRIPTION = 'Latest version of the UPhone'
    private static final String NEW_EN_PRODUCT_NAME = 'UPhone XI'
    private static final String NEW_EN_PRODUCT_DESCRIPTION = 'The newest and fastest UPhone'
    private static final String NEW_DE_PRODUCT_NAME = 'UPhone XI.de'
    private static final String NEW_DE_PRODUCT_DESCRIPTION = 'Das neueste und schnellste UPhone'
    private static final String NEW_ES_PRODUCT_NAME = 'UPhone XI.es'
    private static final String NEW_ES_PRODUCT_DESCRIPTION = 'El \u00FAltimo y m\u00E1s r\u00E1pido UPhone es'
    private static final String NEW_ES_CO_PRODUCT_NAME = 'UPhone XI.es_CO'
    private static final String NEW_ES_CO_PRODUCT_DESCRIPTION = 'El \u00FAltimo y m\u00E1s r\u00E1pido UPhone es_CO'
    private static final String CATALOG_ID = 'LocalizedAttributesPersistence'
    private static final String CATALOG_VERSION_VERSION = 'Staged'
    private static final String NEW_EN_CATALOG_VERSION_CATEGORY_SYSTEM_NAME = 'New tech of the year'
    private static final String INTEGRATION_KEY = "$CATALOG_VERSION_VERSION|$CATALOG_ID|$PRODUCT_CODE"
    private static final String CONTENT_LANGUAGE = 'Content-Language'

    @Shared
    @ClassRule
    IntegrationObjectModelBuilder ioBuilder = IntegrationObjectModelBuilder.integrationObject().withCode(SERVICE)
            .withItem(integrationObjectItem().withCode('Product').root()
                    .withAttribute(integrationObjectItemAttribute('code').unique())
                    .withAttribute(integrationObjectItemAttribute('catalogVersion').withReturnItem('CatalogVersion').unique())
                    .withAttribute(integrationObjectItemAttribute('name'))
                    .withAttribute(integrationObjectItemAttribute('description')))
            .withItem(integrationObjectItem().withCode('CatalogVersion')
                    .withAttribute(integrationObjectItemAttribute('version').unique())
                    .withAttribute(integrationObjectItemAttribute('catalog').withReturnItem('Catalog').unique())
                    .withAttribute(integrationObjectItemAttribute('categorySystemName')))
            .withItem(integrationObjectItem().withCode('Catalog')
                    .withAttribute(integrationObjectItemAttribute('id').unique()))
    @Shared
    @ClassRule
    CatalogVersionBuilder catalogVersionBuilder = CatalogVersionBuilder.catalogVersion()
            .withVersion(CATALOG_VERSION_VERSION)
            .withCatalog(CATALOG_ID)

    @Rule
    ItemTracker itemTracker = ItemTracker.track ProductModel

    @AutoCleanup('cleanup')
    def languageBuilder = LanguageBuilder.languageBuilder()

    @Resource(name = 'defaultODataFacade')
    ODataFacade facade

    @Test
    def "localizedAttributes are persisted with content language '#contentLanguage' and accept language '#acceptLanguage'"() {
        given: 'A request with localized attributes in the body'
        def request = postRequest()
                .withContentLanguage(contentLanguage)
                .withAcceptLanguage(acceptLanguage)
                .withBody(product()
                        .withField('name', ORIG_EN_PRODUCT_NAME)
                        .withField('description', ORIG_EN_PRODUCT_DESCRIPTION)
                        .withLocalizedAttributes(
                                [language: 'en', name: NEW_EN_PRODUCT_NAME, description: NEW_EN_PRODUCT_DESCRIPTION],
                                [language: 'de', name: NEW_DE_PRODUCT_NAME, description: NEW_DE_PRODUCT_DESCRIPTION]))
        and:
        languageBuilder.withCode('de').build()

        when: 'The request is processed'
        def oDataResponse = facade.handleRequest(createContext(request))

        then: 'Response body contains data in Accept-Language locale'
        ODataAssertions.assertThat(oDataResponse)
                .hasStatus(HttpStatusCodes.CREATED)
                .jsonBody()
                .hasPathWithValue('d.name', bodyValues['name'])
                .hasPathWithValue('d.description', bodyValues['description'])

        and: 'Localized attributes are persisted at the item level.'
        ProductModel productModel = retrieveProduct()
        NEW_EN_PRODUCT_NAME == productModel.getProperty('name', Locale.ENGLISH)
        NEW_EN_PRODUCT_DESCRIPTION == productModel.getProperty('description', Locale.ENGLISH)
        NEW_DE_PRODUCT_NAME == productModel.getProperty('name', Locale.GERMAN)
        NEW_DE_PRODUCT_DESCRIPTION == productModel.getProperty('description', Locale.GERMAN)

        and: 'Localized attributes are persisted at a nested level.'
        CatalogVersionModel catalogVersionModel = retrieveCatalogVersion()
        NEW_EN_CATALOG_VERSION_CATEGORY_SYSTEM_NAME == catalogVersionModel.getProperty('categorySystemName', Locale.ENGLISH)

        and: 'Response contains Content-Language header with the same locale as body'
        def header = oDataResponse.getHeader(CONTENT_LANGUAGE)
        header == acceptLanguage.language

        where:
        contentLanguage | acceptLanguage | bodyValues
        Locale.ENGLISH  | Locale.ENGLISH | [name: NEW_EN_PRODUCT_NAME, description: NEW_EN_PRODUCT_DESCRIPTION]
        Locale.ENGLISH  | Locale.GERMAN  | [name: NEW_DE_PRODUCT_NAME, description: NEW_DE_PRODUCT_DESCRIPTION]
    }

    @Test
    def "#localizedAttribute is persisted with '#description' description using locale #locale"() {
        given:
        def request = postRequest()
                .withContentLanguage(Locale.ENGLISH)
                .withBody(product().withLocalizedAttributes(localizedAttribute))
                .build()
        and:
        languageBuilder.withCode('de').build()
        languageBuilder.withCode('es').build()
        languageBuilder.withCode('es_CO').build()

        when:
        facade.handleRequest(createContext(request))

        then:
        ProductModel productModel = retrieveProduct()
        name == productModel.getProperty('name', locale)
        description == productModel.getProperty('description', locale)

        where:
        localizedAttribute                                                                            | name                   | description                   | locale
        [language: 'de', name: NEW_DE_PRODUCT_NAME, description: '']                                  | NEW_DE_PRODUCT_NAME    | ''                            | Locale.GERMAN
        [language: 'de', name: NEW_DE_PRODUCT_NAME]                                                   | NEW_DE_PRODUCT_NAME    | null                          | Locale.GERMAN
        [language: 'es', name: NEW_ES_PRODUCT_NAME, description: NEW_ES_PRODUCT_DESCRIPTION]          | NEW_ES_PRODUCT_NAME    | NEW_ES_PRODUCT_DESCRIPTION    | createLocale('es')
        [language: 'es_CO', name: NEW_ES_CO_PRODUCT_NAME, description: NEW_ES_CO_PRODUCT_DESCRIPTION] | NEW_ES_CO_PRODUCT_NAME | NEW_ES_CO_PRODUCT_DESCRIPTION | createLocale('es', 'CO')
    }

    @Test
    def "exception is thrown if the language is not provided in the localizedAttribute map entry"() {
        given:
        def request = postRequest()
                .withContentLanguage(Locale.ENGLISH)
                .withBody(product().withLocalizedAttributes([name: NEW_EN_PRODUCT_NAME, description: NEW_EN_PRODUCT_DESCRIPTION]))

        when:
        def oDataResponse = facade.handleRequest(createContext(request))

        then:
        ODataAssertions.assertThat(oDataResponse)
                .hasStatus(HttpStatusCodes.BAD_REQUEST)
                .jsonBody()
                .hasPathWithValue('error.code', 'missing_language')
    }

    @Test
    def "exception is thrown if the provided language does not match any existing platform language"() {
        given: "'zz' is a language tag that is not supported by the platform"
        def request = postRequest()
                .withContentLanguage(Locale.ENGLISH)
                .withBody(product().withLocalizedAttributes([language: 'zz', name: NEW_EN_PRODUCT_NAME]))

        when:
        def oDataResponse = facade.handleRequest(createContext(request))

        then:
        ODataAssertions.assertThat(oDataResponse)
                .hasStatus(HttpStatusCodes.BAD_REQUEST)
                .jsonBody()
                .hasPathWithValue('error.code', 'invalid_language')
                .hasPathWithValue('error.innererror', INTEGRATION_KEY)
    }

    @Test
    def "Content-Language is returned when Accept-Language is omitted"() {
        given:
        def request = postRequest()
                .withContentLanguage(Locale.GERMAN)
                .withBody(product().withLocalizedAttributes(
                        [language: 'en', name: NEW_EN_PRODUCT_NAME, description: NEW_EN_PRODUCT_DESCRIPTION],
                        [language: 'de', name: NEW_DE_PRODUCT_NAME, description: NEW_DE_PRODUCT_DESCRIPTION]))
        and:
        languageBuilder.withCode('de').build()

        when:
        def oDataResponse = facade.handleRequest(createContext(request))

        then: 'The value of the response body correspond with the locale provided in the Content-Language request header'
        ODataAssertions.assertThat(oDataResponse).jsonBody()
                .hasPathWithValue('d.name', NEW_DE_PRODUCT_NAME)
                .hasPathWithValue('d.description', NEW_DE_PRODUCT_DESCRIPTION)

        and: 'Content-Language is included in the response header'
        def header = oDataResponse.getHeader(CONTENT_LANGUAGE)
        header == Locale.GERMAN.language
    }

    @Test
    @Issue('https://jira.tools.sap/browse/CXEC-44964')
    def "request language #requestLang can be persisted when #platformLang language exists in the platform"() {
        given:
        languageBuilder.withCode(platformLang).build()
        and:
        def request = postRequest()
                .withContentLanguage(requestLang)
                .withBody(product()
                        .withField('name', 'some value')
                        .withLocalizedAttributes(
                                [language: requestLang.toString(), description: 'some value']))

        when:
        def response = facade.handleRequest createContext(request)

        then:
        response.status == HttpStatusCodes.CREATED
        and:
        def product = retrieveProduct()
        product.getName(persistedLang) == 'some value'
        product.getDescription(persistedLang) == 'some value'

        where:
        requestLang              | platformLang | persistedLang
        createLocale('de', 'CH') | 'de'         | createLocale('de')
        createLocale('de', 'CH') | 'de_CH'      | createLocale('de', 'CH')
        createLocale('de', 'CH') | 'de-CH'      | createLocale('de', 'CH')
    }

    private static ProductModel retrieveProduct() {
        assertModelExists new ProductModel(code: PRODUCT_CODE)
    }

    private static CatalogModel retrieveCatalog() {
        assertModelExists new CatalogModel(id: CATALOG_ID)
    }

    private static CatalogVersionModel retrieveCatalogVersion() {
        assertModelExists new CatalogVersionModel(catalog: retrieveCatalog(), version: CATALOG_VERSION_VERSION)
    }

    private static JsonBuilder product() {
        json()
                .withCode(PRODUCT_CODE)
                .withField('catalogVersion', catalogVersion())
    }

    private static JsonBuilder catalogVersion() {
        json()
                .withField('version', CATALOG_VERSION_VERSION)
                .withField('catalog', catalog())
                .withLocalizedAttributes([language: 'en', categorySystemName: NEW_EN_CATALOG_VERSION_CATEGORY_SYSTEM_NAME])
    }

    private static JsonBuilder catalog() {
        json().withId(CATALOG_ID)
    }

    private static ODataRequestBuilder postRequest() {
        ODataFacadeTestUtils.postRequestBuilder(SERVICE, PRODUCTS, MediaType.APPLICATION_JSON_VALUE)
    }

    private static Locale createLocale(String language) {
        new Locale.Builder().setLanguage(language).build()
    }

    private static Locale createLocale(String language, String region) {
        new Locale.Builder()
                .setLanguage(language)
                .setRegion(region)
                .build()
    }
}
