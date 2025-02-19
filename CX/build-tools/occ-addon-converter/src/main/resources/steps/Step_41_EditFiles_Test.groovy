/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import static groovy.io.FileType.FILES
import org.apache.tools.ant.BuildException

new File(ctx.EXTENSION_DIRECTORY).eachFileRecurse(FILES) { file ->
	def text = file.getText('UTF-8')
	if (text.contains(ctx.ADDON_NAME)) {
		throw new BuildException("Files should not contain the addon name, in this case $ctx.ADDON_NAME, one was found in ${file.absolutePath}, aborting.")
	} else if (text.contains(ctx.ADDON_NAME.capitalize())) {
		throw new BuildException("Files should not contain the addon name, in this case $ctx.ADDON_NAME, one was found in ${file.absolutePath}, aborting.")
	} else if (text.contains('ycommercewebservices')) {
		throw new BuildException("Files should not contain references to ycommercewebservices, one was found in ${file.absolutePath}, aborting.")
	}
}
