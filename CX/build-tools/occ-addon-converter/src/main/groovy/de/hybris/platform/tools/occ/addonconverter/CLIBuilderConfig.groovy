/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import groovy.cli.picocli.CliBuilder

class CLIBuilderConfig {
	static CliBuilder getCliBuilderInstance() {
		def cliBuilder = new CliBuilder(
				usage: '''
					java -jar occ-addon-converter.jar \\
					--addondir /Users/user/mywebservicesaddon/ \\
					--extdir /Users/user/mywebservicesocc/ #has to end with "occ"\\
					--stepsdir /Users/user/customsteps/ #optional
				'''.stripIndent(),
				header: 'Options:',
		)
		cliBuilder.with {
			h longOpt: 'help', 'Prints help text.'
			addondir(longOpt: 'addondir', args: 1, argName: 'ADDON_DIR',
					'Sets the directory where addon is located')
			extdir(longOpt: 'extdir', args: 1, argName: 'EXT_DIR',
					'Sets the directory where the new extension is located, folder\'s name is the extension name which has to end with "occ".')
			stepsdir(longOpt: 'stepsdir', args: 1, argName: 'STEPS_DIR',
					'(Optional) Sets the directory where custom steps are located')
		}
		return cliBuilder
	}
}
