/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices.item

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.testframework.JUnitPlatformSpecification
import org.junit.Test

@UnitTest
class StringToLocaleConverterUnitTest extends JUnitPlatformSpecification {
    def converter = new StringToLocaleConverter()

    @Test
    void "converts #input to #output"() {
        expect:
        converter.convert(input) == output

        where:
        input                      | output
        null                       | null
        ''                         | new Locale('')
        'en'                       | new Locale('en')
        'en-'                      | new Locale('en')
        'en-us'                    | new Locale('en', 'US')
        'es-CO'                    | new Locale('es', 'CO')
        'en_CA'                    | new Locale('en', 'CA')
        'es-es'                    | new Locale('es', 'ES')
        'es-es_'                   | new Locale('es', 'ES')
        'es_es-an'                 | new Locale('es', 'ES')
        'en-US-TX'                 | new Locale('en', 'US')
        'en-US-tx-posix'           | new Locale('en', 'US')
        'newLanguage'              | new Locale('newLanguage')
        'newLanguage_country'      | new Locale('newLanguage', 'country')
        'newLanguage-country'      | new Locale('newLanguage', 'country')
        'newLanguage-country-temp' | new Locale('newLanguage', 'country')
    }
}
