package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String email) {
        super(new ErrorResponse("USER_NOT_FOUND", "User not found:" + email));
    }
}
