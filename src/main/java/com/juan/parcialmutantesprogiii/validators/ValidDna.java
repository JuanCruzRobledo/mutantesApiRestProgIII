package com.juan.parcialmutantesprogiii.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DnaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDna {
    String message() default "Secuencia de ADN no valida";
    Class <?> [] groups() default{};
    Class <? extends Payload> [] payload() default{};
}
