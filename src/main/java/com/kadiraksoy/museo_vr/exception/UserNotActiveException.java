package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class UserNotActiveException extends BaseException {
    public UserNotActiveException( ) {
        super(new ErrorLog("USER_NOT_ACTIVE",
                "User is not active. Please activate your account and try again."));
    }
}
