package com.Library.libraryManagement.handler;


import com.Library.libraryManagement.exceptions.NotValidObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException exception)
    {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(NotValidObject.class)
    public ResponseEntity<?> NotValidObjectException(NotValidObject exception)
    {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        Map<String ,String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) ->
                {
                    String filedName = ((FieldError) error).getField();
                    String messege = error.getDefaultMessage();
                    errors.put(filedName,messege);
                }
                );
        return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);

    }
}
