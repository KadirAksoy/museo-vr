package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;
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
    public ResponseEntity<ErrorLog> handleBaseException(BaseException ex) {
        ErrorLog errorLog = ex.getErrorResponse();
        logServiceImpl.logError(errorLog);
        return new ResponseEntity<>(errorLog, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorLog> handleGenericException(Exception ex) {
        ErrorLog errorLog = new ErrorLog("INTERNAL_SERVER_ERROR", ex.getMessage());
        logServiceImpl.logError(errorLog);
        return new ResponseEntity<>(errorLog, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}