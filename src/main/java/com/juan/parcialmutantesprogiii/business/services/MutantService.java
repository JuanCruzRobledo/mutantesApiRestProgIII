package com.juan.parcialmutantesprogiii.business.services;

import com.juan.parcialmutantesprogiii.business.detector.MutantDetector;
import com.juan.parcialmutantesprogiii.domain.dto.DnaStats;
import com.juan.parcialmutantesprogiii.domain.entities.Dna;
import com.juan.parcialmutantesprogiii.repositories.DnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Marca esta clase como un servicio de Spring.
public class MutantService {

    private final DnaRepository dnaRepository; // Inyección de dependencias para la interfaz DnaRepository, declarada como final para garantizar la inmutabilidad.
    private final MutantDetector mutantDetector; // Inyección de MutantDetector
    @Autowired
    public MutantService(DnaRepository dnaRepository, MutantDetector mutantDetector){ // Constructor para inyectar el repositorio de ADN.
        this.dnaRepository = dnaRepository;
        this.mutantDetector = mutantDetector;
    }


    // Método para verificar si una secuencia de ADN es mutante
    public boolean isMutant(String[] dna) {

        String dnaSequence = String.join(",", dna); // Convertir array a string único
        Optional<Dna> existingRecord = dnaRepository.findByDnaSequence(dnaSequence);

        // Si ya existe el registro en la base de datos, devolver el resultado guardado
        if (existingRecord.isPresent()) {
            return existingRecord.get().isMutant();
        }

        // Si no existe, verifica y guarda el resultado en la base de datos

        boolean isMutant = mutantDetector.isMutant(dna);
        Dna record = new Dna(null, dnaSequence, isMutant);
        dnaRepository.save(record);

        return isMutant;
    }


    // Método para calcular las estadísticas de las secuencias de ADN
    public DnaStats getStats() {
        long mutants = dnaRepository.countByIsMutant(true); // Cuenta el número de registros mutantes en la base de datos.
        long humans = dnaRepository.countByIsMutant(false); // Cuenta el número de registros humanos en la base de datos.
        double ratio = (humans == 0) ? 0 : (double) mutants / humans; // Calcula el ratio de mutantes a humanos, evitando la división por cero.
        return new DnaStats(mutants, humans, ratio); // Devuelve un objeto DnaStats.
    }
}