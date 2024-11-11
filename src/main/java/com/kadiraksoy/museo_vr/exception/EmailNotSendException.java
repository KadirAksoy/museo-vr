package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class EmailNotSendException extends BaseException{
    public EmailNotSendException(String email) {
        super(new ErrorLog("MAIL_NOT_SEND", "Mail not send to:" + email));
    }
}
