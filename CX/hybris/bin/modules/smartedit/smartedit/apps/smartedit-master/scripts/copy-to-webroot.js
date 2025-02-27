/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
const fs = require('fs-extra');

const args = process.argv.slice(2);

const hasArgument = (expectedArg) => {
    return args.some((arg) => {
        return arg == expectedArg;
    });
};

const copyCKEditor = () => {
    console.log('Copying CKEditor sources to webroot');
    fs.copySync(
        './node_modules/ckeditor4',
        '../../web/webroot/static-resources/thirdparties/ckeditor',
        {
            overwrite: true,
            dereference: true
        }
    );
    // remove ckeditor4 samples due to CVE-2024-24816
    fs.removeSync('./node_modules/ckeditor4/samples');
    fs.removeSync('../../web/webroot/static-resources/thirdparties/ckeditor/samples');
};

if (hasArgument('--inner')) {
    console.log('Copying generated inner frame sources to webroot');
    fs.copySync('./dist/smartedit', '../../web/webroot/static-resources/dist/smartedit-new', {
        overwrite: true
    });
}

if (hasArgument('--container')) {
    console.log('Copying generated container sources to webroot');
    fs.copySync(
        './dist/smartedit-container',
        '../../web/webroot/static-resources/dist/smartedit-container-new',
        {
            overwrite: true
        }
    );
}

copyCKEditor();
