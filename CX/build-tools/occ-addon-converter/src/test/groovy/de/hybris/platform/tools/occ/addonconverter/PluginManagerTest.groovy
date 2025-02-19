/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import static groovy.test.GroovyAssert.assertEquals
import org.junit.Test

class PluginManagerTest {

	@Test
	void testGetSteps() {
		assertEquals(10, PluginManager.getSteps("/fakeCustomStepsDirectory").size())
	}

}
