package com.juan.parcialmutantesprogiii.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {

    @Override
    public void initialize(ValidDna constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if (dna == null || dna.length == 0) {
            return false;
        }

        int length = dna.length;

        for (String row : dna) {
            if (row.length() != length || !row.matches("[ATCG]+")) {
                return false; // Falso si alguna cadena no cumple las reglas.
            }
        }

        return true; // Verificado como matriz NxN y con caracteres válidos.
    }
}

