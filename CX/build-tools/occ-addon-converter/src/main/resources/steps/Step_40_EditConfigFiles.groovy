/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import de.hybris.platform.tools.occ.addonconverter.ScriptUtils

removeListenerBeans()
editExtensionInfo()
removeRedundantProjectProperty()
adjustClassPathEclipseConfig()

def removeListenerBeans() {
	def charset = 'UTF-8'
	def springXmlFile = new File("${ctx.EXTENSION_DIRECTORY}/resources/$ctx.EXTENSION_NAME-spring.xml")
	def springXmlFileContent = springXmlFile.getText('UTF-8')
	if (!springXmlFileContent.contains('abstractCoreDataImportedEventListener') || !springXmlFileContent.contains('abstractGenericAddOnSampleDataEventListener')) {
		return
	}

	springXmlFileContent = ScriptUtils.getXmlContentWithoutBean(springXmlFileContent, 'abstractCoreDataImportedEventListener')
	springXmlFileContent = ScriptUtils.getXmlContentWithoutBean(springXmlFileContent, 'abstractGenericAddOnSampleDataEventListener')

	springXmlFile.withWriter(charset) { writer -> writer.write(springXmlFileContent) }
}

def editExtensionInfo() {
	ant.replaceregexp(file: "${ctx.EXTENSION_DIRECTORY}/extensioninfo.xml", byline: true, encoding: 'UTF-8') {
		ant.regexp(pattern: '<requires-extension name="addonsupport"/>')
		ant.substitution(expression: '')
	}

	ant.replaceregexp(file: "${ctx.EXTENSION_DIRECTORY}/extensioninfo.xml", byline: true, encoding: 'UTF-8') {
		ant.regexp(pattern: 'name="commercewebservicescommons"')
		ant.substitution(expression: 'name="commercewebservices"')
	}

	ant.replaceregexp(file: "${ctx.EXTENSION_DIRECTORY}/extensioninfo.xml", byline: true, encoding: 'UTF-8') {
		ant.regexp(pattern: '<meta key="deprecated" value="true"/>')
		ant.substitution(expression: '')
	}
}

def removeRedundantProjectProperty() {
	ant.replaceregexp(file: "${ctx.EXTENSION_DIRECTORY}/project.properties", byline: true, encoding: 'UTF-8') {
		ant.regexp(pattern: "^ycommercewebservices.additionalWebSpringConfigs.$ctx.ADDON_NAME.*")
		ant.substitution(expression: '')
	}
}

def adjustClassPathEclipseConfig() {
	def classPathFile = new File("${ctx.EXTENSION_DIRECTORY}/.classpath")
	def oldSrcDirectoryReference = 'path="acceleratoraddon/web/src"'
	if (!classPathFile.exists()) {
		return
	}
	if (classPathFile.getText('UTF-8').contains(oldSrcDirectoryReference)) {
		ScriptUtils.removeLineWithStringInFile(classPathFile, oldSrcDirectoryReference)
	}

	ant.replaceregexp(file: "${ctx.EXTENSION_DIRECTORY}/.classpath", byline: true, encoding: 'UTF-8') {
		ant.regexp(pattern: 'path="acceleratoraddon/web/webroot/WEB-INF/messages"')
		ant.substitution(expression: "path=\"resources/occ/v2/${ctx.EXTENSION_NAME}/messages\"")
	}
}
