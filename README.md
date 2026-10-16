# MS Customer MongoDB WebFlux OpenAPI

Microservicio de clientes desarrollado con Spring Boot, WebFlux y MongoDB Reactive.

## Requisitos

- Java 17
- Maven 3.9+
- MongoDB en local o accesible por URL
- Git

## Estructura del proyecto

- `src/main/java`: código de la aplicación
- `src/main/resources/application.properties`: configuración de Spring
- `src/test/java`: pruebas

## Configuración

Asegúrate de tener MongoDB corriendo localmente en:

```text
mongodb://localhost:27017
```

Y la base de datos configurada en `application.properties`:

```properties
spring.application.name=ms-customer-dummy
server.port=8085
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/ui-docs
spring.data.mongodb.uri=mongodb://localhost:27017
spring.data.mongodb.database=bootcampbd
```

## Compilar

Desde la raíz del proyecto:

```bash
mvn clean install
```

## Ejecutar

```bash
mvn spring-boot:run
```

La aplicación quedará disponible en:

```text
http://localhost:8085
```

## Swagger

Una vez ejecutada la app, abre:

```text
http://localhost:8085/ui-docs
```

También puedes consultar la especificación OpenAPI en:

```text
http://localhost:8085/api-docs
```

## Endpoints principales

### Listar clientes

```http
GET http://localhost:8085/api/customers
```

### Buscar cliente por ID

```http
GET http://localhost:8085/api/customers/{id}
```

### Crear cliente

```http
POST http://localhost:8085/api/customers
Content-Type: application/json
```

Body ejemplo:

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan.perez@mail.com",
  "dni": "12345678"
}
```

### Eliminar cliente

```http
DELETE http://localhost:8085/api/customers/{id}
```

## Solución de problemas

### Swagger no carga

Verifica:

1. Que la app se haya levantado correctamente.
2. Que el puerto sea `8085`.
3. Que no haya conflicto entre `spring-boot-starter-web` y `spring-boot-starter-webflux`.
4. Abre estas URLs para comprobar:

```text
http://localhost:8085/ui-docs
http://localhost:8085/swagger-ui/index.html
http://localhost:8085/swagger-ui.html
```

### Error 404

Si ves un `Whitelabel Error Page`, revisa la URL exacta y asegúrate de estar llamando a un endpoint existente, por ejemplo:

```text
http://localhost:8085/api/customers
```
