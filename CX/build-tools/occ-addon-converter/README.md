<h1 align="center">OCC AddOn Converter</h1>

> A script that automates the process of converting OCC AddOns to OCC Extensions.

## Prerequisites

- [JDK 17](https://help.sap.com/viewer/a74589c3a81a4a95bf51d87258c0ab15/latest/en-US/4e00fd71f0dd4dcab248f022a8c98d3d.html)
- OCC AddOns that you wish to convert

## Running the script

You can use the script directly from the source code or run it as a packed JAR file. The code snippets below show how to convert example modules, where xyoccaddon is the name of the OCC AddOn and xyzocc is the name of the new OCC Extension. The names of OCC Extensions need to end with "occ".

### Using OCC AddOn Converter as a JAR file:

For Microsoft Windows systems:
```bat
gradlew.bat jar;
 
java -jar build\libs\occ-addon-converter.jar ^
--addondir "C:\Projects\xyoccaddon" ^
--extdir "C:\Projects\xyzocc" ^
--stepsdir "C:\Projects\customSteps" & :: stepsdir is optional
```
For Unix-related systems (such as Linux or Mac OS): 
```shell script
./gradlew jar;
 
java -jar build/libs/occ-addon-converter.jar \
--addondir "/Projects/xyoccaddon" \
--extdir "/Projects/xyzocc" \
--stepsdir "/Projects/customSteps" # stepsdir is optional
```
### Using OCC AddOn Converter from the source code:

For Microsoft Windows systems:
```bat
gradlew.bat run --args="--addondir ""C:\Projects\xyoccaddon"" --extdir ""C:\Projects\xyzocc"" --stepsdir ""C:\Projects\customSteps"""
```
For Unix-related systems (such as Linux or Mac OS):
```shell script
./gradlew run --args="--addondir \"/Projects/xyoccaddon\" --extdir \"/Projects/xyzocc\" --stepsdir \"/Projects/customSteps\""
```

Where:
- `addondir` is the directory of the OCC AddOn.
- `extdir` is the directory where the new OCC Extension is located after the conversion. The folder is erased each time OCC AddOn Converter is used. The folder's name (in this case xyzocc) is the OCC Extension's name. 
- `stepsdir` is an optional field specifying the directory with custom steps that can be used to apply additional changes.

---

More information on the occ-addon-converter tool can be found in the [SAP Help Portal](https://help.sap.com/viewer/9d346683b0084da2938be8a285c0c27a/latest/en-US/f7b37faa192d4110bffa10530f1c9e36.html).
