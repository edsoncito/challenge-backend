package com.challenge.commons.exceptions;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProviderException.class)
    public ResponseEntity<ErrorDetail> handleProviderException(ProviderException ex) {ErrorDetail body = new ErrorDetail(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<ErrorDetail>(body , ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleUnexpected(Exception ex) {
        ErrorDetail response = new ErrorDetail("ERR-500", "Error interno del servidor", ex.getMessage());
        return new ResponseEntity<ErrorDetail>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<ErrorDetail> handleRedisError(RedisConnectionFailureException ex) {
        ErrorDetail response = new ErrorDetail("ERR-REDIS", "No se pudo conectar a Redis", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
