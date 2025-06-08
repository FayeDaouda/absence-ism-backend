# Étape 1 : builder l'application image-storage-service
FROM maven:3.8.7-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Étape 2 : lancer l'application image-storage-service
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Attention : cible bien le bon fichier .jar
COPY --from=build /app/target/*.jar app.jar

# Le port exposé par le microservice
EXPOSE 8082

# Lancement de l'app
ENTRYPOINT ["java", "-jar", "app.jar"]
