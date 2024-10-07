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
        // Validar el input
        if (dna == null || dna.length == 0) {
            throw new IllegalArgumentException("El ADN no puede ser null o vacío.");
        }

        int n = dna.length;

        // Verificar si es un cuadrado NxN
        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new IllegalArgumentException("El ADN debe ser un cuadrado NxN.");
            }
        }

        // Verificar caracteres válidos
        for (String row : dna) {
            for (char base : row.toCharArray()) {
                if ("ATCG".indexOf(base) == -1) {
                    throw new IllegalArgumentException("El ADN contiene caracteres inválidos.");
                }
            }
        }


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

    // Lógica para verificar secuencias de 4 letras en las direcciones especificadas
    private boolean checkIfMutant(String[] dna) {
            int filas = dna.length;
            int columnas = dna[0].length();
            int contadorSecuencias = 0;

            // Verificar horizontalmente
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j <= columnas - 4; j++) {
                    char caracterActual = dna[i].charAt(j);
                    if (dna[i].charAt(j + 1) == caracterActual && dna[i].charAt(j + 2) == caracterActual && dna[i].charAt(j + 3) == caracterActual) {
                        contadorSecuencias++;
                    }
                }
            }

            // Verificar verticalmente
            for (int i = 0; i <= filas - 4; i++) {
                for (int j = 0; j < columnas; j++) {
                    char caracterActual = dna[i].charAt(j);
                    if (dna[i + 1].charAt(j) == caracterActual && dna[i + 2].charAt(j) == caracterActual && dna[i + 3].charAt(j) == caracterActual) {
                        contadorSecuencias++;
                    }
                }
            }

            // Verificar diagonalmente (de izquierda a derecha y de derecha a izquierda)
            for (int i = 0; i <= filas - 4; i++) {
                for (int j = 0; j <= columnas - 4; j++) {
                    char caracterActual = dna[i].charAt(j);
                    if (dna[i + 1].charAt(j + 1) == caracterActual && dna[i + 2].charAt(j + 2) == caracterActual && dna[i + 3].charAt(j + 3) == caracterActual) {
                        contadorSecuencias++;
                    }
                    if (dna[i + 3].charAt(j) == caracterActual && dna[i + 2].charAt(j + 1) == caracterActual && dna[i + 1].charAt(j + 2) == caracterActual && dna[i].charAt(j + 3) == caracterActual) {
                        contadorSecuencias++;
                    }
                }
            }

            return contadorSecuencias > 1;
    }
    // Método para calcular las estadísticas de las secuencias de ADN
    public DnaStats getStats() {
        long mutants = dnaRepository.countByIsMutant(true);
        long humans = dnaRepository.countByIsMutant(false);
        double ratio = (humans == 0) ? 0 : (double) mutants / humans;
        return new DnaStats(mutants, humans, ratio);
    }
}