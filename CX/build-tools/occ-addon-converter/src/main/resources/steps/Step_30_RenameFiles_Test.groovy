/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import org.apache.tools.ant.BuildException

new File(ctx.EXTENSION_DIRECTORY).eachFileRecurse { file ->
	if (file.name.contains(ctx.ADDON_NAME) || file.name.contains(ctx.ADDON_NAME.capitalize())) {
		throw new BuildException("File \"${file.absolutePath}\" with addon name ($ctx.ADDON_NAME) was found, aborting!")
	}
}
