
hybris Changes
=============================

This is a modified version of Solr.

The following files/directories were removed:
- docs
- example

The following files/directories were added:
- HYBRIS_README.txt
- modules/hybris
- server/solr/security.json.example
- server/solr/solr.p12
- server/solr/solr_client.p12
- server/etc/zookeepercredentials.properties

The following files/directories were modified/replaced:
- bin/solr.cmd:
	- workaround for https://issues.apache.org/jira/browse/SOLR-7283 (lines 19-20)
	- workaround for https://issues.apache.org/jira/browse/SOLR-17457 (line 1463-1467)
- bin/solr.in.cmd:
	- authentication and ssl configuration example (lines 267-290)
	- remove UseLargePages parameter from GC_TUNE parameters due to problems with running solr within docker container (line 292)
	- disable java security manager - starting from solr 9.0.0 it is enabled by default (line 294)
	- disable request logging - starting from solr 9.0.0 it is enabled by default (line 296)
	- configuration of zookeeper ACL - default settings without ACLs (lines 298-307)
	- replica placement plugin configuration (lines 309-310)
	- disable solr.cluster.plugin.edit.enabled flag which is needed for solr.xml replica placement plugin configuration (lines 312-314)
- bin/solr.in.sh:
	- authentication and ssl configuration example (lines 306-329)
	- remove UseLargePages parameter from GC_TUNE parameters due to problems with running solr within docker container (line 331)
	- disable java security manager - starting from solr 9.0.0 it is enabled by default (line 333)
	- disable request logging - starting from solr 9.0.0 it is enabled by default (line 335)
	- configuration of zookeeper ACL - default settings without ACLs (lines 337-348)
	- default replica placement plugin configuration (lines 350-351)
	- disable solr.cluster.plugin.edit.enabled flag which needed for solr.xml replica placement plugin configuration (lines 353-355)
- server/resources/log4j2.xml:
	- change log level for zookeeper to error following changes done as a part of https://issues.apache.org/jira/browse/SOLR-15968 (68-69)
- server/scripts/cloud-scripts/zkcli.bat:
    - configuration of zookeeper ACL - default settings without ACLs (lines 27-38)
- server/scripts/cloud-scripts/zkcli.sh:
    - configuration of zookeeper ACL - default settings without ACLs (lines 29-40)
- server/solr/solr.xml
- server/solr/configsets
- hadoop-client-runtime library (due to https://nvd.nist.gov/vuln/detail/CVE-2023-39410 )
    - licenses/hadoop-client-runtime-3.3.6.jar.sha1 -> licenses/hadoop-client-runtime-3.4.0.jar.sha1
    - modules/hdfs/lib/hadoop-client-runtime-3.3.6.jar -> modules/hdfs/lib/hadoop-client-runtime-3.4.0.jar
- zookeeper libraries (due to https://nvd.nist.gov/vuln/detail/CVE-2024-51504)
    - licenses/zookeeper-3.9.2.jar.sha1 -> licenses/zookeeper-3.9.3.jar.sha1
    - licenses/zookeeper-3.9.2-tests.jar.sha1 -> licenses/zookeeper-3.9.3-tests.jar.sha1
    - licenses/zookeeper-jute-3.9.2.jar.sha1 -> licenses/zookeeper-jute-3.9.3.jar.sha1
    - server/solr-webapp/webapp/WEB-INF/lib/zookeeper-3.9.2.jar -> server/solr-webapp/webapp/WEB-INF/lib/zookeeper-3.9.3.jar
    - server/solr-webapp/webapp/WEB-INF/lib/zookeeper-jute-3.9.2.jar -> server/solr-webapp/webapp/WEB-INF/lib/zookeeper-jute-3.9.3.jar
- protobuf-java libraries (due to https://nvd.nist.gov/vuln/detail/CVE-2024-7254)
	- licenses/protobuf-java-3.25.3.jar.sha1 -> licenses/protobuf-java-3.25.5.jar.sha1
	- modules/gcs-repository/lib/protobuf-java-3.25.3.jar -> modules/gcs-repository/lib/protobuf-java-3.25.5.jar
	- modules/opentelemetry/lib/protobuf-java-3.25.3.jar -> modules/opentelemetry/lib/protobuf-java-3.25.5.jar
	- modules/sql/lib/protobuf-java-3.25.3.jar -> modules/sql/lib/protobuf-java-3.25.5.jar
	- licenses/protobuf-java-util-3.25.3.jar.sha1 -> licenses/protobuf-java-util-3.25.5.jar.sha1
	- modules/gcs-repository/lib/protobuf-java-util-3.25.3.jar -> modules/gcs-repository/lib/protobuf-java-util-3.25.5.jar
