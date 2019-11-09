FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD jrebel-license-server-1.0.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
