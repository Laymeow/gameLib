FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

RUN ./gradlew dependencies

COPY src /app/src

RUN ./gradlew build -x test

EXPOSE 8082

CMD ["java", "-jar", "build/libs/auth-service-0.0.1-SNAPSHOT.jar"]