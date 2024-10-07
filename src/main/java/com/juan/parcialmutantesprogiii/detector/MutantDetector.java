package com.juan.parcialmutantesprogiii.detector;


public class MutantDetector {
    private static final int MUTANT_SEQUENCE_LENGTH = 4;

    public boolean isMutant(String[] dna) {
        int sequenceCount = 0; // Contador de secuencias mutantes
        int n = dna.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char base = dna[i].charAt(j);

                // Verifica horizontal (hacia la derecha)
                if (j <= n - MUTANT_SEQUENCE_LENGTH && checkSequence(dna, i, j, 0, 1, base)) {
                    sequenceCount++;
                }

                // Verifica vertical (hacia abajo)
                if (i <= n - MUTANT_SEQUENCE_LENGTH && checkSequence(dna, i, j, 1, 0, base)) {
                    sequenceCount++;
                }

                // Verifica diagonal descendente (hacia abajo a la derecha)
                if (i <= n - MUTANT_SEQUENCE_LENGTH && j <= n - MUTANT_SEQUENCE_LENGTH && checkSequence(dna, i, j, 1, 1, base)) {
                    sequenceCount++;
                }

                // Verifica diagonal ascendente (hacia arriba a la derecha)
                if (i >= MUTANT_SEQUENCE_LENGTH - 1 && j <= n - MUTANT_SEQUENCE_LENGTH && checkSequence(dna, i, j, -1, 1, base)) {
                    sequenceCount++;
                }

                // Si ya se encontró más de una secuencia, es mutante
                if (sequenceCount > 1) {
                    return true; // ADN mutante si hay más de una secuencia
                }
            }
        }
        return false; // No es mutante si solo se encontró una o ninguna secuencia
    }

    // Verifica si hay una secuencia de 4 iguales en la dirección dada
    private boolean checkSequence(String[] dna, int row, int col, int rowIncrement, int colIncrement, char base) {
        for (int step = 0; step < MUTANT_SEQUENCE_LENGTH; step++) {
            int newRow = row + step * rowIncrement;
            int newCol = col + step * colIncrement;

            // Verifica si está fuera de los límites
            if (newRow < 0 || newRow >= dna.length || newCol < 0 || newCol >= dna.length) {
                return false;
            }

            if (dna[newRow].charAt(newCol) != base) {
                return false;
            }
        }
        return true;
    }
}
