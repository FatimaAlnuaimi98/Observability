# Observability

A simple Java spring boot application built for the purpose of observabilty and monitroing using Azure Application Insights and OpenTelemetry. 

## Features

- Integration with Azure Application Insights via Java agent
- Custom telemetry using OpenTelemetry instrumentation
- GitHub Actions CI/CD pipeline

## Application Architecture

The application follows a simple structure:
- `Controller.java`: REST API endpoints for managing users
- `User.java`: User entity model

## Application Insights Using Java agent

This application uses the Application Insights Java agent for automatic instrumentation and telemetry collection.

### Running Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Observability.git
   cd Observability/java-app
   ```

2. Build the application:
   ```bash
   mvn clean package
   ```

3. Download the Application Insights Java agent:
   ```bash
   curl -L -o applicationinsights-agent.jar https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.4.17/applicationinsights-agent-3.4.17.jar
   ```

4. Create an `applicationinsights.json` configuration file:
   ```json
   {
     "connectionString": "YOUR_CONNECTION_STRING",
     "role": {
       "name": "user-service"
     },
     "sampling": {
       "percentage": 100
     },
     "instrumentation": {
       "logging": {
         "level": "DEBUG"
       }
     }
   }
   ```

5. Replace `YOUR_CONNECTION_STRING` with your Application Insights connection string from the Azure Portal.

6. Run the application with the agent:
   ```bash
   java -javaagent:applicationinsights-agent.jar -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

### Running with Docker

1. Build the Docker image:
   ```bash
   docker build -t user-service .
   ```

2. Run the Docker container:
   ```bash
    docker run -e APPLICATIONINSIGHTS_CONNECTION_STRING="" -p 6060:6060 user-service
   ```

The Dockerfile is already configured to include the Application Insights Java agent and configuration.

### Azure Deployment

#### Using GitHub Actions

The repository includes GitHub Actions workflows for CI/CD deployment to Azure. To use them:

1. Set up the following GitHub secrets:
   - `AZURE_CREDENTIALS`: Azure service principal credentials
   - `ACR_NAME`: Azure Container Registry name
   - `RESOURCE_GROUP`: Azure resource group name
   - `WEB_APP_NAME`: Azure Web App name

2. Push to the main branch to trigger the workflow.

## API Endpoints

- `GET /`: Welcome message
- `GET /users`: List all users
- `POST /add_user`: Add a new user
