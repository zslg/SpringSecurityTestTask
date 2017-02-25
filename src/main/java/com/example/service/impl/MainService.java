package com.example.service.impl;

import com.example.config.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


public class MainService {

    @Autowired
    private Validator validator;

    protected void validate(Object object,Class ... groups){
        Set<ConstraintViolation<Object>> validate = validator.validate(object, groups);
        if (!validate.isEmpty()) {
            throw new ValidationException(validate);
        }
    }

}
