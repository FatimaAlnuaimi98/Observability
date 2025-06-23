FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
COPY applicationinsights.json applicationinsights.json
# Download Application Insights Java agent with proper error handling
RUN apt-get update && apt-get install -y curl \
    && curl -L -o /app/applicationinsights-agent.jar https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.4.17/applicationinsights-agent-3.4.17.jar 
   
EXPOSE 6060
CMD ["java", "-javaagent:/app/applicationinsights-agent.jar", "-jar", "app.jar", "--server.port=6060"]
