# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven or Gradle build artifact to the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default Spring Boot port is 8080)
EXPOSE 8081

# Set environment variables for PostgreSQL (adjust if necessary)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/task_manager
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
