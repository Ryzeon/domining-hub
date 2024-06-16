FROM openjdk:21-jdk-slim
MAINTAINER Ryzeon
ARG JAR_FILE=target/domining-hub-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]