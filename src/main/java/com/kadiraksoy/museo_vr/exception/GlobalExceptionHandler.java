package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;
import com.kadiraksoy.museo_vr.service.impl.LogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final LogServiceImpl logServiceImpl;

    public GlobalExceptionHandler(LogServiceImpl logServiceImpl) {
        this.logServiceImpl = logServiceImpl;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        ErrorResponse errorResponse = ex.getErrorResponse();
        logServiceImpl.logError(errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        logServiceImpl.logError(errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}