package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class CommentNotFoundException extends BaseException{
    public CommentNotFoundException() {
        super(new ErrorResponse("COMMENT_NOT_FOUND", "Comment not found"));
    }
}
