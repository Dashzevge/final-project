# Use an official JDK runtime as a parent image
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY target/finalProject-0.0.1-SNAPSHOT.jar /app

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "finalProject-0.0.1-SNAPSHOT.jar"]