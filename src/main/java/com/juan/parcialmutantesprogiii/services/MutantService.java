package com.juan.parcialmutantesprogiii.services;

import com.juan.parcialmutantesprogiii.detector.MutantDetector;
import com.juan.parcialmutantesprogiii.domain.dtos.DnaStats;
import com.juan.parcialmutantesprogiii.domain.entities.Dna;
import com.juan.parcialmutantesprogiii.repositories.DnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantService {

    @Autowired
    private final DnaRepository dnaRepository;


    // Método para verificar si una secuencia de ADN es mutante
    public boolean isMutant(String[] dna) {

        String dnaSequence = String.join(",", dna); // Convertir array a string único
        Optional<Dna> existingRecord = dnaRepository.findByDnaSequence(dnaSequence);

        // Si ya existe el registro en la base de datos, devolver el resultado guardado
        if (existingRecord.isPresent()) {
            return existingRecord.get().isMutant();
        }

        // Si no existe, verifica y guarda el resultado en la base de datos

        MutantDetector detector = new MutantDetector();
        boolean isMutant = detector.isMutant(dna);
        Dna record = new Dna(null, dnaSequence, isMutant);
        dnaRepository.save(record);

        return isMutant;
    }


    // Método para calcular las estadísticas de las secuencias de ADN
    public DnaStats getStats() {
        long mutants = dnaRepository.countByIsMutant(true);
        long humans = dnaRepository.countByIsMutant(false);
        double ratio = (humans == 0) ? 0 : (double) mutants / humans;
        return new DnaStats(mutants, humans, ratio);
    }
}