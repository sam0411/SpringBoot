FROM openjdk:8
VOLUME /slm
ARG JAR_FILE=spring-boot/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]