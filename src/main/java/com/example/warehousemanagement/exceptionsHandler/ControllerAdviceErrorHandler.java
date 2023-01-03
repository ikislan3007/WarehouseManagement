package com.example.warehousemanagement.exceptionsHandler;

import com.example.warehousemanagement.exceptionsHandler.exceptions.ContentTypeNotSupportedException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.IncorrectPasswordException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.ResourceNotFoundException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.UserNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviceErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onConstraintViolationException(ConstraintViolationException constraintViolationException) {
        List<Violation> violations = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            violations.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return new ErrorResponse(violations);
    }

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

    @ExceptionHandler({IncorrectPasswordException.class, ContentTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PasswordViolation handleIncorrectPasswordExceptionException(RuntimeException exception) {
        return new PasswordViolation(exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<Violation> violations = new ArrayList<>();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ErrorResponse(violations);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(sqlIntegrityConstraintViolationException.getSQLState(), sqlIntegrityConstraintViolationException.getMessage()));

        return new ErrorResponse(violations);
    }

    private record Violation(String field, String message) {

    }

    private record ErrorResponse(List<Violation> violations) {

    }

    private record PasswordViolation(String message) {

    }

}
