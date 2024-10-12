package com.juan.parcialmutantesprogiii.presentation.controllers;

import com.juan.parcialmutantesprogiii.domain.dto.DnaStats;
import com.juan.parcialmutantesprogiii.business.services.MutantService;
import com.juan.parcialmutantesprogiii.domain.dto.DnaRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController // Indica la clase es un controlador REST en Spring Boot que gestiona peticiones HTTP y devolviendo respuestas en formato JSON.
@RequestMapping("/mutant") // Define la ruta base para las solicitudes a este controlador.
@Validated
public class MutantController {

    private final MutantService mutantService;  //Inyección de dependencias para el servicio MutantService, es declarado como final para asegurar su inmutabilidad.
    @Autowired //Desde la version 4.3 de spring ya no es necesario.
    // Constructor para la inyección de dependencias de MutantService.
    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    //Configuracion para la documentacion de la API.
    @Operation(summary = "Verifica si la secuencia de ADN es mutante", description = "Se tiene que mandar un post respetando: 1-Matriz de strings / 2-Matriz NxN / 3-Solo caracteres ACTG / 4-Sin valores nulos ni vacios. En caso de ser mutante devuelve un HTTP 200-0K  y en caso de No ser mutante devuelve un 403-FORBIDDEN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Secuencia de ADN es mutante",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Ejemplo Mutante 1", value = "{\"dna\": [\"AAAA\", \"CCCC\", \"TCAG\", \"GGTC\"]}"),
                                    @ExampleObject(name = "Ejemplo Mutante 2" , value = "{\"dna\": [\"TCGGGTGAT\", \"TGATCCTTT\", \"TACGAGTGA\", \"AAATGTACG\", \"ACGAGTCGT\", \"GAATTCCAA\",\"ACTACGACC\",\"TGAGTATCC\"]}")
                            })),
            @ApiResponse(responseCode = "403",
                    description = "Secuencia de ADN es humana",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Ejemplo Humano 1", value = "{\"dna\": [\"CATG\", \"TACG\", \"AGTC\", \"GATC\"]}"),
                                    @ExampleObject(name = "Ejemplo Humano 2", value = "{\"dna\": [\"AAAC\", \"AACC\", \"CCGA\", \"TGGC\"]}")
                            })),
    })

    @PostMapping("") // Define una solicitud POST a la ruta base "/mutant".
    public ResponseEntity<?> isMutant(@Valid @RequestBody DnaRequest dnaRequest) { // @Valid asegura que los datos de entrada en DnaRequest cumplan las restricciones de validación.
        try {
            boolean isMutant = mutantService.isMutant(dnaRequest.getDna()); //Llama al servicio para devolver el resultado de si es mutante
            // Retorna un estado HTTP 200 OK si es mutante, o 403 FORBIDDEN si no lo es.
            return isMutant ? ResponseEntity.ok().body("El ADN es MUTANTE") : ResponseEntity.status(HttpStatus.FORBIDDEN).body("El ADN es HUMANO");
        } catch (Exception e){
            // Retorna un estado HTTP 500 INTERNAL_SERVER_ERROR si ocurre algún error en la verificación.
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Intente mas tarde");
        }
    }

    //Configuracion para la documentacion de la API.
    @Operation(summary = "Obtiene estadísticas de ADN de mutantes y humanos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Estadísticas de ADN obtenidas correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DnaStats.class,
                                    example = "{\n" +
                                            "  \"countMutantDna\": 10,\n" +
                                            "  \"countHumanDna\": 20,\n" +
                                            "  \"ratio\": 0.5\n" +
                                            "}")))
    })
    @GetMapping("/stats") // Define una solicitud GET a "/mutant/stats".
    public ResponseEntity<?> getStats() {
        try {

            DnaStats stats = mutantService.getStats(); // Llama al servicio para obtener estadísticas de ADN.
            // Retorna un estado HTTP 200 OK con las estadísticas en caso de éxito.
            return ResponseEntity.ok(stats);
        } catch (Exception e){
            // Retorna un estado HTTP 500 INTERNAL_SERVER_ERROR si ocurre algún error en la verificación.
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Intente mas tarde");
        }
    }
    

}