/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import static groovy.io.FileType.FILES
import de.hybris.platform.tools.occ.addonconverter.ScriptUtils

replaceAddonName()
replaceYcommercewebservices()
replaceAllCapitalizedAddonUsages()
deleteJaloIfOnlyManagerExists()

def replaceAddonName() {
	ant.replace(dir: ctx.EXTENSION_DIRECTORY, token: ctx.ADDON_NAME, value: ctx.EXTENSION_NAME, encoding: 'UTF-8')
}

def replaceYcommercewebservices() {
	ant.replace(dir: ctx.EXTENSION_DIRECTORY, token: 'ycommercewebservices', value: 'commercewebservices', encoding: 'UTF-8')
	ant.replace(dir: ctx.EXTENSION_DIRECTORY, token: 'de.hybris.platform.commercewebservices.', value: 'de.hybris.platform.commercewebservices.core.', encoding: 'UTF-8')
}

def replaceAllCapitalizedAddonUsages() {
	ant.replace(dir: ctx.EXTENSION_DIRECTORY, token: ctx.ADDON_NAME.capitalize(), value: ctx.EXTENSION_NAME.capitalize(), encoding: 'UTF-8')
}

def deleteJaloIfOnlyManagerExists() {
	def newExtExtensionInfoFile = new XmlParser().parseText(new File("${ctx.EXTENSION_DIRECTORY}/extensioninfo.xml").getText('UTF-8'))
	def extensionPackage = newExtExtensionInfoFile.extension.coremodule.@packageroot[0].replaceAll('\\.', '/')
	def occAddonManagerFileName = "${ctx.EXTENSION_NAME.capitalize()}Manager.java"

	if (ScriptUtils.isDirectory("${ctx.EXTENSION_DIRECTORY}/src/$extensionPackage/jalo/$occAddonManagerFileName")) {
		def jaloDirectory = new File("${ctx.EXTENSION_DIRECTORY}/src/$extensionPackage/jalo")
		def jaloPackageFileList = []

		jaloDirectory.traverse(type: FILES, maxDepth: 0) {
			jaloPackageFileList.add(it)
		}
		if (jaloPackageFileList.size() == 1) {
			ant.delete(dir: jaloDirectory)
		}
	}
}
