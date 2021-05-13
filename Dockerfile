FROM maven:3.6.0-jdk-11-slim
COPY ./ ./
RUN mvn clean package

FROM openjdk:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY --from=0 ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

