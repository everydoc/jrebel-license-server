FROM openjdk:11
ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar
ENV JAVA_OPTS="-Xmx512m -Xms512m"
EXPOSE 9090
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "${JAVA_OPTS}", "-jar", "-Duser.timezone=GMT+8", "/app.jar"]
