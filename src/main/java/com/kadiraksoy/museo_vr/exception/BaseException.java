package com.kadiraksoy.museo_vr.exception;


import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class BaseException extends RuntimeException{

    private final ErrorLog errorLog;

    public BaseException(ErrorLog errorLog) {
        super(errorLog.getMessage());
        this.errorLog = errorLog;
    }

    public ErrorLog getErrorResponse() {
        return errorLog;
    }
}
