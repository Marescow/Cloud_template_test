const fs = require('fs');
const path = require('path');
const fg = require('fast-glob');

// Define the path pattern to search for, including wildcards
const pattern = './chromedriver/**/chromedriver';

const targetDirectory = process.argv[2];

try {
    fs.mkdirSync(targetDirectory, {recursive: true});
} catch (err) {
    console.error(`fail to create chromeDriverBin directory: ${err}`)
    process.exit(1);
}


// Use the glob module to find matching files
const chromeDriverFiles = fg.sync(pattern);
if (chromeDriverFiles.length === 0) {
    console.error('No chromedriver file found')
    process.exit(1);
}
const file = chromeDriverFiles[0];
console.log(`Found the following matching files: ${file}`);
// Construct the target file path
const targetFilePath = path.join(targetDirectory, path.basename(file));

// Move the file to the target folder
fs.rename(file, targetFilePath, (moveErr) => {
    if (moveErr) {
        console.error(`Error while moving the file: ${moveErr}`);
    } else {
        console.log(`Successfully moved the file to ${targetFilePath}`);
    }
});
