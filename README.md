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


### RESOLUCION PARCIAL 
1) DIAGRAMA ENTIDAD-RELACION
 Se realiza el diagrama siguiendo los lineamientos del parcial creando los roles, los colaboradores, los computadores los perifericos y cada cosa con sus respectivas caracteristicas

<img width="1245" height="377" alt="image" src="https://github.com/user-attachments/assets/3b793773-913b-4838-89dc-96b6ced4a76c" />

2) Construya las tablas diseñadas en su base de datos de PostgreSQL

   desde la aplicacion de DBeaver se pueden observar las tablas correspondientes tras la insercion de datos insertados en cada tabla (

   <img width="338" height="224" alt="image" src="https://github.com/user-attachments/assets/9c6e5f2f-0e04-485a-a600-f9e99e1d4aca" />


3) inserte al menos 3 registros en cada tabla

roles_permisos
<img width="305" height="281" alt="image" src="https://github.com/user-attachments/assets/87214ffc-0abf-4ba6-ac74-618b153177a4" />

Roles

<img width="286" height="134" alt="image" src="https://github.com/user-attachments/assets/4c33882b-2d59-426d-b17b-970c4c218b51" />

Permisos

<img width="390" height="167" alt="image" src="https://github.com/user-attachments/assets/49396fef-8838-441a-869c-a3789c27f297" />


Perifericos 

<img width="999" height="119" alt="image" src="https://github.com/user-attachments/assets/5fe025c9-09e5-4065-955f-0f2c5aae02e0" />


Computadores 

<img width="920" height="126" alt="image" src="https://github.com/user-attachments/assets/a9630cd4-7d7b-441b-8b0d-744b1c05fff5" />


Controladores 

<img width="1156" height="126" alt="image" src="https://github.com/user-attachments/assets/3fd50ac9-cbc2-420d-b66d-17efd3902cac" />



5) Crear diagrama de clases
   
 <img width="1276" height="674" alt="image" src="https://github.com/user-attachments/assets/adc6552a-cd8e-4112-8cef-fc5cbda03b72" />


   
