package com.kadiraksoy.museo_vr.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class UserNotActiveException extends BaseException {
    public UserNotActiveException( ) {
        super(new ErrorResponse("USER_NOT_ACTIVE",
                "User is not active. Please activate your account and try again."));
    }
}
