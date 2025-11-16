# Use a imagem base do Maven
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o pom.xml e o diretório src
COPY pom.xml .
COPY src ./src

# Execute o Maven para construir a aplicação
RUN mvn clean package

# Use uma imagem do JDK 17 a partir de eclipse-temurin
FROM eclipse-temurin:21 AS runtime

# Copie o JAR da fase de build
COPY --from=build /app/target/mubification-1.0-SNAPSHOT.jar app.jar

# Exponha a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
