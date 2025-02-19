/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

def cliBuilder = CLIBuilderConfig.cliBuilderInstance
def options = cliBuilder.parse(args)

if (!options) {
	System.err << 'Error while parsing command-line options.\n'
	System.exit 1
} else if (args.length == 0) {
	println 'No values present, showing help section.'
	cliBuilder.usage()
} else if (options.h) {
	cliBuilder.usage()
	System.exit 0
} else if (options.addondir && options.extdir) {
	ScriptExecutor.executeScript(
			options.getProperty('addondir') as String,
			options.getProperty('extdir') as String,
			options.getProperty('stepsdir') ? options.getProperty('stepsdir') as String : null
	)
} else {
	println 'Error occurred while loading configuration from command line, please check your command and try again.'
	cliBuilder.usage()
	System.exit 128
}
