# Chessmaster Backend

Este es el repositorio del backend de la plataforma Chessmaster, desarrollada con **Spring Boot**, **MongoDB**, y **Docker**.
Repositorio Backend de la plataforma Chessmaster360 con las funcionalidades de: Gestión de usuarios y autenticación. Almacenamiento de partidas (MongoDB). Exposición de APIs REST para conectar con el frontend.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

- **Java 11 o superior** (Para ejecutar el backend con Spring Boot)
- **Maven** (Para la gestión de dependencias y construcción del proyecto)
- **Docker** y **Docker Compose** (Para la contenedorización y ejecución del entorno)
- **MongoDB** (Para almacenar los datos, pero se usa en Docker en este caso)

## Instalación

### 1. Clonar el Repositorio

Clona este repositorio en tu máquina local:

```bash
git clone https://github.com/tu-usuario/chessmaster-backend.git
cd chessmaster-backend
```

### 2. Configuración del Proyecto

#### 2.1 Dependencias de Maven

Asegúrate de tener todas las dependencias necesarias para ejecutar el backend. El archivo `pom.xml` debe contener las siguientes dependencias:

```xml
<dependencies>
    <!-- Spring Boot Web dependency for REST API -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot MongoDB dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>

    <!-- Spring Boot Starter Test for testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Optional: Add any other dependencies you need -->
</dependencies>
```

#### 2.2 Configuración de MongoDB

En el archivo `application.properties` o `application.yml` de Spring Boot, agrega la configuración para MongoDB:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/chessmaster
```

### 3. Docker

Para ejecutar el backend junto con una instancia de MongoDB usando **Docker Compose**, sigue estos pasos: 

#### 3.1 Crear el Dockerfile ***(En este caso no es Necesario ya que está creado dentro del repositorio estos archivos)***

Crea un archivo `Dockerfile` en la raíz del proyecto con el siguiente contenido:

```Dockerfile
# Usa una imagen base de Java
FROM openjdk:11-jre-slim

# Copia el archivo JAR del proyecto
COPY target/chessmaster-backend.jar /app/chessmaster-backend.jar

# Expón el puerto 8181
EXPOSE 8181

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/chessmaster-backend.jar"]
```

#### 3.2 Crear el archivo docker-compose.yml ***(En este caso no es Necesario ya que está creado dentro del repositorio estos archivos)***

Crea un archivo `docker-compose.yml` en la raíz del proyecto para definir tanto el backend como MongoDB en contenedores separados:

```yaml

services:
  mongodb:
    image: mongo:latest
    container_name: chessmaster-mongo
    ports:
      - "27017:27017"
    networks:
      - chessmaster-network

  backend:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: chessmaster-backend
    ports:
      - "8181:8181"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/chessmaster
    depends_on:
      - mongodb
    networks:
      - chessmaster-network

networks:
  chessmaster-network:
    driver: bridge
```

Este archivo de `docker-compose` levantará dos servicios:

1. **MongoDB** (en el puerto 27017)
2. **Backend** (en el puerto 8181), que se conecta a MongoDB a través de la red de Docker.

### 4. Ejecutando la Aplicación

#### 4.1 Usando Docker Compose

Para ejecutar ambos servicios (MongoDB y el backend), ejecuta el siguiente comando en la raíz de tu proyecto donde se encuentra el archivo `docker-compose.yml`:

```bash
docker-compose up --build
```

Esto compilará la aplicación y levantará los contenedores. El backend estará disponible en `http://localhost:8080` y MongoDB estará en `mongodb://localhost:27017`.

#### 4.2 Ejecutar Sin Docker

Si prefieres ejecutar la aplicación sin Docker, puedes hacerlo utilizando Maven:

1. **Construir el JAR del proyecto**:

```bash
mvn clean package
```

2. **Ejecutar la aplicación**:

```bash
java -jar target/chessmaster-backend.jar
```

### 5. Probando la API

Una vez que el backend esté ejecutándose, puedes probar las API disponibles. Por ejemplo:

- **GET** `http://localhost:8080/` — devuelve un mensaje de bienvenida.
- **POST** `http://localhost:8080/users` — para crear un nuevo usuario.
- **GET** `http://localhost:8080/users/{id}` — para obtener detalles de un usuario.

### 6. Acceder al Contenedor MongoDB

Si deseas acceder a MongoDB directamente desde la terminal del contenedor:

```bash
docker exec -it chessmaster-mongo mongo
```

Esto te permitirá interactuar con la base de datos desde la shell de MongoDB.

### 7. Consideraciones

- Asegúrate de que el contenedor de MongoDB esté en funcionamiento antes de intentar ejecutar el backend, ya que el backend depende de la base de datos.
- Puedes personalizar más la configuración de MongoDB y Spring Boot según lo necesites (por ejemplo, autenticación, configuraciones adicionales, etc.).

## Contribuciones

Si deseas contribuir a este proyecto, por favor crea un **pull request** con las mejoras que desees hacer. Asegúrate de seguir los siguientes pasos:

1. Realiza un **fork** del repositorio.
2. Crea una nueva rama para tus cambios (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz un **commit** (`git commit -m "Descripción de los cambios"`).
4. Sube tu rama (`git push origin feature/nueva-caracteristica`).
5. Crea un **pull request** a la rama `main` del repositorio original.

## Resumen

- **Dependencias de Maven**: Incluye `spring-boot-starter-web` para manejar las rutas REST y `spring-boot-starter-data-mongodb` para la integración con MongoDB.
- **Docker y Docker Compose**: Proporciona un archivo `Dockerfile` y `docker-compose.yml` para facilitar la ejecución en contenedores, incluyendo la configuración para MongoDB.
- **Estructura del Proyecto**: Explica cómo estructurar y ejecutar el backend, con configuraciones de MongoDB y la creación de un contenedor para la base de datos.

## Licencia

Este proyecto está bajo la **Licencia MIT**. Para más detalles, consulta el archivo [LICENSE](LICENSE).
  
