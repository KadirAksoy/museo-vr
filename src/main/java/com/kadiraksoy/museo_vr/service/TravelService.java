package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.model.Travel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TravelService {

     Travel createTravel(Travel travel);
     Travel updateTravel(Travel travel);
     List<Travel> getTravels();
     Travel getTravelById(Long id);
     List<Travel> getTravelsByUserId(Long userId);
     List<Travel> getTravelsSortedByLikes();
     Travel addImagesToTravel(Long travelId, List<MultipartFile> images);
     void deleteTravel(Long id);

}
