# Challenge Backend

## Descripción
Este proyecto esta desarrollado en **Java + Spring Boot** que expone servicios de calulo dinámicas y registro de historial de llamadas, utilizando **PostgreSQL 15** como base de datos  y **Redis** como almacenamiento cache.

## Tecnologías usadas
- Java 21+
- Spring Bot 3
- Spring Data JPA
- PostgreSQL 15
- Redis
- Docker
- Swagger/OpenAPI (para documentación de APIs)

## Arquitectura
El proyecto sigue principios de **Clean Architecture**, organizando la solución en capas basado en Onion Architecture.
- **Domain**: Modelos y repositorios.
- **Application**: Servicios.
- **Infrastructure**: Persistencia de datos (JPA, Redis) y consumo de servicio externos (Mock).
- **Web**: Controladores REST y configuración general.
- **Common**: Clases comunes y utilidades. que podria ser un sharedKarnel para el uso compartido.

## Requisitos
- Docker (para base de datos y Redis)
- Java 21
- Maven

## Instalación y ejecución local

1. Clona el repositorio:
   ```bash
   git clone https://github.com/edsoncito/challenge-backend.git
   cd challenge-backend
   ```
   
2. levantar las dependencias de docker
   ```bash
   docker-compose up -d
   ```
   
3.corre el proyecto
   ```bash
   mvn spring-boot:run
   ```

4. accede a swagger
   ```bash
   http://localhost:8051/challenge/swagger-ui/index.html
   ```

## endpoint principales
- **POST** `/api/dynamic-rate` : Realiza una operación de sumar los valores y aplique un porcentaje adicional obtenido de un servicio externo o mock. 
- **GET** `/api//history-call` : Devuelve el historial de operaciones realizadas.


## Ejemplo de uso 
 - Dentro de la carpeta infraestructere podes cambiar el mock de la respuesta 
   
 - Para que la llamda sea exitosa
   return 12.10;
   
 - para que llamada al servicio reciba null como respuesta
   return null;

 para que la llamada sea una exception simulando que el servicio esta abajo o fallo 
    throw new Exception("Error Server");
   