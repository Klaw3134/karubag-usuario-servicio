# usuario-servicio

Microservicio de gestión de usuarios para la plataforma Karübag.

## Descripción
Gestiona las cuentas de usuario del sistema, incluyendo clientes residenciales, corporativos y operadores de ruta.

## Tecnologías
- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- PostgreSQL (Neon)
- Spring Security

## Puerto
`8081`

## Base de datos
`karubag_usuario`

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/usuarios | Listar todos los usuarios |
| GET | /api/usuarios/{id} | Obtener usuario por ID |
| POST | /api/usuarios | Crear usuario |
| PUT | /api/usuarios/{id} | Actualizar usuario |
| DELETE | /api/usuarios/{id} | Eliminar usuario |

## Cómo ejecutar
```bash
./mvnw spring-boot:run
```

## Variables de entorno
Configurar en `application.properties`:
```
spring.datasource.url=jdbc:postgresql://<host>/karubag_usuario
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
```