# How to generate project from archetype

### Download the archetype on local and install it


> mvn install

### Use below command to generate project from Archetype.

>  mvn archetype:generate -DarchetypeGroupId=com.techm.bm.messaging
  -DarchetypeArtifactId=bm-activemq-project-archetype
  -DarchetypeVersion=1.0


### Give groupId, artifactId and version for the project you want to create in interactive mode.

```

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
 [INFO] Using following parameters for creating project from Archetype:  bm-activemq-project-archetype:1.0
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
```

### Required project from archetype will be generated.

_Delete .settings, .project and .classpath before you import it in eclipse or any other IDE._

# How to use generated project

__application.properties__** contains following important properties which are to be defined in order to use the project in **point-to-point or **publisher-subscriber mode.

```
#activemq broker props
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin

#2 valid values p2p, pubsub
activemq.mode=p2p

# valid values true and false:
# for activemq.mode=p2p, keep this false
# for activemq.mode=pubsub keep this true
spring.jms.pub-sub-domain=false

# sender queue name
sender-queue=sender-queue
# receiver queue name
receiver-queue=sender-queue

# multiple topics can be created.
#Give different topic names to publish and subscribe on different topics.
topic.name1=myTopic
topic.name2=myTopic

# session cache size
session-cache-size=10
```
  This property acts as a switch to toggle behaviour from p2p to pub sub.
> activemq.mode=p2p

  This property is false for p2p mode and true for pubsub mode. When true, it allows subscription by multiple Subscribers.
> spring.jms.pub-sub-domain=false

  For ease queue and topic names are taken similar as producer and consumer are working on same broker(queue and topic).
  If required these can be changed to any suitable name to achieve deployment as separate application.
  
  
  ### _Please feel free to delete unused code in your generated code as per usecase._