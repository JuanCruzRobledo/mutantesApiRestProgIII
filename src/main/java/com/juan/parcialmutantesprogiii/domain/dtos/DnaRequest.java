package com.juan.parcialmutantesprogiii.domain.dtos;

import com.juan.parcialmutantesprogiii.validators.ValidDna;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequest {
    @ValidDna
    @NotNull(message = "El ADN no puede ser null.")
    @NotEmpty(message = "El ADN no puede estar vacío.")
    private String[] dna;
}
