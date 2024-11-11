package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class EmailNotFoundException extends BaseException{
    public EmailNotFoundException(String email) {
        super(new ErrorLog("EMAIL_NOT_FOUND", "Email address not found" + email));
    }
}
