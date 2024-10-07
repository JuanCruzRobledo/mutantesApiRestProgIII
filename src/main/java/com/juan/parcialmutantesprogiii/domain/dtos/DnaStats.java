package com.juan.parcialmutantesprogiii.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DnaStats {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}