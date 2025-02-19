/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */

import org.apache.tools.ant.BuildException
import de.hybris.platform.tools.occ.addonconverter.ScriptUtils

copyXmlResourcesFiles()
copyDirectories()
copyRootFiles()
copyPropertiesTemplate()

def copyXmlResourcesFiles() {
	ant.copy(todir: "${ctx.EXTENSION_DIRECTORY}/resources/") {
		ant.fileset(dir: "${ctx.ADDON_DIRECTORY}/resources/") {
			ant.include(name: '*.xml')
		}
	}
}

def copyDirectories() {
	def directoriesToCopy = [
			// 'source'                                     : 'target'
			'src/'                                          : 'src/',
			'acceleratoraddon/web/src/'                     : 'src/',
			'testsrc/'                                      : 'testsrc/',
			'acceleratoraddon/web/testsrc/'                 : 'testsrc/',
			'resources/localization/'                       : 'resources/localization/',
			'acceleratoraddon/web/webroot/WEB-INF/messages/': "resources/${ctx.RESOURCE_EXT_DIR}messages/",
			("resources/${ctx.ADDON_NAME}/web/spring/")     : "resources/${ctx.RESOURCE_EXT_DIR}web/spring/",
	]

	directoriesToCopy.each { sourceDirectory, targetDirectory ->
		try {
			ScriptUtils.validateFilesDuplicate(new File(ctx.ADDON_DIRECTORY + sourceDirectory), new File(ctx.EXTENSION_DIRECTORY + targetDirectory))
			ant.copy(todir: ctx.EXTENSION_DIRECTORY + targetDirectory) {
				ant.fileset(dir: ctx.ADDON_DIRECTORY + sourceDirectory)
			}
		} catch (FileNotFoundException e) {
			ant.echo("Directory not found: $e.message")
		} catch (BuildException e) {
			ant.echo("Problem occured when merging directories: $e.message")
			throw e
		}
	}
}

def copyRootFiles() {
	def optionalRootFilesToCopy = [
			'external-dependencies.xml',
			'buildcallbacks.xml',
			'.classpath',
			'.project',
			'.externalToolBuilders',
			'.springBeans',
			'project.properties',
			'lib/',
			'.settings/',
			'.externalToolBuilders/',
	]

	optionalRootFilesToCopy.each { fileName ->
			if (new File(ctx.ADDON_DIRECTORY + fileName).file) {
				ant.copy(file: ctx.ADDON_DIRECTORY + fileName,
						todir: ctx.EXTENSION_DIRECTORY,
						overwrite: true)
			}
			if (new File(ctx.ADDON_DIRECTORY + fileName).directory) {
				ant.copy(todir: ctx.EXTENSION_DIRECTORY + fileName) {
					ant.fileset(dir: ctx.ADDON_DIRECTORY + fileName)
				}
			}
	}
	ant.copy(file: "${ctx.ADDON_DIRECTORY}/extensioninfo.xml",
			todir: ctx.EXTENSION_DIRECTORY,
			overwrite: true)
}

def copyPropertiesTemplate() {
	ant.copy(file: "${ctx.ADDON_DIRECTORY}/project.properties.template",
			tofile: "${ctx.EXTENSION_DIRECTORY}/project.properties",
			overwrite: true)
}
