package com.juan.parcialmutantesprogiii.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dna {
    @Id
    private String dna; // Usamos el ADN como clave primaria para evitar duplicados
    private boolean isMutant;
}
