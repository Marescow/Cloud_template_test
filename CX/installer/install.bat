@echo off
SET INSTALLER_WORKING_DIR=%~dp0
SET EXPECTED_JAVA_MAJOR_VER=17

for /f tokens^=2-5^ delims^=.-_^" %%j in ('java -fullversion 2^>^&1') do set "jver=%%j%"

if %jver% LSS %EXPECTED_JAVA_MAJOR_VER% (
    echo "you are running unsupported version of java: %jver%. Required: %EXPECTED_JAVA_MAJOR_VER%"
) else (
    java -classpath ";%INSTALLER_WORKING_DIR:~0,-1%/libs/installer-cli-22.05.16-RC1.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/commons-cli-1.2.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-ant-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/commons-lang-2.6.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-test-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-astbuilder-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-cli-picocli-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-groovysh-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-console-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-datetime-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-groovydoc-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-docgenerator-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-jmx-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-json-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-jsr223-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-macro-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-nio-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-servlet-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-sql-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-swing-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-templates-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-test-junit5-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-xml-3.0.21.jar;%INSTALLER_WORKING_DIR:~0,-1%/libs/groovy-3.0.21.jar" de.hybris.installer.CmdHandler %*
)