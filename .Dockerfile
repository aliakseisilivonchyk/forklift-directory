# Stage 1: Build the application with Maven and a JDK
FROM maven:3.9.13-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml ./
COPY backend/pom.xml ./backend/
COPY backend/src ./backend/src
COPY frontend/pom.xml ./frontend/
COPY frontend/index.html ./frontend/
COPY frontend/jsconfig.json ./frontend/
COPY frontend/package.json ./frontend/
COPY frontend/package-lock.json ./frontend/
COPY frontend/vite.config.js ./frontend/
COPY frontend/src ./frontend/src
COPY frontend/public ./frontend/public
RUN mvn clean install -DskipTests

# Stage 2: Create a lightweight runtime image with a JRE
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/backend/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]