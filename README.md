# README para el Backend

## Descripción

Esta es la parte backend de la aplicación full-stack, construida con **Spring Boot** y **MongoDB**.
Esta guía te ayudará a montar y ejecutar el back del proyecto.

## Requisitos Previos

- Java 17
- Docker


## Configuración de Docker
Construir el servicio de MongoDB y mongo-express, para MongoDB deberia de poder acceder 
con estas credenciales:

    Usuario: rootuser 
    
    Contraseña: rootpass

#### Con MongoExpress deberias de poder acceder con estas credenciales:

    Usuario: admin
    
    Contraseña: pass

Puedes acceder a mongo-express en http://localhost:8081 para gestionar tus bases de datos. 


## Ejecución y Testeo
En este proyecto se isa Swagger para documentar la API, puedes acceder a los controladores desde la siguiente URL:

    http://localhost:8080/festimania/swagger-ui/index.html

Para poder usar los endpoints de PUT, POST y DELETE, deberás de registrarte, cambiar el rol a ADMIN en la 
bases de datos y loguearte con el usuario creado.

## Integración con el Frontend
Para conectar el frontend, sigue estos pasos:

Descarga el proyecto del frontend que utiliza Angular 17.

Instala PrimeNG en el proyecto del frontend.

Ejecuta la aplicación frontend en el puerto 4200 con el siguiente comando:

```bash
ng serve --port 4200
```

Asegúrate de que la API esté corriendo y que la base de datos esté activa antes de ejecutar el frontend.

### Autor
[Francesco Marelli]
¡Ahora disfruta de la API! Si tienes preguntas o necesitas ayuda, no dudes en contactarme.



