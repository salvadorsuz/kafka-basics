FROM openjdk:8-jdk-alpine

ARG JAR_FILE
COPY target/${JAR_FILE} /app/service.jar

ENTRYPOINT ["java","-jar","/app/service.jar"]