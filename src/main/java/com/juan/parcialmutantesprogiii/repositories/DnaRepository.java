package com.juan.parcialmutantesprogiii.repositories;

import com.juan.parcialmutantesprogiii.domain.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Indica que esta interfaz es un componente de acceso a datos y será manejado por Spring
public interface DnaRepository extends JpaRepository<Dna, Long> {
    Optional<Dna> findByDnaSequence(String dnaSequence);
    long countByIsMutant(boolean isMutant);
}
