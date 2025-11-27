# Etapa de build com Maven + JDK 21
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copia arquivos do Maven primeiro para cache eficiente
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código
COPY src ./src

# Gera o jar "shaded"
RUN mvn clean package -DskipTests

# Runtime com JDK 21
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

COPY --from=build /app/target/mubification-1.0-SNAPSHOT.jar app.jar

# Exponha apenas para documentação — o Render ignora EXPOSE
EXPOSE 8080

# Start no Render usa PORT, então não force porta no código
ENTRYPOINT ["java", "-jar", "app.jar"]
