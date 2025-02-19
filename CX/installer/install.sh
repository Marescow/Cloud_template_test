#!/bin/sh
SCRIPT_PATH=$(dirname $0)
chmod +x "${SCRIPT_PATH}/gradlew"

if [ -x "$(command -v java)" ]; then
  _java=java
elif [ -n "$JAVA_HOME" ]; then
  _java="$JAVA_HOME/bin/java"
else
  echo "Error: Neither PATH nor JAVA_HOME is set with Java path, please set it in your environment."
  exit 255
fi

EXPECTED_JAVA_MAJOR_VER=17

version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
if [ "$version" \< "${EXPECTED_JAVA_MAJOR_VER}" ]; then
  echo "Wrong java version is set - "$version", Installer requires at least java ${EXPECTED_JAVA_MAJOR_VER}"
  exit 255
fi
export INSTALLER_WORKING_DIR="${SCRIPT_PATH}"

java -classpath "${SCRIPT_PATH}/libs/installer-cli-22.05.16-RC1.jar:${SCRIPT_PATH}/libs/commons-cli-1.2.jar:${SCRIPT_PATH}/libs/groovy-ant-3.0.21.jar:${SCRIPT_PATH}/libs/commons-lang-2.6.jar:${SCRIPT_PATH}/libs/groovy-test-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-astbuilder-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-cli-picocli-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-groovysh-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-console-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-datetime-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-groovydoc-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-docgenerator-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-jmx-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-json-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-jsr223-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-macro-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-nio-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-servlet-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-sql-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-swing-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-templates-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-test-junit5-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-xml-3.0.21.jar:${SCRIPT_PATH}/libs/groovy-3.0.21.jar:" de.hybris.installer.CmdHandler "$@"