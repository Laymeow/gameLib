FROM openjdk:17-jdk-slim

WORKDIR /app

COPY api-gateway/build/libs/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "app.jar"]