FROM amazoncorretto:8-alpine-jdk
VOLUME /slm
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]