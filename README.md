###Download the archetype on local and install it

>mvn install

###Use below command to generate project from Archetype.

>mvn archetype:generate -DarchetypeGroupId=com.techm.bm.messaging -DarchetypeArtifactId=bm-activemq-project-archetype -DarchetypeVersion=1.0

Give groupId, artifactId and version for the project you want to create in interactive mode.

E.g.
========================== Console Output ==============================================================
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] >>> maven-archetype-plugin:3.0.1:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO]
[INFO] <<< maven-archetype-plugin:3.0.1:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO]
[INFO]
[INFO] --- maven-archetype-plugin:3.0.1:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] Archetype repository not defined. Using the one from [com.techm.bm.messaging:bm-activemq-project-archetype:1.0] found in catalog local
Define value for property 'groupId': com.bm.messaging.amq
Define value for property 'artifactId':bm-activemq-topic
Define value for property 'version' 1.0-SNAPSHOT: : 1.0
Define value for property 'package' com.bm.messaging.amq: :
Confirm properties configuration:
groupId: com.bm.messaging.amq
artifactId: bm-activemq-topic
version: 1.0
package: com.bm.messaging.amq
 Y: : y
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: bm-activemq-project-archetype:1.0
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: com.bm.messaging.amq
[INFO] Parameter: artifactId, Value: bm-activemq-topic
[INFO] Parameter: version, Value: 1.0
[INFO] Parameter: package, Value: com.bm.messaging.amq
[INFO] Parameter: packageInPathFormat, Value: com/bm/messaging/amq
[INFO] Parameter: package, Value: com.bm.messaging.amq
[INFO] Parameter: version, Value: 1.0
[INFO] Parameter: groupId, Value: com.bm.messaging.amq
[INFO] Parameter: artifactId, Value: bm-activemq-topic
[INFO] Project created from Archetype in dir: C:\gitrepo\bm-activemq-topic
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 01:28 min
[INFO] Finished at: 2019-04-18T11:33:06+05:30
[INFO] Final Memory: 15M/148M
[INFO] ------------------------------------------------------------------------


Required project from archetype will be generated.

Delete .settings, .project and .classpath before you import it in eclipse or any other IDE.