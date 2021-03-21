package com.brabos.bahia.instagram.test.resources.exceptions;

import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
        StandardError standardError = new StandardError(new Date(), "Página não encontrada", exception.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
}
