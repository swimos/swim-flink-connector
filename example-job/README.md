# Example Flink Job

An example Flink job to be used for verifying the Swim Flink Connector.

## Getting Started

### Prerequisites

- Install JDK 11
- Ensure that your `JAVA_HOME` environment variable points to the Java installation.
- Ensure that your `PATH` includes `$JAVA_HOME`.

### Running the Application

#### On Windows

```bat
$ .\gradlew.bat run
```

#### On Linux or MacOS

```bash
$ ./gradlew run
```

This will start a Flink job that generates random user data and relays that to a Swim application
running on `localhost:9001`.

### Creating a Docker Package

#### On Windows

```bash
$ .\gradlew.bat shadowJar
```

#### On Linux or MacOS

```bash
$ ./gradlew shadowJar
```

This will create a jar with all required dependencies at `build/libs/example-job-4.0.1-all.jar`. This jar can be submitted as a job to 
a Flink cluster.


## Repository Structure

### Key files

- [gradlew](gradlew)/[gradlew.bat](gradlew.bat) — gradle wrapper
- [build.gradle](build.gradle) — gradle file to build and run the application
- [gradle.properties](gradle.properties) — application configuration variables

### Key directories

- [src](src) — application source code
    - [main/java](src/main/java) — java source code for the application
- [gradle](gradle) — support files for the `gradlew` build script
