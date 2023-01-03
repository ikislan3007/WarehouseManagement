package com.example.warehousemanagement.exceptionsHandler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class ContentTypeNotSupportedException extends RuntimeException {
 public ContentTypeNotSupportedException() {
        super("Content type not supported");
    }
}
