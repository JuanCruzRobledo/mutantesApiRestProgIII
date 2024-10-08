package com.juan.parcialmutantesprogiii.controllers;

import com.juan.parcialmutantesprogiii.domain.dtos.DnaStats;
import com.juan.parcialmutantesprogiii.services.MutantService;
import com.juan.parcialmutantesprogiii.domain.dtos.DnaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Matriz no debe ser: Vacia,Nula,NxM y los caracteres solo pueden ser A,T,C o G");
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<DnaStats> getStats() {
        DnaStats stats = mutantService.getStats();
        return ResponseEntity.ok(stats);
    }
}