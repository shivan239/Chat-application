FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY springboot/app /app

# Give execute permission to mvnw
RUN chmod +x mvnw

RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/app-0.0.1-SNAPSHOT.jar"]
