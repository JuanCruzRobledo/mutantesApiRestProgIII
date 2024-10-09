package com.juan.parcialmutantesprogiii.domain.dtos;

import com.juan.parcialmutantesprogiii.validators.ValidDna;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequest {

    @ValidDna
    @Valid
    private String[] dna;
}
