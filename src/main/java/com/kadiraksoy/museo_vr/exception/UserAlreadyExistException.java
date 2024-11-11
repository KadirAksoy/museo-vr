package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class UserAlreadyExistException extends BaseException {
    public UserAlreadyExistException(String email) {
        super(new ErrorLog("USER_ALREADY_EXIST", "User already exists with email:"+ email));
    }
}
