package com.brabos.bahia.instagram.test.resources.exceptions;

import com.brabos.bahia.instagram.test.services.exceptions.AuthorizationException;
import com.brabos.bahia.instagram.test.services.exceptions.BadObjectFormation;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
        StandardError standardError = new StandardError(new Date(), "Página não encontrada", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        String errors = null;
        ValidationError error = new ValidationError(new Date(),"Erro de validação", null);

        for(FieldError x : e.getBindingResult().getFieldErrors() ){
            error.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> validation(HttpMessageNotReadableException e, HttpServletRequest request){
        String errors = null;
        ValidationError error = new ValidationError(new Date(), "Tipos de valores inválidos", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> parameters(MissingServletRequestParameterException e, HttpServletRequest request){
        StandardError standardError = new StandardError(new Date(), "Parametros requeridos: "
                + e.getParameterName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(BadObjectFormation.class)
    public ResponseEntity<StandardError> badObjectFormation(BadObjectFormation e, HttpServletRequest request){
        StandardError standardError = new StandardError(new Date(), "Campos ááá com nomes inválidos", "Campo: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
        StandardError standardError = new StandardError(new Date(), "Não autorizado", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> runtime(RuntimeException e, HttpServletRequest request){
        StandardError standardError = new StandardError(new Date(), "Erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }
}
