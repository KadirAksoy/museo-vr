package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class CommentNotFoundException extends BaseException{
    public CommentNotFoundException() {
        super(new ErrorLog("COMMENT_NOT_FOUND", "Comment not found"));
    }
}
