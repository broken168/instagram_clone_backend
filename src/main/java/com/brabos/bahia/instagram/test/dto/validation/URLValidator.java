package com.brabos.bahia.instagram.test.dto.validation;

import com.brabos.bahia.instagram.test.resources.exceptions.FieldMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class URLValidator implements ConstraintValidator<URL, String> {

    @Override
    public void initialize(URL constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        FieldMessage error = null;
        if(!s.contains("http://") || !s.contains("https://") ){
            error = new FieldMessage("imageUrl", "URL inválida");

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error.getMessage())
                    .addPropertyNode(error.getFieldName())
                    .addConstraintViolation();
        }
        return error != null;
    }
}
