package com.kadiraksoy.museo_vr.mapper;

import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.dto.request.TravelRequest;
import com.kadiraksoy.museo_vr.dto.response.TravelImageResponse;
import com.kadiraksoy.museo_vr.dto.response.TravelResponse;
import com.kadiraksoy.museo_vr.model.Travel;
import com.kadiraksoy.museo_vr.model.TravelImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class TravelMapper {



    public Travel travelRequestToTravel(TravelRequest travelRequest){
        Travel travel = new Travel();
        travel.setTitle(travelRequest.getTitle());
        travel.setDescription(travelRequest.getDescription());
        travel.setCreatedAt(LocalDateTime.now());
        travel.setUpdatedAt(LocalDateTime.now());

        return travel;
    }

    public TravelResponse travelToTravelResponse(Travel travel){
        TravelResponse travelResponse = new TravelResponse();
        travelResponse.setId(travel.getId());
        travelResponse.setTitle(travel.getTitle());
        travelResponse.setDescription(travel.getDescription());
        travelResponse.setCreatedAt(travel.getCreatedAt());
        travelResponse.setUpdatedAt(travel.getUpdatedAt());
        return travelResponse;
    }


    public TravelImageResponse travelImageToTravelImageResponse(TravelImage travelImage){
        TravelImageResponse travelImageResponse = new TravelImageResponse();
        travelImageResponse.setId(travelImage.getId());
        travelImageResponse.setName(travelImage.getName());
        travelImageResponse.setType(travelImage.getType());
        travelImageResponse.setContent(travelImage.getContent());
        return travelImageResponse;
    }






}
