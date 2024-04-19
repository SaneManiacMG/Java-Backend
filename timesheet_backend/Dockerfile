FROM openjdk:21-jdk
ENV PORT=8080
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ./target/timesheet_backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]