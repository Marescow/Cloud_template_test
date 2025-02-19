/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.tools.occ.addonconverter

import static de.hybris.platform.tools.occ.addonconverter.ResourceUtils.listFiles
import static de.hybris.platform.tools.occ.addonconverter.ResourceUtils.listFilesFromResources

class PluginManager {

	static List<File> getSteps(String customStepsDirectory) {
		return (defaultStepFiles + getCustomStepFiles(customStepsDirectory)).sort { it.name }
	}

	private static List<File> getDefaultStepFiles() {
		return listFilesFromResources('/steps')
				.findAll { it.name.endsWith('.groovy') }
	}

	private static List<File> getCustomStepFiles(String customStepsDirectory) {
		return customStepsDirectory
				? listFiles(customStepsDirectory, '.groovy')
				: List.<File> of()
	}
}
