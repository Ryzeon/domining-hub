# Use maven image with jdk 17 to build the project
FROM jelastic/maven:3.9.5-openjdk-21 AS build

# Set the working directory in the image
WORKDIR /app

# Copy pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Change spring.profiles.default to prod
RUN sed -i 's/spring.profiles.active=dev/spring.profiles.active=prod/' src/main/resources/application.properties

# Package the application
RUN mvn clean install

FROM openjdk:21-jdk-slim
MAINTAINER Ryzeon
ARG JAR_FILE=target/domining-hub-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

