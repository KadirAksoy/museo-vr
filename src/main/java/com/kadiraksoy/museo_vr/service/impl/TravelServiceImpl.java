package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.dto.request.TravelRequest;
import com.kadiraksoy.museo_vr.dto.response.TravelDetailsResponse;
import com.kadiraksoy.museo_vr.dto.response.TravelImageResponse;
import com.kadiraksoy.museo_vr.dto.response.TravelResponse;
import com.kadiraksoy.museo_vr.exception.AccesdeniedException;
import com.kadiraksoy.museo_vr.exception.TravelNotFoundException;
import com.kadiraksoy.museo_vr.exception.UserNotFoundException;
import com.kadiraksoy.museo_vr.mapper.TravelMapper;
import com.kadiraksoy.museo_vr.model.Travel;
import com.kadiraksoy.museo_vr.model.TravelImage;
import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.repository.TravelImageRepository;
import com.kadiraksoy.museo_vr.repository.TravelRepository;
import com.kadiraksoy.museo_vr.service.TravelImageService;
import com.kadiraksoy.museo_vr.service.TravelService;
import com.kadiraksoy.museo_vr.service.UserService;
import com.kadiraksoy.museo_vr.utils.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.kadiraksoy.museo_vr.exception.TravelImageNotFoundException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;



@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    private final UserService userService;
    private final TravelRepository travelRepository;
    private final TravelMapper travelMapper;
    private final TravelImageService travelImageService;

    @Override
    @Transactional
    public TravelResponse createTravel(TravelRequest travelRequest, MultipartFile file) throws IOException {
        User user = getUserBySecurityContext();

        TravelImage travelImage = travelImageService.saveTravelImage(travelRequest.getTravelImageRequest(), file);

        Travel travel = travelMapper.travelRequestToTravel(travelRequest);
        travel.setUser(user);
        travel.setContentImage(travelImage);

        // CascadeType: ALL olduğu için veri tabanına yazılır.
        travelImage.setTravel(travel);

        travelRepository.save(travel);
        return createTravelResponse(travel, travelImage);

    }

    // AOP tarafından kullanıcı doğrulama yapılacak
    @Override
    @Transactional
    public TravelResponse updateTravel(Long id, TravelRequest travelRequest, MultipartFile file) throws IOException {
        User user = getUserBySecurityContext();
        Travel existingTravel = travelRepository.findById(id).orElseThrow(TravelNotFoundException::new);
        if(!existingTravel.getUser().getId().equals(user.getId())){
            throw new AccesdeniedException();
        }
        TravelImage travelImage = travelImageService.updateTravelImage(existingTravel.getContentImage().getId(),
                file, travelRequest.getTravelImageRequest().getContent());

        existingTravel.setUpdatedAt(LocalDateTime.now());
        existingTravel.setDescription(travelRequest.getDescription());
        existingTravel.setTitle(travelRequest.getTitle());
        existingTravel.setContentImage(travelImage);

        travelRepository.save(existingTravel);
        return createTravelResponse(existingTravel, travelImage);
    }

    @Override
    @Transactional
    public List<TravelResponse> getTravels() {
        List<Travel> travels = travelRepository.findAll();
        if (travels.isEmpty()) {
            throw new TravelNotFoundException();
        }
        return travels.stream()
                .map(travel -> createTravelResponse(travel, travel.getContentImage()))
                .toList();
    }

    @Override
    @Transactional
    public TravelDetailsResponse getTravelById(Long id) {
        Travel travel = travelRepository.findById(id).orElseThrow(TravelNotFoundException::new);
        if(travel == null){
            throw new TravelNotFoundException();
        }
        TravelImage travelContentImage = travel.getContentImage();
        List<TravelImageResponse> travelImageResponses = travel.getImages()
                .stream()
                .map(travelMapper::travelImageToTravelImageResponse).toList();

        TravelResponse travelResponse = createTravelResponse(travel, travelContentImage);

        TravelDetailsResponse travelDetailsResponse = new TravelDetailsResponse();
        travelDetailsResponse.setTravelResponse(travelResponse);
        travelDetailsResponse.setTravelImageResponses(travelImageResponses);
        return travelDetailsResponse;
    }

    @Override
    @Transactional
    public List<TravelResponse> getTravelsByUserId(Long userId) {
        List<Travel> travels = travelRepository.findByUserId(userId);
        if (travels.isEmpty()) {
            throw new TravelNotFoundException();
        }
        return travels.stream()
                .map(travel -> createTravelResponse(travel, travel.getContentImage())).toList();
    }

    @Override
    @Transactional
    public List<TravelResponse> getMyTravels() {
        User user = getUserBySecurityContext();
        List<Travel> travels = travelRepository.findByUserId(user.getId());
        if (travels.isEmpty()) {
            throw new TravelNotFoundException();
        }
        return travels.stream()
                .map(travel -> createTravelResponse(travel, travel.getContentImage())).toList();
    }

    @Override
    @Transactional
    public List<TravelResponse> getTravelsSortedByLikes() {
        List<Travel> travels = travelRepository.findTop10ByOrderByLikesDesc();
        return travels.stream()
                .map(travel -> createTravelResponse(travel, travel.getContentImage())).toList();
    }

    @Override
    public TravelResponse addImagesToTravel(Long travelId, MultipartFile image, TravelImageRequest content) throws IOException {
        User user = getUserBySecurityContext();
        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);
        if(!travel.getUser().getId().equals(user.getId())){
            throw new AccesdeniedException();
        }

        TravelImage travelImage = travelImageService.saveTravelImage(content, image);
        travelImage.setTravel(travel);
        travel.getImages().add(travelImage);
        travelRepository.save(travel);

        return createTravelResponse(travel, travelImage);
    }

    @Override
    public void deleteTravel(Long id) {
        User user = getUserBySecurityContext();

        Travel travel = travelRepository.findById(id).orElseThrow(TravelNotFoundException::new);
        if(!travel.getUser().getId().equals(user.getId())){
            throw new AccesdeniedException();
        }
        travelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteTravelImagesFromTravel(Long travelId, Long imageId) {
        User user = getUserBySecurityContext();
        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);
        if (!travel.getUser().getId().equals(user.getId())) {
            throw new AccesdeniedException();
        }
        TravelImage travelImage = travel.getImages().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .orElseThrow(TravelImageNotFoundException::new);

        travelImage.setTravel(null);
        travelRepository.save(travel);
    }

    @Override
    @Transactional
    public TravelResponse updateImagesToTravel(Long travelId,
                                               Long imageId,
                                               MultipartFile image,
                                               TravelImageRequest travelImageRequest) throws IOException {
        User user = getUserBySecurityContext();
        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);
        if (!travel.getUser().getId().equals(user.getId())) {
            throw new AccesdeniedException();
        }
        TravelImage travelImage = travel.getImages().stream()
                .filter(image1 -> image1.getId().equals(imageId))
                .findFirst()
                .orElseThrow(TravelImageNotFoundException::new);
        travelImageService.updateTravelImage(travelImage.getId(), image, travelImageRequest.getContent());
        return createTravelResponse(travel, travelImage);
    }


    private TravelResponse createTravelResponse(Travel travel, TravelImage travelImage) {
        TravelResponse travelResponse = travelMapper.travelToTravelResponse(travel);
        travelResponse.setTravelImageResponse(travelMapper.travelImageToTravelImageResponse(travelImage));
        return travelResponse;
    }

    private User getUserBySecurityContext() {
        Long userId = AuthenticationUtils.getCurrentUserId();
        User user = userService.getUserById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

}
