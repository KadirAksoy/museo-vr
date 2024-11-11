package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String email) {
        super(new ErrorLog("USER_NOT_FOUND", "User not found:" + email));
    }
}
