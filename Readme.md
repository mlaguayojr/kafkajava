# Kafka, Zookeeper, Java
This little demo shows how to integrate Kafka, Zookeper, and Java.

# What is Kafka?
"Apache Kafka is a distributed streaming platform. " -- [Kafka](https://kafka.apache.org/)

In a very generic way, Kafka is a very smart streaming system with various use cases. Kafka has four core APIs:
* Producer
* Consumer
* Streams
* Connector

This demo only covers the *Producer* and the *Consumer* APIs. The Producer API allows an application to write entries to a topic. The Consumer API allows an application to read those records based on the topics subscribed to. Consumers cannot communicate with Producers, Consumers can only listen to what the Producers are sending to the subscribed topic.

Ideally, Kafka is best used in a clustered nature.

## Topics
The category/container/collection of records (think of Hashtags in Twitter, or a sub-reddit). A Topic does not depend on Consumers, meaning a Topic can live without Consumers listening to it.

Kafka partitions topics into a structured log of records. Each partition is an ordered (sequential numbers), as-is, file where new messages are appended to. Each record are uniquely identified by their *offset*.

## Kafka's Example Use Cases of Producer and Consumer
* Messaging
* Website Activity Tracking
* Metrics / Big Data Analysis
* Log Aggregation
* Stream Processing
* Event Sourcing
* Commit Log

## Why Kafka?
* Realtime processing of records
* Distributed Partitions of records
* Low-latency
* Versatile

All in all, if you have a project that deals with big data or a collection of information and you want a method of organizing, or pruning, Kafka can be a resource if your data comes from varying sources/environments.

# How about Zookeeper?
Zookeeper is the backend service / container for Kafka. With Zookeeper, Kafka will have a Leader-Follower architecture making synchronization easy for clusters.

# Installation
I'm not going to lie - go grab a cup of coffee -- this will take a while. This is also being done through Windows -- if you're on Linux, it's a lot easier...

## Dependencies
* [Gradle](https://gradle.org/install/)
* [Kafka Source](https://kafka.apache.org/downloads)
* [Zookeeper Source](http://zookeeper.apache.org/releases.html#download)

### Gradle
Go ahead and follow the instructions for installing Gradle [here](https://gradle.org/install/#manually)

### Kafka and Zookeeper
Download and Extract Kafka and Zookeeper each to the root of your harddrive for easy access.

Make sure each of these have an entry in PATH variable:
* C:\gradle\bin
* C:\zookeeper\bin

Add "JAVA_HOME" to your System Variables section with the value "C:\Progra~1\Java\jdk-10.0.1". (Zookeeper uses this)

### Compile Kafka
In Command Prompt (*This will take a while*):
```
cd C:\kafka
mkdir jars
gradle jarAll
for /r %i in (*.jar) do xcopy /Y "%i" jars
```
Running the last command will prompt you whether "jars" is a file or a directory. Enter D for directory and hit enter. This will find all the jars that gradle compiled and move them to the "jars" folder that you just made.

## Configure
Before we test the Producer and Consumer, we need to create our topic first. In Command Prompt:
```
cd C:\kafka
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic "JavaTest"
```

## Start Zookeeper Server
In command prompt:
```
zkserver
```

## Start Kafka Server
In command prompt:
```
cd C:\kafka
bin\windows\kafka-server-start.bat config\server.properties
```

## Inside Eclipse
1. Create a new Java Project
2. Navigate to your project's folder in the "Package Explorer" pane.
3. Right-click your project
4. Click "Properties"
5. Navigate to the "Libraries" tab under "Java Build Path".
6. Click the "Add External JARS..." button
7. Navigate to "C:\kafka\jars" and import all the jar files
8. Click the "Apply and Close" button

Make sure that DemoProducer and DemoConsumer are using the same topic "JavaTest" (or whichever topic you decided to create).

# Resources
* [What is Kafka and Why is popular?](https://techbeacon.com/what-apache-kafka-why-it-so-popular-should-you-use-it)
* [Apache ZooKeeper](https://zookeeper.apache.org/)
* [Apache Kafka](https://kafka.apache.org/)
* [Introduction to Apache Kafka by James Ward (Youtube Video)](https://www.youtube.com/watch?v=UEg40Te8pnE)
* [Lessons learned from Kafka in production by Tim Berglund of Confluent (Youtube video](https://www.youtube.com/watch?v=1vLMuWsfMcA)
* [What is Zookeeper?](https://www.youtube.com/watch?v=Kgf9EjTNucM)
* [xcopy command](https://superuser.com/a/653928)
