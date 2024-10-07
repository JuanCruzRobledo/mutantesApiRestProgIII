package com.juan.parcialmutantesprogiii.business.detector;

public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;
    private static final int MIN_MUTANT_SEQUENCES = 2;

    public boolean isMutant(String[] dna) {
        int sequencesFound = 0;

        int n = dna.length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Verifica horizontal, vertical, diagonal derecha y diagonal izquierda
                if (hasHorizontalSequence(dna, row, col) ||
                        hasVerticalSequence(dna, row, col) ||
                        hasDiagonalRightSequence(dna, row, col) ||
                        hasDiagonalLeftSequence(dna, row, col)) {
                    sequencesFound++;
                }

                if (sequencesFound >= MIN_MUTANT_SEQUENCES) {
                    return true;
                }
            }
        }
        return false;
    }

    // Métodos para verificar las secuencias en diferentes direcciones
    private boolean hasHorizontalSequence(String[] dna, int row, int col) {
        int n = dna.length;
        if (col + SEQUENCE_LENGTH > n) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row].charAt(col + i) != base) return false;
        }
        return true;
    }

    private boolean hasVerticalSequence(String[] dna, int row, int col) {
        int n = dna.length;
        if (row + SEQUENCE_LENGTH > n) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row + i].charAt(col) != base) return false;
        }
        return true;
    }

    private boolean hasDiagonalRightSequence(String[] dna, int row, int col) {
        int n = dna.length;
        if (row + SEQUENCE_LENGTH > n || col + SEQUENCE_LENGTH > n) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row + i].charAt(col + i) != base) return false;
        }
        return true;
    }

    private boolean hasDiagonalLeftSequence(String[] dna, int row, int col) {
        int n = dna.length;
        if (row + SEQUENCE_LENGTH > n || col - SEQUENCE_LENGTH < -1) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row + i].charAt(col - i) != base) return false;
        }
        return true;
    }
}
