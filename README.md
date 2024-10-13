# 🧬 API de Detección de Mutantes para Magneto

## 📂 Tabla de Contenidos
- [📋 Descripción](#-descripción)
- [🌐 URL de Producción](#-url-de-producción)
- [⚙️ Requisitos Previos](#️-requisitos-previos)
- [🚀 Ejecución del Proyecto](#-ejecución-del-proyecto)
- [📬 Uso de la API](#-uso-de-la-api)
- [🧪 Pruebas](#-pruebas)

## 📋 Descripción
Esta API permite detectar si una secuencia de ADN corresponde a un mutante, apoyando la misión de Magneto de reclutar nuevos integrantes. La API analiza secuencias de ADN y proporciona estadísticas sobre las verificaciones.
## Diagrama y explicación de secuencia 

![Diagrama de secuencia - Parcial Programacion III ](https://github.com/user-attachments/assets/36631540-5952-40b8-9533-9acbbcb12ecb)

[Parcial Programacion - Diagrama de secuencia y explicacion..pdf](https://github.com/user-attachments/files/17357334/Parcial.Programacion.-.Diagrama.de.secuencia.y.explicacion.pdf)

## 🌐 URL de Producción
La API está hosteada en Render:
- URL Base: `https://mutantesapirestprogiii.onrender.com`
- Documentación interactiva en ➡️ [Swagger UI](https://mutantesapirestprogiii.onrender.com/swagger-ui/index.html).

## ⚙️ Requisitos Previos
- Java 17
- Gradle
- Docker
- Cuenta en Render

## 🚀 Ejecución del Proyecto

- ### Para ejecutar el proyecto localmente:

    - **Clonar el repositorio**:
      ```bash
      git clone https://github.com/JuanCruzRobledo/mutantesApiRestProgIII.git
      cd mutantesApiRestProgIII
      ```
   
    - **Ejecutar la aplicación**: Ejecutar la clase principal Spring Boot llamada ParcialMutantesProgIiiApplication
   
    - **Acceder a la API localmente**: Si ejecutas el proyecto en tu máquina local, 8080 es el puerto especificado en el archivo application.properties (puedes modificarlo si es necesario).
      - Puedes hacer las solicitudes a la API en http://localhost:8080/swagger-ui/index.html.
      - Puedes enviar una request atraves de Postman.
         - **POST**: http://localhost:8080/mutant
         - **GET**:  http://localhost:8080/mutant/stats
      - Puedes acceder a la base de datos h2  http://localhost:8080/h2-console/ (revisar en el archivo application.properties: contraseña, usuario, URL de JDBC que es en memoria).

        https://github.com/user-attachments/assets/f423c07c-98f3-4b07-a6dc-d77484523db8
        
- ### Para acceder a la api Hosteada:
   Acceder a la API en producción: La API está hosteada en Render, y puedes acceder a ella en para ver la documentación visual e interactiva de la API. [Swagger UI](https://mutantesapirestprogiii.onrender.com/swagger-ui/index.html)
   - Tambien puedes enviar una request atraves de Postman.
      - **POST**: https://mutantesapirestprogiii.onrender.com/mutant
      - **GET**:  https://mutantesapirestprogiii.onrender.com/mutant/stats
   - Puedes enviar una request atraves de la documentación interactiva [Swagger UI](https://mutantesapirestprogiii.onrender.com/swagger-ui/index.html). 

     https://github.com/user-attachments/assets/8887c1fc-cfd3-4625-aaa7-225d024de9fc
     

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
   ```
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

## 🧪 Pruebas

### Pruebas Untarias y de Integración
La API cuenta con pruebas unitarias y de integración para asegurar su correcto funcionamiento, estas `se pueden ejecutar` para probar el funcionamiento de la API de Manera independiente sin tener que ejecutar el servidor.

### Pruebas de Servicio (`MutantServiceTest`) 
 **Ubicacion**: `src\test\java\com\juan\parcialmutantesprogiii\controllers`.Esta clase se encarga de validar el manejo de errores en el detector de mutantes.


### Pruebas de Controlador (`MutantControllerTest`) 
 **Ubicacion**: `src\test\java\com\juan\parcialmutantesprogiii\controllers` .Esta clase realiza pruebas de integración en el controlador para asegurarse de que las respuestas HTTP sean las esperadas.

 ![image](https://github.com/user-attachments/assets/cdc26870-be30-45d4-a3bb-7fc704186a7b)


### Pruebas POST Manuales
- Mutantes
```json
   {
      "dna": [
         "AAAA",
         "CCCC",
         "TCAG",
         "GGTC"
      ]
   }
```
   
 ```json
   {
      "dna": [
          "TGAC",
          "AGCC",
          "TGAC",
          "GGTC"
      ]
   }

```
```json
   {
   "dna": [
          "ATAT",
          "TATA",
          "ATAT",
          "TATA"
      ]
   }
```
 ```json
   {
   "dna": [
          "GGGG",
          "ATCG",
          "ATCG",
          "GGGG"
      ]
   }
```
- No Mutantes
```json
   {
      "dna": [
         "AAAT",
         "AACC",
         "AAAC",
         "CGGG"
      ]
   }
```
   
 ```json
   {
      "dna": [
          "TGAC",
          "ATCC",
          "TAAG",
          "GGTC"
      ]
   }

```
```json
   {
   "dna": [
          "ATCG",
          "TAGC",
          "CGAT",
          "GCAT"
      ]
   }
```
```json
   {
   "dna": [
          "AAAC",
          "AACC",
          "CCGA",
          "TGGC"
      ]
   }
```
