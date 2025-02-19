/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import groovy.ant.AntBuilder
import groovy.io.FileType
import groovy.xml.XmlParser
import org.apache.tools.ant.BuildException

static String getXmlContentWithoutBean(xmlContent, beanName) {
	def indexOfBeanOccurrence = xmlContent.indexOf(beanName)
	def indexOfBeanStart = xmlContent.substring(0, indexOfBeanOccurrence).lastIndexOf('<bean')
	def indexOfNearestBeanEnd = xmlContent.indexOf('</bean>', indexOfBeanOccurrence)
	def countOfEmbeddedBeanStarts = xmlContent.substring(indexOfBeanOccurrence, indexOfNearestBeanEnd).count('<bean')

	while (countOfEmbeddedBeanStarts > 0) {
		indexOfNearestBeanEnd = xmlContent.indexOf('</bean>', indexOfNearestBeanEnd + '</bean>'.length())
		countOfEmbeddedBeanStarts--
	}

	def result = new StringBuilder(xmlContent.substring(0, indexOfBeanStart))
	result.append(xmlContent.substring(indexOfNearestBeanEnd + '</bean>'.length(), xmlContent.length()))

	if (result.contains(beanName)) {
		throw new BuildException("$beanName bean could not be removed from the Spring config file." +
				' See the Spring config file to examine what went wrong.')
	}
	return result
}

static boolean isDirectory(String dir) {
	def f = new File(dir)
	return f.exists() && f.directory
}

static void removeLineWithStringInFile(File file, String textInLineToDelete) {
	def updatedFileContent = []
	file.eachLine {
		line ->
			if (!line.contains(textInLineToDelete)) {
				updatedFileContent.add(line)
			}
	}
	file.withWriter { out ->
		updatedFileContent.each { out.println it }
	}
}

static String getExtensionName(String extensionPath) {
	def extensionInfoFile = new File(extensionPath + '/extensioninfo.xml')
	assert extensionInfoFile.file
	return new XmlParser().parseText(extensionInfoFile.getText('UTF-8')).extension.@name[0]
}

static void cleanDirectory(String extensionDirectory) {
	def ant = new AntBuilder()
	ant.delete(includeemptydirs: true) {
		ant.fileset(dir: extensionDirectory, includes: '**/*')
	}
}

static void logStepExecution(String fileName) {
	def fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'))
	assert !fileNameWithoutExt.endsWith('.groovy')
	def logMessage = "-> Executing: $fileNameWithoutExt"
	println logMessage
}

static void validateFilesDuplicate(File dir1, File dir2) throws BuildException {
	def files = [] as Set

	dir1.traverse (type: FileType.FILES) {
		String relativePath = it.absolutePath.replace(dir1.absolutePath, '')
		files.add(relativePath)
	}

	dir2.traverse (type: FileType.FILES) {
		String relativePath = it.absolutePath.replace(dir2.absolutePath, '')
		if (!files.add(relativePath)) {
			throw new BuildException("Duplicated files detected in: ${dir1.absolutePath + relativePath} and ${dir2.absolutePath + relativePath}")
		}
	}
}

