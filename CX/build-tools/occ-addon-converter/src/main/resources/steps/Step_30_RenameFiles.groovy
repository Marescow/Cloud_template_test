/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
renameFilenamesWithAddonName()
renameCapitalizedFilenamesWithAddonName()

def renameFilenamesWithAddonName() {
	ant.move(todir: ctx.EXTENSION_DIRECTORY, includeemptydirs: true, verbose: true) {
		ant.fileset(dir: ctx.EXTENSION_DIRECTORY)
		ant.regexpmapper(from: "^(.*)${ctx.ADDON_NAME}(.*)\$\$", to: "\\1${ctx.EXTENSION_NAME}\\2")
	}
}

def renameCapitalizedFilenamesWithAddonName() {
	ant.move(todir: ctx.EXTENSION_DIRECTORY, includeemptydirs: true, verbose: true) {
		ant.fileset(dir: ctx.EXTENSION_DIRECTORY)
		ant.regexpmapper(from: "^(.*)${ctx.ADDON_NAME.capitalize()}(.*)\$\$", to: "\\1${ctx.EXTENSION_NAME.capitalize()}\\2")
	}
}
