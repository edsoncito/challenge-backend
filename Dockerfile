FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia TODO el proyecto, incluyendo mvnw y la carpeta .mvn
COPY . .

# Da permisos al mvnw
RUN chmod +x mvnw

# Construye usando Maven Wrapper
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/challenge-1.0-SNAPSHOT.jar"]
