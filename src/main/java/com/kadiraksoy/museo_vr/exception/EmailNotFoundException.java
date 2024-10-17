package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class EmailNotFoundException extends BaseException{
    public EmailNotFoundException(String email) {
        super(new ErrorResponse("EMAIL_NOT_FOUND", "Email address not found" + email));
    }
}
