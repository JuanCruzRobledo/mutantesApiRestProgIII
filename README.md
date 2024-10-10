# 🧬 API de Detección de Mutantes para Magneto

## 📋 Descripción
Esta API permite detectar si una secuencia de ADN corresponde a un mutante, apoyando la misión de Magneto de reclutar nuevos integrantes. La API analiza secuencias de ADN y proporciona estadísticas sobre las verificaciones.

## 🌐 URL de Producción
La API está hosteada en Render:
- URL Base: `https://mutantesapirestprogiii.onrender.com`
- Documentación interactiva en ➡️ [Swagger UI](https://mutantesapirestprogiii.onrender.com/swagger-ui/index.html).

## 📂 Tabla de Contenidos
- [📋 Descripción](#-descripción)
- [🌐 URL de Producción](#-url-de-producción)
- [⚙️ Requisitos Previos](#️-requisitos-previos)
- [🚀 Ejecución del Proyecto](#-ejecución-del-proyecto)
- [📬 Uso de la API](#-uso-de-la-api)
- [📈 Despliegue](#-despliegue)
- [📑 Documentación Adicional](#-documentación-adicional)

## ⚙️ Requisitos Previos a la ejecución
- Java 17
- Gradle
- Docker
- Cuenta en Render

## 🚀 Ejecución del Proyecto

- ### Para acceder a la api Hosteada:
   Acceder a la API en producción: La API está hosteada en Render, y puedes acceder a ella en para ver la documentación visual e interactiva de la API. [Swagger UI](https://mutantesapirestprogiii.onrender.com/swagger-ui/index.html)

- ### Para ejecutar el proyecto localmente:

    - **Clonar el repositorio**:
      ```bash
      git clone https://github.com/JuanCruzRobledo/mutantesApiRestProgIII.git
      cd mutantesApiRestProgIII
   
    - **Ejecutar la aplicación**: Ejecutar la clase principal Spring Boot llamada ParcialMutantesProgIiiApplication
   
    - **Acceder a la API localmente**: Si ejecutas el proyecto en tu máquina local, 8080 es el puerto especificado en el archivo application.properties (puedes modificarlo si es necesario).
      - Puedes hacer las solicitudes a la API en http://localhost:8080/swagger-ui/index.html.
      - Puedes enviar una request atraves de Postman.
      - Puedes acceder a la base de datos h2  http://localhost:8080/h2-console/ (revisar en el archivo application.properties: contraseña, usuario, URL de JDBC que es en memoria).
  

## 📬 Uso de la API

| Endpoint        | Método | Descripción                   |
|-----------------|--------|-------------------------------|
| `/mutant`      | POST   | Detecta si un ADN es mutante |
| `/mutant/stats`        | GET    | Muestra estadísticas de ADN  |

#### 🔎 Endpoint `/mutant/`
Este endpoint detecta si una secuencia de ADN pertenece a un mutante según los criterios de Magneto. La solicitud debe enviarse como un POST en formato JSON con el siguiente esquema:



1. **Formato JSON**:
   ```json
   {
   "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
   }

#### 📝 Validaciones Requeridas
- **Clave `dna`**: Debe ser un arreglo de strings representando cada fila de la matriz de ADN.
- #### **Restricciones de Matriz**:
  - **Tamaño mínimo**: 4x4.
  - **Formato NxN**: El número de filas y columnas debe ser igual.
  - **Bases Nitrogenadas Válidas**: Cada string debe contener solo las letras A, C, T, o G.
  - **Datos Completos**: No debe haber valores nulos ni vacíos.
#### 🔄 Respuestas del Endpoint
- **Mutante detectado**: Devuelve HTTP 200 OK.
- **No es mutante**: Devuelve HTTP 403 Forbidden.







