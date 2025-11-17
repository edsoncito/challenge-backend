# Usa una imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Construimos la app con Maven wrapper (si lo tienes) o con Maven instalado en la imagen
RUN ./mvnw clean package -DskipTests

# Expone el puerto donde corre Spring Boot
EXPOSE 8080

# Ejecuta la app
CMD ["java", "-jar", "target/challenge-1.0-SNAPSHOT.jar"]
