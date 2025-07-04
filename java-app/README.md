# Java User Management Application

A simple java spring boot application, built for the purpose of testing out different azure monitoring services.

## Application Insights Using Java agent

```bash
# Download the Application Insights Java agent
curl -L -o applicationinsights-agent.jar https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.4.17/applicationinsights-agent-3.4.17.jar

# Run the application with the agent
java -javaagent:applicationinsights-agent.jar -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Running with Docker

Application Insight connection string is copied from the resource in Azure portal. 

```bash
# Build the Docker image
docker build -t user-app .

# Run the container
docker run -e APPLICATIONINSIGHTS_CONNECTION_STRING="" -p 6060:6060 user-app
```

## API Endpoints

- `GET /` - Welcome message
- `GET /users` - List all users
- `POST /add_user` - Add a new user