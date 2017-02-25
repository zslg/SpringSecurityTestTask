package com.example.config.exception;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ValidationException extends RuntimeException {

    private List<String> errors = new ArrayList<>();

    public ValidationException(Set<ConstraintViolation<Object>> validateResults) {
        if (validateResults != null && !validateResults.isEmpty()) {
            List<String> errors = new ArrayList<>(validateResults.size());
            for (ConstraintViolation<Object> cv : validateResults) {
                String message = String.format(
                        "Attention Error! property: [%s], value: [%s], message: [%s]",
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage());
                errors.add(message);
            }
            this.errors = errors;
        }
    }

    public ValidationException(String message) {
        this.errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }
}
