# Stage 1: Build the application with Maven and a JDK
FROM maven:3.9.13-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml /app/
COPY backend /app/backend
COPY frontend /app/frontend
RUN mvn clean install -DskipTests

# Stage 2: Create a lightweight runtime image with a JRE
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/backend/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]