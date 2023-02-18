/* Copyright 2020 the original author or authors. All rights reserved. */
package com.challenge.mindata.exception.handler;

import com.challenge.mindata.exception.ApiError;
import com.challenge.mindata.exception.exception.ItemNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<Object> handleItemNotFoundException(Exception ex, WebRequest request) {
        return notFound(ex, request);
    }

    private ResponseEntity<Object> notFound(Exception ex, WebRequest request) {
        var apiError = new ApiError(NOT_FOUND, ex.getMessage());

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

}
