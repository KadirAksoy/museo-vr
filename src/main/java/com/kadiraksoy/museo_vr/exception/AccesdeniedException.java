package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class AccesdeniedException extends BaseException{
    public AccesdeniedException() {
        super(new ErrorLog("ACCESS_DENIED", "Access denied."));
    }
}
