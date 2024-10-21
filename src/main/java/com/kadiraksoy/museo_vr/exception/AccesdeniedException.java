package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class AccesdeniedException extends BaseException{
    public AccesdeniedException() {
        super(new ErrorResponse("ACCESS_DENIED", "Access denied."));
    }
}
