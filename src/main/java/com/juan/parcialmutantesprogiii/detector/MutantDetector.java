package com.juan.parcialmutantesprogiii.detector;

import java.util.ArrayList;
import java.util.List;

public class MutantDetector {
    private static final int MUTANT_SEQUENCE_LENGTH = 4;
    private int sequenceCount;

    public boolean isMutant(String[] dna) {
        sequenceCount = 0;
        List<int[]> hints = scanHints(dna);
        return processHints(dna, hints);
    }

    // Etapa 1: Escanear intermitentemente en busca de pistas de mutantes
    private List<int[]> scanHints(String[] dna) {
        int n = dna.length;
        List<int[]> hints = new ArrayList<>();

        // Escaneo intermitente: saltar uno (opción A) o dos casilleros (opción B)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j += 2) {  // Saltar uno o dos casilleros
                char current = dna[i].charAt(j);

                // Detectar posibles pistas
                if (j + 1 < n && current == dna[i].charAt(j + 1)) {  // Pista de dos caracteres iguales juntos
                    hints.add(new int[]{i, j, 1});  // Horizontal
                }
                if (i + 1 < n && current == dna[i + 1].charAt(j)) {  // Pista de dos caracteres iguales en vertical
                    hints.add(new int[]{i, j, 2});  // Vertical
                }
                if (i + 1 < n && j + 1 < n && current == dna[i + 1].charAt(j + 1)) {  // Pista de diagonal descendente
                    hints.add(new int[]{i, j, 3});  // Diagonal descendente
                }
                if (i + 1 < n && j - 1 >= 0 && current == dna[i + 1].charAt(j - 1)) {  // Pista de diagonal ascendente
                    hints.add(new int[]{i, j, 4});  // Diagonal ascendente
                }
            }
        }
        return hints;
    }

    // Etapa 2: Procesar pistas para confirmar cadenas mutantes
    private boolean processHints(String[] dna, List<int[]> hints) {
        for (int[] hint : hints) {
            int i = hint[0];
            int j = hint[1];
            int direction = hint[2];
            char base = dna[i].charAt(j);

            if (isValidMutantSequence(dna, i, j, direction, base)) {
                sequenceCount++;
                if (sequenceCount > 1) return true;  // ADN mutante si hay más de una secuencia
            }
        }
        return false;
    }

    // Validar secuencias de longitud 4 en la dirección dada
    private boolean isValidMutantSequence(String[] dna, int i, int j, int direction, char base) {
        int n = dna.length;
        for (int step = 1; step < MUTANT_SEQUENCE_LENGTH; step++) {
            switch (direction) {
                case 1:  // Horizontal
                    if (j + step >= n || dna[i].charAt(j + step) != base) return false;
                    break;
                case 2:  // Vertical
                    if (i + step >= n || dna[i + step].charAt(j) != base) return false;
                    break;
                case 3:  // Diagonal descendente
                    if (i + step >= n || j + step >= n || dna[i + step].charAt(j + step) != base) return false;
                    break;
                case 4:  // Diagonal ascendente
                    if (i + step >= n || j - step < 0 || dna[i + step].charAt(j - step) != base) return false;
                    break;
            }
        }
        return true;
    }
}