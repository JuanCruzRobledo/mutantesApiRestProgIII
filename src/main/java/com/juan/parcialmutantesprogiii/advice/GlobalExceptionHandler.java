package com.juan.parcialmutantesprogiii.advice;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //En caso de que las validaciones no sean correctas muestra
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String,String> mapErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String clave = ((FieldError)error).getField();
            String valor = error.getDefaultMessage();
            mapErrors.put(clave,valor);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La solicitud de ADN no es válida." + mapErrors);
    }
}
