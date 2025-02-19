/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import static de.hybris.platform.tools.occ.addonconverter.ScriptUtils.isDirectory

@Singleton
class ConverterContext {
	String ADDON_NAME
	String ADDON_DIRECTORY
	String EXTENSION_NAME
	String EXTENSION_DIRECTORY
	String RESOURCE_EXT_DIR

	static void initConverterContext(
			String addonName,
			String addonDirectory,
			String extensionName,
			String extensionDirectory
	) {
		def ctx = this.instance
		ctx.ADDON_DIRECTORY = addonDirectory.concat(addonDirectory.endsWith('/') ? '' : '/')
		ctx.EXTENSION_DIRECTORY = extensionDirectory.concat(extensionDirectory.endsWith('/') ? '' : '/')
		ctx.ADDON_NAME = addonName
		ctx.EXTENSION_NAME = extensionName
		ctx.RESOURCE_EXT_DIR = 'occ/v2/' + extensionName + '/'
	}

	static boolean isConverterContextValid() {
		def ctx = this.instance
		if (!ctx.EXTENSION_NAME.endsWith('occ')) {
			throw new IllegalArgumentException('Extension name has to end with occ')
		}
		return isDirectory(ctx.ADDON_DIRECTORY) && isDirectory(ctx.EXTENSION_DIRECTORY)
	}
}
