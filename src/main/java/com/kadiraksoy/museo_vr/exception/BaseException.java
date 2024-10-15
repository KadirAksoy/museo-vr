package com.kadiraksoy.museo_vr.exception;


import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class BaseException extends RuntimeException{

    private final ErrorResponse errorResponse;

    public BaseException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
