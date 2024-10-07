package com.juan.parcialmutantesprogiii.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequest {
    private String[] dna;
}
