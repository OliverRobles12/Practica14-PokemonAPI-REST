package com.example.PokemonApi.exceptions;

import com.example.PokemonApi.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException exception) {
        List<String> detalles = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            detalles.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ErrorDTO errorDTO = new ErrorDTO(
                "Error de validación en los datos enviados",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> manejarJsonInvalido(HttpMessageNotReadableException exception) {
        List<String> detalles = new ArrayList<>();
        detalles.add("El cuerpo de la petición no tiene un formato JSON o de datos válido");

        ErrorDTO errorDTO = new ErrorDTO(
                "Petición mal formada",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> manejarRecursoNoEncontrado(ResourceNotFoundException exception) {
        List<String> detalles = new ArrayList<>();
        detalles.add(exception.getMessage());

        ErrorDTO errorDTO = new ErrorDTO(
                "Recurso no encontrado",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarExcepcionGlobal(Exception exception) {
        List<String> detalles = new ArrayList<>();
        detalles.add(exception.getLocalizedMessage());

        ErrorDTO errorDTO = new ErrorDTO(
                "Ocurrió un error interno en el servidor",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
