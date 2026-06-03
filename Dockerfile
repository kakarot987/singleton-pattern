# Multi-stage build for Spring Boot backend
FROM gradle:8.0-jdk17 AS builder

WORKDIR /app

# Copy gradle files
COPY build.gradle settings.gradle ./

# Copy source code
COPY src ./src

# Build the application
RUN gradle build -x test --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Install curl for healthcheck
RUN apk add --no-cache curl

# Copy built jar from builder stage
COPY --from=builder /app/build/libs/singleton-demo-1.0.0.jar app.jar

# Copy application properties
COPY src/main/resources/application.properties application.properties
COPY src/main/resources/application-docker.properties application-docker.properties

# Create logs directory
RUN mkdir -p /app/logs

EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC"

HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8080/api/singleton/all || exit 1

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

