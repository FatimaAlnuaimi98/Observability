FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 6060
CMD ["java", "-jar", "app.jar", "--server.port=6060"]
