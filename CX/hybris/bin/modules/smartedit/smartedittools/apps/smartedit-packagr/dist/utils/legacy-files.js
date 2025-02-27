"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.bundleAngularJSTemplates = exports.readTemplateFiles = exports.findJavaScriptFiles = void 0;
/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
const readFile_1 = require("./readFile");
const path_1 = require("./path");
const html_1 = require("./html");
const fg = require('fast-glob');
const path = require('path');
const escapeQuotes = (html) => {
    return html.replace(/"/g, '\\"').replace(/\n/g, '');
};
const compileTemplateFile = async (filePath, includePathInName) => {
    const rawHtml = await readFile_1.readFile(filePath);
    const miniHtml = await html_1.minifyHtml(rawHtml);
    const html = escapeQuotes(miniHtml);
    const templateName = includePathInName ? filePath : path.basename(filePath);
    const content = `
    $templateCache.put(
        "${templateName}", 
        "${html}"
    );
    `;
    return {
        name: templateName,
        filePath,
        content
    };
};
/**
 *
 * @param patterns
 */
const findJavaScriptFiles = (patterns) => {
    return fg(patterns, {
        cwd: path_1.resolveBasePath(),
        absolute: true
    });
};
exports.findJavaScriptFiles = findJavaScriptFiles;
/**
 *
 * @param patterns
 */
const readTemplateFiles = async (patterns, includePathInName) => {
    const matchedFiles = await fg(patterns, {
        cwd: path_1.resolveBasePath()
    });
    return Promise.all(matchedFiles.map((filePath) => compileTemplateFile(filePath, includePathInName)));
};
exports.readTemplateFiles = readTemplateFiles;
/**
 *
 * @param moduleName The name of the module to use in angularJS for the concatenated templates
 * @param templates An array containing
 */
const bundleAngularJSTemplates = (moduleName, templates) => {
    const concatenatedTemplates = templates.reduce((acc, currentValue) => {
        return acc + ' ' + currentValue.content;
    }, '');
    return `
    (function(){
      "use strict";
      var angular = angular || window.angular;
      var SE_NG_TEMPLATE_MODULE = null;
      
      try {
        SE_NG_TEMPLATE_MODULE = angular.module('${moduleName}')
      } catch (err) {}
      SE_NG_TEMPLATE_MODULE = SE_NG_TEMPLATE_MODULE || angular.module('${moduleName}', []);
      SE_NG_TEMPLATE_MODULE.run(['$templateCache', function($templateCache) {
        'use strict';
        ${concatenatedTemplates}
      }]);
    })();
    `;
};
exports.bundleAngularJSTemplates = bundleAngularJSTemplates;
