# DOSW_ParcialT2_JuanVillegas

Proyecto base del parcial practico de DOSW, preparado con Spring Boot y la estructura solicitada en los prerequisitos.

## Datos del proyecto

- Repositorio: `DOSW_ParcialT2_JuanVillegas`
- groupId: `edu.dosw.parcial`
- artifactId: `DOSW-ParcialT2`
- Java: `21`
- Spring Boot: `3.5.13`

## Estructura base

El proyecto incluye los paquetes base:

- `config`
- `controller`
- `service`
- `repository`
- `entity`
- `mapper`
- `model`
- `dto`
- `exception`
- `security`

Tambien se agregaron las carpetas:

- `src/main/resources/docs/uml`
- `src/main/resources/docs/images`
- `src/main/resources/docs/requirements`

## Base de datos

El proyecto esta configurado para PostgreSQL con estos datos:

- host: `localhost`
- puerto: `5432`
- base de datos: `testdb`
- usuario: `postgres`
- contrasena: `1234`

Contenedor sugerido:

```bash
docker run --name postgres \
  -e POSTGRES_PASSWORD=1234 \
  -e POSTGRES_DB=testdb \
  -p 5432:5432 \
  -d postgres
```

## Comandos utiles

Ejecutar la aplicacion:

```bash
./mvnw.cmd spring-boot:run
```

Ejecutar pruebas:

```bash
./mvnw.cmd test
```

Endpoint base de verificacion:

- `GET /api/health`
