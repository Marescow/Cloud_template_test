/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import de.hybris.platform.tools.occ.addonconverter.ScriptUtils

def libsToCopy = []
def pathsToLib = [
		"${ctx.ADDON_DIRECTORY}/lib/",
		"${ctx.ADDON_DIRECTORY}/acceleratoraddon/web/webroot/WEB-INF/lib/",
]

def libDirectories = pathsToLib
		.findAll(ScriptUtils.&isDirectory)
		.collect(File.&new)

libDirectories.each {
	it.eachFileRecurse {
		if (it.toString().endsWith('.jar')) {
			libsToCopy.add(it)
		}
	}
}

libsToCopy.each {
	ant.copy(file: it, todir: "${ctx.EXTENSION_DIRECTORY}/lib", overwrite: true)
}
