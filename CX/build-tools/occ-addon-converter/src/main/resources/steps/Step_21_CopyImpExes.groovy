/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import de.hybris.platform.tools.occ.addonconverter.ScriptUtils

def impexFiles = []
def impexOperations = [
		'INSERT',
		'INSERT_UPDATE',
		'REMOVE',
		'UPDATE',
]

def hasImpexOperationsClosure = {
	line -> impexOperations.any(line.&contains)
}

def isFilePerformingOperationClosure = {
	it.filterLine(hasImpexOperationsClosure).any()
}

if (ScriptUtils.isDirectory("${ctx.ADDON_DIRECTORY}/resources/$ctx.ADDON_NAME/import/")) {
	new File("${ctx.ADDON_DIRECTORY}/resources/$ctx.ADDON_NAME/import/").eachFileRecurse {
		impexFiles.add(it)
	}
}

def reducedImpexFiles = impexFiles
		.findAll(isFilePerformingOperationClosure)

reducedImpexFiles.each {
	ant.copy(file: it, todir: "$ctx.EXTENSION_DIRECTORY/resources/impex/")
}
