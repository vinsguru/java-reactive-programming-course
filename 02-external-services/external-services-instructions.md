# External Services

We will be using below jar file to demonstrate non-blocking HTTP.

## How To Run

- Ensure that you have Java 17 or anything above installed
- Please download [this jar](https://github.com/vinsguru/java-reactive-programming-course/raw/master/02-external-services/external-services.jar)
- Keep the downloaded jar somewhere in your machine
- Open terminal/command line and navigate to the path where you have the jar.
- Run this below command.

```bash
java -jar external-services.jar
```
- It uses port `7070` by default.

## Docker

If you do not want to run the jar directly on your machine, You can use docker. Build your image and run by doing the port mapping.
```Dockerfile
FROM bellsoft/liberica-openjdk-alpine:21
WORKDIR app
ADD https://github.com/vinsguru/java-reactive-programming-course/raw/master/02-external-services/external-services.jar external-services.jar
CMD java -jar external-services.jar
```

## To change the port

```bash
java -jar external-services.jar --server.port=8080
```

