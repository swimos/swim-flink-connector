# <a href="https://www.swimos.org"><img src="https://docs.swimos.org/readme/breach-marlin-blue-wide.svg"></a> Swim Flink Connector Library&ensp;![version](https://img.shields.io/github/tag/swimos/swim.svg?label=version) [![license](https://img.shields.io/github/license/swimos/swim.svg?color=blue)](https://github.com/swimos/swim/blob/main/LICENSE)


This is a [Flink Connector](https://nightlies.apache.org/flink/flink-docs-master/docs/connectors/datastream/overview/) library. The Swim Flink Connector library
acts as a bridge between Flink and Swim applications. This library should be configured to send messages generated from
Flink jobs to a specified Swim application.


## Apache Flink

Apache Flink is an open source stream processing framework with powerful stream- and batch-processing capabilities.

Learn more about Flink at [https://flink.apache.org/](https://flink.apache.org/)


## Building the Swim Flink Connector from Source

### Prerequisites

- Install JDK 11
- Ensure that your `JAVA_HOME` environment variable points to the Java installation.
- Ensure that your `PATH` includes `$JAVA_HOME`.

### On Windows

```bat
$ .\gradlew.bat build
```

### On Linux or MacOS

```bash
$ ./gradlew build
```

The resulting jars can be found in the `build/libs`.


## Design and Implementation Details

### Swim Application Overview
Swim applications consist of interconnected, distributed objects, called Web Agents. Each Web Agent has URI address, like a REST endpoint.
But unlike RESTful Web Services, Web Agents are stateful, and accessed via streaming APIs.  Each Web Agent has a set of named lanes,
representing the properties and methods of the Web Agent. Lanes come in several varieties, corresponding to common data structures and access patterns.

To send a message to a specific Web Agent, external systems (like the Swim Flink Connector) need the following:
1. Host URI : A Swim Application has a Host URI (similar to a web server's host URI).
2. Web Agent URI: Web Agents are hosted in a Swim Application and have unique URIs (similar to a REST endpoint).
3. Lane URI: The name of the lane in the Web Agent.

For a detailed overview of Swim concepts please refer to [swimos.org](https://www.swimos.org/concepts/)


## Validating with the Test Application
The Swim Flink Connector library can be validated by sending data to the test application in the [test-app/](test-app) directory. This can
be done using the following steps

### Run the Test Application
The Test Application can be run from the Commandline or using a pre-built Docker Image. Using either of these methods, the
application will start up and bind to port 9001.

#### Use Commandline
Go to the [test-app/](test-app) directory and execute the following command on the commandline.  
For Linux/MacOS: `./gradlew run`  
For Windows: `.\gradlew.bat run`

#### Use Docker Image
Go to the [test-app/](test-app) directory and build the docker image.
Run the application using the following command: `docker run -p 9001:9001 test-app`

### Access the data from the Application
Data sent to the Web Agents in the Test Application from the Swim Flink Connector library can be accessed using HTTP APIs.
For example, if the `id` extracted from the message is "User_5" then the Swim Flink Connector Library will compute the agent URI to
be: `/agent/User_5`. The HTTP API to get the message from this agent will be  
`http://<host-name>:9001/agent/User_5?lane=latestData`

If the `id` extracted from the message is "User_7", then the HTTP API to get the message from this agent will be:  
`http://<host-name>:9001/agent/User_7?lane=latestData`

Here the `<host-name>` will be "localhost" if the Test Application is running on the same machine as the Swim Flink Connector library.
If the Test Application is running on a different machine then the `<host-name>` will be the Fully Qualified Domain Name of the machine.


## Repository Structure

### Key files

- [gradlew](gradlew)/[gradlew.bat](gradlew.bat) — gradle wrapper
- [build.gradle](build.gradle) — gradle file to build the library
- [gradle.properties](gradle.properties) — configuration variables

### Key directories

- [src](src) — source code
    - [main/java](src/main/java) — java source code
    - [main/resources](src/main/resources) — configuration files
    - [test](src/test)-unit test code
- [test-app](test-app) — test Swim application which maybe used to validate the swim-flink-connector library