package com.example.warehousemanagement.exceptionsHandler;

import com.example.warehousemanagement.exceptionsHandler.exceptions.IncorrectPasswordException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.ResourceNotFoundException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviceErrorHandler {

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Violation handleUserNotFoundException(RuntimeException exception) {
        return new Violation("username", exception.getMessage());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Violation handleResourceNotFoundException(RuntimeException exception) {
        return new Violation("id", exception.getMessage());
    }

    @ExceptionHandler({IncorrectPasswordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PasswordViolation handleIncorrectPasswordExceptionException(RuntimeException exception) {
        return new PasswordViolation(exception.getMessage());
    }


    private record Violation(String field, String message) {

    }

    private record PasswordViolation(String message) {

    }

}
