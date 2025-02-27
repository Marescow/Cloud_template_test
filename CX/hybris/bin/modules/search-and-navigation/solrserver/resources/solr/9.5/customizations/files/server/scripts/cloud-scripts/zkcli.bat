@echo off
REM You can override pass the following parameters to this script:
REM 

set JVM=java

REM  Find location of this script

set SDIR=%~dp0
if "%SDIR:~-1%"=="\" set SDIR=%SDIR:~0,-1%

set "LOG4J_CONFIG=file:///%SDIR%\..\..\resources\log4j2-console.xml"

REM Settings for ZK ACL
REM set SOLR_ZK_CREDS_AND_ACLS=-DzkACLProvider=org.apache.solr.common.cloud.DigestZkACLProvider ^
REM  -DzkCredentialsProvider=org.apache.solr.common.cloud.DigestZkCredentialsProvider ^
REM  -DzkCredentialsInjector=org.apache.solr.common.cloud.VMParamsZkCredentialsInjector ^
REM  -DzkDigestUsername=admin-user -DzkDigestPassword=CHANGEME-ADMIN-PASSWORD ^
REM  -DzkDigestReadonlyUsername=readonly-user -DzkDigestReadonlyPassword=CHANGEME-READONLY-PASSWORD
REM optionally, you can use using a a Java properties file 'zkDigestCredentialsFile'
REM ...
REM   -DzkDigestCredentialsFile=/path/to/zkDigestCredentialsFile.properties
REM ...

REM hybris Changes - start
REM default - Settings without ZK ACL
set SOLR_ZK_CREDS_AND_ACLS=-DzkACLProvider=org.apache.solr.common.cloud.DefaultZkACLProvider ^
 -DzkCredentialsProvider=org.apache.solr.common.cloud.DefaultZkCredentialsProvider ^
 -DzkCredentialsInjector=org.apache.solr.common.cloud.DefaultZkCredentialsInjector

REM Settings for ZK ACL
REM set SOLR_ZK_CREDS_AND_ACLS=-DzkACLProvider=org.apache.solr.common.cloud.DigestZkACLProvider ^
REM  -DzkCredentialsProvider=org.apache.solr.common.cloud.DigestZkCredentialsProvider ^
REM  -DzkCredentialsInjector=org.apache.solr.common.cloud.VMParamsZkCredentialsInjector ^
REM  -DzkDigestCredentialsFile=%SDIR%\..\..\etc\zookeepercredentials.properties
REM hybris Changes - end

"%JVM%" %SOLR_ZK_CREDS_AND_ACLS% %ZKCLI_JVM_FLAGS% -Dlog4j.configurationFile="%LOG4J_CONFIG%" ^
-classpath "%SDIR%\..\..\solr-webapp\webapp\WEB-INF\lib\*;%SDIR%\..\..\lib\ext\*;%SDIR%\..\..\lib\*" org.apache.solr.cloud.ZkCLI %*
