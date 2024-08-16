package com.Library.libraryManagement.handler;


import com.Library.libraryManagement.exceptions.NotValidObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
