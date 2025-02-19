/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
import org.apache.tools.ant.BuildException

def extensionSpringXmlAsText = new File("$ctx.EXTENSION_DIRECTORY/resources/$ctx.EXTENSION_NAME-spring.xml").getText('UTF-8')
if (extensionSpringXmlAsText.contains('abstractCoreDataImportedEventListener') || extensionSpringXmlAsText.contains('abstractGenericAddOnSampleDataEventListener')) {
	throw new BuildException("Beans related to old cron jobs logic were found in $ctx.EXTENSION_NAME-spring.xml file, aborting.")
}

def extensionInfoAsText = new File("${ctx.EXTENSION_DIRECTORY}/extensioninfo.xml").getText('UTF-8')
if (extensionInfoAsText.contains('addonsupport')) {
	throw new BuildException('addonsupport is included in the extensioninfo.xml, aborting.')
}

if (extensionInfoAsText.contains('"commercewebservicescommons"')) {
	throw new BuildException('OCC extension cannot depend on commercewebservicescommons, aborting.')
}

if (new File("${ctx.EXTENSION_DIRECTORY}/.classpath").getText('UTF-8').contains('path="acceleratoraddon/web/src"')) {
	throw new BuildException('.classpath file should not contain reference to "acceleratoraddon/web/src" since in OCC extensions it should not exist.')
}

if (new File("${ctx.EXTENSION_DIRECTORY}/.classpath").getText('UTF-8').contains('path="acceleratoraddon/web/webroot/WEB-INF/messages"')) {
	throw new BuildException('.classpath file should not contain reference to "acceleratoraddon/web/webroot/WEB-INF/messages" since in OCC extensions messages should be in "resources/occ/v2/${ctx.EXTENSION_NAME}/".')
}
