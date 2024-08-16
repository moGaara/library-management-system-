package com.Library.libraryManagement.validator;

import com.Library.libraryManagement.exceptions.NotValidObject;
import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T>
{
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(T validationObject)
    {
       Set<ConstraintViolation<T>> violations = validator.validate(validationObject);
       if(!violations.isEmpty())
       {
           Set<String> errors = violations.stream()
                   .map(ConstraintViolation::getMessage)
                   .collect(Collectors.toSet());
           throw new NotValidObject(errors);
       }
    }
}
