/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
'use strict';
/**
 * Rename from pnpmfile.js to .pnpmfile.cjs when bump pnpm to 6.x
 * https://github.com/microsoft/rushstack/issues/1254#issuecomment-882050826
 */

/**
 * When using the PNPM package manager, you can use pnpmfile.js to workaround
 * dependencies that have mistakes in their package.json file.  (This feature is
 * functionally similar to Yarn's "resolutions".)
 *
 * For details, see the PNPM documentation:
 * https://pnpm.js.org/docs/en/hooks.html
 *
 * IMPORTANT: SINCE THIS FILE CONTAINS EXECUTABLE CODE, MODIFYING IT IS LIKELY TO INVALIDATE
 * ANY CACHED DEPENDENCY ANALYSIS.  After any modification to pnpmfile.js, it's recommended to run
 * "rush update --full" so that PNPM will recalculate all version selections.
 */
module.exports = {
    hooks: {
        readPackage
    }
};

/**
 * This hook is invoked during installation before a package's dependencies
 * are selected.
 * The `packageJson` parameter is the deserialized package.json
 * contents for the package that is about to be installed.
 * The `context` parameter provides a log() function.
 * The return value is the updated object.
 */
function readPackage(packageJson, context) {
    if (packageJson.dependencies.minimist) {
        context.log('Force minimist version to 1.2.8 because of CXEC-46767');
        packageJson.dependencies.minimist = '1.2.8';
    }

    return packageJson;
}
