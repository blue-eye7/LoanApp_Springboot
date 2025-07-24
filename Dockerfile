# Use an official Java 17 JDK base image
FROM openjdk:17-jdk-slim

# Add a label (optional)
LABEL maintainer="batman_container"

# Add the jar file (adjust name as needed)
ARG JAR_FILE=target/Loan1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose port (optional – for Docker Compose or Kubernetes)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
