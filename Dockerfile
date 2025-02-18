# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Java file into the container
COPY HelloWorld.java .

# Compile the Java file
RUN javac HelloWorld.java

# Define the command to run the Java application
CMD ["java", "HelloWorld"]
