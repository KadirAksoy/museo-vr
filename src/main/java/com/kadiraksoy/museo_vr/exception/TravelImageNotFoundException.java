package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class TravelImageNotFoundException extends BaseException{
    public TravelImageNotFoundException() {
        super(new ErrorResponse("TRAVEL_IMAGE_NOT_FOUND",
                "Travel image not found"));
    }
}
