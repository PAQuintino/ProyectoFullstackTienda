# Proyecto Fullstack Tienda

Sistema de tienda online construido con arquitectura de microservicios en Spring Boot, completamente dockerizado, con autenticación JWT, documentación Swagger y comunicación REST entre servicios.

## Integrantes

- **Pablo Quintino** - PAQuintino - pa.quintino@duocuc.cl

**Asignatura:** DSY1103 - Desarrollo Fullstack I
**Docente:** Carlos Abarzúa Castro
**Institución:** DuocUC

## Tecnologías

- Java 17
- Spring Boot 3.5.3
- Spring Cloud Gateway
- Spring Security 6 + JWT (jjwt 0.12.6)
- JPA / Hibernate
- Liquibase
- MySQL 8.0
- Docker + Docker Compose
- springdoc-openapi 2.8.9 (Swagger)
- JUnit 5 + Mockito (pruebas unitarias)
- Lombok 1.18.38
- Maven

## Arquitectura - 10 microservicios

| Servicio | Puerto | Base de datos | Descripcion |
|----------|--------|---------------|-------------|
| api-gateway | 8090 | - | Puerta de entrada unica, enruta a los demas servicios |
| cliente-service | 8091 | db_cliente | Gestion de clientes (con HATEOAS) |
| producto-service | 8092 | db_producto | Gestion de productos y categorias |
| inventario-service | 8093 | db_inventario | Gestion de stock y almacenes |
| pedido-service | 8094 | db_pedido | Gestion de pedidos (usa WebClient con Mono.zip) |
| pago-service | 8095 | db_pago | Gestion de pagos y transacciones |
| autenticacion-service | 8096 | db_autenticacion | Login y generacion de tokens JWT |
| notificacion-service | 8097 | db_notificacion | Notificaciones del sistema |
| auditoria-service | 8098 | db_auditoria | Registro de auditoria |
| reporte-service | 8099 | db_reporte | Generacion de reportes |

## Rutas del API Gateway

Todas las peticiones pueden hacerse a traves del Gateway en el puerto 8090:

| Ruta | Microservicio destino |
|------|----------------------|
| /api/clientes/** | cliente-service |
| /api/productos/** | producto-service |
| /api/inventarios/** | inventario-service |
| /api/pedidos/** | pedido-service |
| /api/pagos/** | pago-service |
| /api/usuarios/** | autenticacion-service |
| /api/notificaciones/** | notificacion-service |
| /api/auditorias/** | auditoria-service |
| /api/reportes/** | reporte-service |

## Documentacion Swagger

Cada microservicio expone su propia documentacion Swagger UI:

- cliente-service: http://localhost:8091/swagger-ui.html
- producto-service: http://localhost:8092/swagger-ui.html
- inventario-service: http://localhost:8093/swagger-ui.html
- pedido-service: http://localhost:8094/swagger-ui.html
- pago-service: http://localhost:8095/swagger-ui.html
- autenticacion-service: http://localhost:8096/swagger-ui.html
- notificacion-service: http://localhost:8097/swagger-ui.html
- auditoria-service: http://localhost:8098/swagger-ui.html
- reporte-service: http://localhost:8099/swagger-ui.html

## Requisitos previos

- Docker Desktop instalado y corriendo
- Git
- (XAMPP cerrado si esta instalado, para evitar conflictos de puerto)

## Instrucciones de ejecucion

### 1. Clonar el repositorio

git clone https://github.com/PAQuintino/ProyectoFullstackTienda.git
cd ProyectoFullstackTienda

### 2. Levantar todos los servicios

docker-compose up --build

La primera vez tarda varios minutos en descargar dependencias y compilar. Las siguientes veces es mas rapido con:

docker-compose up

### 3. Verificar que todo este corriendo

Esperar a que aparezcan en los logs los mensajes "Started X Application in N seconds" de los 10 servicios. Luego abrir en el navegador:

http://localhost:8091/swagger-ui.html

### 4. Detener los servicios

Ctrl + C

O para limpiar contenedores:

docker-compose down

## Autenticacion JWT

### 1. Hacer login

POST http://localhost:8096/api/auth/login
Content-Type: application/json

{
    "email": "admin@tienda.com",
    "password": "1234"
}

Respuesta:

{
    "token": "eyJhbGc...",
    "email": "admin@tienda.com",
    "rol": "ADMIN"
}

### 2. Usar el token

En cada peticion a servicios protegidos, agregar el header:

Authorization: Bearer <token>

## Pruebas unitarias

Cada microservicio (excepto api-gateway) tiene pruebas con JUnit + Mockito siguiendo el patron Given-When-Then.

Para correr los tests de un servicio:

cd cliente-service
./mvnw test

## Estructura del proyecto

ProyectoFullstackTienda/
+-- api-gateway/
+-- cliente-service/
+-- producto-service/
+-- inventario-service/
+-- pedido-service/
+-- pago-service/
+-- autenticacion-service/
+-- notificacion-service/
+-- auditoria-service/
+-- reporte-service/
+-- docker-compose.yml

Cada microservicio tiene la siguiente estructura interna:

servicio-X/
+-- src/
¦   +-- main/
¦   ¦   +-- java/com/tienda/servicio_x/
¦   ¦   ¦   +-- config/
¦   ¦   ¦   +-- controller/
¦   ¦   ¦   +-- service/
¦   ¦   ¦   +-- repository/
¦   ¦   ¦   +-- entity/
¦   ¦   ¦   +-- dto/
¦   ¦   ¦   +-- security/
¦   ¦   +-- resources/
¦   ¦       +-- application.properties
¦   ¦       +-- db/changelog/
¦   +-- test/
+-- Dockerfile
+-- pom.xml

## Caracteristicas tecnicas implementadas

- 10 microservicios independientes
- Patron Controller/Service/Repository
- Comunicacion REST entre servicios con WebClient
- Mono.zip para llamadas paralelas en pedido-service
- API Gateway con rutas y predicados
- HATEOAS en cliente-service (ClienteControllerV2)
- Documentacion Swagger en los 9 servicios con controladores
- Autenticacion JWT (generacion en autenticacion-service, validacion en cliente-service)
- Liquibase para versionado del esquema de BD
- Validaciones con jakarta.validation
- Dockerizacion completa con docker-compose
- Pruebas unitarias con JUnit + Mockito (Given-When-Then)
- Manejo de errores con codigos HTTP adecuados

## Repositorio

https://github.com/PAQuintino/ProyectoFullstackTienda
