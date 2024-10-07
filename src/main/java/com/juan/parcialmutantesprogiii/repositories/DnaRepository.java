package com.juan.parcialmutantesprogiii.repositories;

import com.juan.parcialmutantesprogiii.domain.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<Dna, String> {
    long countByIsMutant(boolean isMutant);
}
