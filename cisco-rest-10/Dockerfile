FROM openjdk:8-jdk-alpine
MAINTAINER Krishna Mohan Koyya <krishna@glarimy.com>
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=target/glarimy.jar
ADD ${JAR_FILE} glarimy.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:27017/library", "-Djava.security.egd=file:/dev/./urandom","-jar","/glarimy.jar"]