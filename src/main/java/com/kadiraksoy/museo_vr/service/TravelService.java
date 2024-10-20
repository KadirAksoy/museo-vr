package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.dto.request.TravelRequest;
import com.kadiraksoy.museo_vr.dto.response.TravelDetailsResponse;
import com.kadiraksoy.museo_vr.dto.response.TravelResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TravelService {

     TravelResponse createTravel(TravelRequest travelRequest, MultipartFile file) throws IOException;
     TravelResponse updateTravel(Long id, TravelRequest travelRequest,MultipartFile file) throws IOException;
     List<TravelResponse> getTravels();
     TravelDetailsResponse getTravelById(Long id);
     List<TravelResponse> getTravelsByUserId(Long userId);
     List<TravelResponse> getTravelsSortedByLikes();
     TravelResponse addImagesToTravel(Long travelId, MultipartFile image, TravelImageRequest content) throws IOException;
     void deleteTravel(Long id);
     void deleteTravelImagesFromTravel(Long travelId, Long imageId);
     TravelResponse updateImagesToTravel(Long travelId, Long imageId, MultipartFile image, TravelImageRequest content) throws IOException;

}
