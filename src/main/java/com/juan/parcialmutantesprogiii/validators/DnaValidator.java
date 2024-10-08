package com.juan.parcialmutantesprogiii.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {
    private static final String VALID_CHARS = "ATCG";

    @Override
    public void initialize(ValidDna constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {

        System.out.println("Ejecutando validación de ADN...");
        // Validar el input
        if (dna == null || dna.length == 0) {
            return false;// Array nulo o vacío.
        }

        int n = dna.length;

        // Verificar si es un cuadrado NxN
        for (String row : dna) {
            // Verificar si es NxN y validar los caracteres en un solo bucle.
            if (row == null || row.length() != n) {
                return false;
            }
            // Verificar caracteres válidos
            for (char base : row.toCharArray()) {
                if (VALID_CHARS.indexOf(base) == -1) {
                    return false; // Caracter no válido.
                }
            }
        }


        return true; // Verificado como matriz NxN y con caracteres válidos.
    }
}

