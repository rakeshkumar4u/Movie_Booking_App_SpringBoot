# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/my-springboot-app.jar app.jar

# Expose the port your app runs on (default is 8080)
EXPOSE 8085

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
