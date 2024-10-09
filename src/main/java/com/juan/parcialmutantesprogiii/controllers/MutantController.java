package com.juan.parcialmutantesprogiii.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.juan.parcialmutantesprogiii.domain.dtos.DnaStats;
import com.juan.parcialmutantesprogiii.services.MutantService;
import com.juan.parcialmutantesprogiii.domain.dtos.DnaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
public class MutantController {
    @Autowired
    private final MutantService mutantService;

    @PostMapping("")
    public ResponseEntity<Object> isMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        try {
            boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
            return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Intente mas tarde");
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<DnaStats> getStats() {
        DnaStats stats = mutantService.getStats();
        return ResponseEntity.ok(stats);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Error en la validación: " + ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleJsonMappingExceptions(JsonMappingException ex) {
        return ResponseEntity.badRequest().body("Formato de entrada inválido. Asegúrese de enviar un arreglo de cadenas.");
    }

}