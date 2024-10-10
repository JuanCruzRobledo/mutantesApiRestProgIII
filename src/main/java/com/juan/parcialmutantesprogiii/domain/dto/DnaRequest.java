package com.juan.parcialmutantesprogiii.domain.dto;

import com.juan.parcialmutantesprogiii.validators.ValidDna;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequest {
    @Schema(description = "Secuencia de ADN", example = "[\"CATG\", \"TACG\", \"AGTC\", \"GATC\"]")
    @Valid
    @ValidDna
    private String[] dna;
}
