/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import static de.hybris.platform.tools.occ.addonconverter.ResourceUtils.getFileContent
import static de.hybris.platform.tools.occ.addonconverter.PluginManager.getSteps
import groovy.ant.AntBuilder

static void executeScript(
		String addonDirectory,
		String extensionDirectory,
		String customStepsDirectory
) {
	ScriptUtils.cleanDirectory(extensionDirectory)
	setupConverterContext(addonDirectory, extensionDirectory)
	def steps = getSteps(customStepsDirectory)
	println 'Steps selected for the execution:'
	steps.each {
		println it.name
	}
	steps.each {
		ScriptUtils.logStepExecution(it.name)
		executeStep(it)
	}
}

private static void setupConverterContext(String addonDirectory, String extensionDirectory) {
	def addonName = ScriptUtils.getExtensionName(addonDirectory)
	def extensionName = new File(extensionDirectory).name

	ConverterContext.initConverterContext(addonName, addonDirectory, extensionName, extensionDirectory)
	if (!ConverterContext.converterContextValid) {
		throw new VerifyError('''
							There was some problem with field validation, \\
							correct the arguments and try again. \\
							Remember to verify if provided paths indeed exist. \\
							'''.stripIndent()
		)
	}
}

private static executeStep(File file) {
	def binding = new Binding([ctx: ConverterContext.instance, ant: new AntBuilder()])
	def shell = new GroovyShell(this.class.classLoader, binding)
	shell.parse(getFileContent(file)).run()
}
