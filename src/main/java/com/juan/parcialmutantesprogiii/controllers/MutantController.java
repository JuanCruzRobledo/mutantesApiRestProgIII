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

    @PostMapping()
    public ResponseEntity<Void> isMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
        return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<DnaStats> getStats() {
        DnaStats stats = mutantService.getStats();
        return ResponseEntity.ok(stats);
    }
}