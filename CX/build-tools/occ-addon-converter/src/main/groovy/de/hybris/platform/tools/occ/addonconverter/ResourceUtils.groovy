/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ResourceUtils {

	private static final String UNIX_SEPARATOR = '/'
	private static final String WINDOWS_SEPARATOR = '\\'

	static List<File> listFilesFromResources(String dirName) {
		def uri = ResourceUtils.class.getResource(dirName).toURI()
		def walk = { path ->
			Files.walk(path as Path, 1).withCloseable {
				it.collect { new File(it.toString()) }
			}
		}
		if (uri.scheme == 'jar') {
			return FileSystems.newFileSystem(uri, Collections.<String, Object> emptyMap()).withCloseable {
				return walk(it.getPath(dirName))
			}
		}
		return walk(Paths.get(uri))
	}

	static List<File> listFiles(String absolutePath, String fileExtension) {
		return new File(absolutePath)
				.listFiles()
				.findAll { it.name.endsWith(fileExtension) }
	}

	static String getFileContent(File file) {
		if (file.exists()) {
			return file.getText('UTF-8')
		}

		//Get file from JAR resources
		return getResourceFileContent(file.path)
	}

	static String getResourceFileContent(String path) {
		return ResourceUtils.classLoader.getResourceAsStream(normalizePath(path)).getText('UTF-8')
	}

	static String normalizePath(String path) {
		if (File.separator == WINDOWS_SEPARATOR) {
			return path.takeAfter(File.separator).replace(WINDOWS_SEPARATOR, UNIX_SEPARATOR)
		}
		return path.takeAfter(File.separator)
	}
}
