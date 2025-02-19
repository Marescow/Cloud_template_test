/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
def directoriesToCreate = [
		'src/',
		'testsrc/',
		'resources/',
		"resources/$ctx.EXTENSION_NAME",
		'resources/impex/',
		'resources/localization/',
		"resources/$ctx.RESOURCE_EXT_DIR",
		"resources/$ctx.RESOURCE_EXT_DIR/cache/",
		"resources/$ctx.RESOURCE_EXT_DIR/messages/",
		"resources/$ctx.RESOURCE_EXT_DIR/web/spring/",
]

ant.echo("Attempting to create new directory structure in $ctx.EXTENSION_NAME")

directoriesToCreate.each {
	ant.mkdir(dir: ctx.EXTENSION_DIRECTORY.concat(it))
}
