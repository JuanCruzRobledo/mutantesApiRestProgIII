package com.juan.parcialmutantesprogiii.domain.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DnaStats {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}