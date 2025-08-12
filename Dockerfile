FROM eclipse-temurin:17-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

WORKDIR /app
COPY springboot/app /app
RUN mvn package -DskipTests

CMD ["java", "-jar", "target/app-0.0.1-SNAPSHOT.jar"]
