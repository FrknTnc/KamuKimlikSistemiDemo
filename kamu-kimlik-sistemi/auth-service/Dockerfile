FROM eclipse-temurin:17

# Doğru JAR adını kullan
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
