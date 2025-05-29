# Étape 1 : builder l'application
FROM maven:3.8.6-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Étape 2 : lancer l'application
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Pas besoin d'exposer un port fixe ici,
# Render gère ça automatiquement.
# EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
