package com.example.warehousemanagement.exceptionsHandler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("Password entered incorrectly.");
    }
}
