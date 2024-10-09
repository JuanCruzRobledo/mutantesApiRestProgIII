package com.juan.parcialmutantesprogiii.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {
    private static final String VALID_CHARS = "ATCG";
    private String message ;

    @Override
    public void initialize(ValidDna constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        // Validar el input

        // Limpiar los mensajes previos
        context.disableDefaultConstraintViolation();

        if (dna == null || dna.length == 0) {
            context.buildConstraintViolationWithTemplate("El array es nulo o está vacío")
                    .addConstraintViolation();
            return false; // Array nulo o vacío
        }

        int n = dna.length;

        // Verificar si es un cuadrado NxN
        for (String row : dna) {
            // Validar los caracteres.
            if (row == null) {
                context.buildConstraintViolationWithTemplate("Contiene valores nulos.")
                        .addConstraintViolation();
                return false;
            } else if (row.length() != n) {  // Verificar si es NxN.
                context.buildConstraintViolationWithTemplate("El array no es N x N.")
                        .addConstraintViolation();
                return false;
            }

            // Verificar caracteres válidos
            for (char base : row.toCharArray()) {
                if (VALID_CHARS.indexOf(base) == -1) {
                    context.buildConstraintViolationWithTemplate("Contiene caracteres que no son " + VALID_CHARS)
                            .addConstraintViolation();
                    return false;
                }
            }
        }

        return true; // Verificado como matriz NxN y con caracteres válidos.
    }

}

