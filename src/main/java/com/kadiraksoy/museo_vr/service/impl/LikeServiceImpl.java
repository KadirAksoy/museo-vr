package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.dto.response.LikeResponse;
import com.kadiraksoy.museo_vr.exception.TravelNotFoundException;
import com.kadiraksoy.museo_vr.exception.UserNotFoundException;
import com.kadiraksoy.museo_vr.model.Like;
import com.kadiraksoy.museo_vr.model.Travel;
import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.repository.LikeRepository;
import com.kadiraksoy.museo_vr.repository.TravelRepository;
import com.kadiraksoy.museo_vr.service.LikeService;
import com.kadiraksoy.museo_vr.service.UserService;
import com.kadiraksoy.museo_vr.utils.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final UserService userService;
    private final TravelRepository travelRepository;
    private final LikeRepository likeRepository;

    @Override
    public void likeTravel(Long travelId, Long userId) {
        User user = getUserBySecurityContext();
        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);

        boolean alreadyLiked = likeRepository.existsByTravelAndUser(travel, user);
        if (alreadyLiked) {
            throw new IllegalStateException("This travel is already liked by the user");
        }

        Like like = new Like();
        like.setTravel(travel);
        like.setUser(user);
        like.setCreatedAt(LocalDateTime.now());
        like.setUpdatedAt(LocalDateTime.now());

        likeRepository.save(like);
    }

    @Override
    public int getLikesCountForTravel(Long travelId) {
        return likeRepository.countByTravelId(travelId);
    }


    @Override
    public void unlikeTravel(Long travelId, Long userId) {
        User user = getUserBySecurityContext();
        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(TravelNotFoundException::new);

        Like like = likeRepository.findByTravelAndUser(travel, user)
                .orElseThrow(() -> new IllegalStateException("Like not found"));

        likeRepository.delete(like);
    }

    @Override
    @Transactional
    public List<LikeResponse> getLikesForUser(Long userId) {

        List<Like> likes = likeRepository.findAllByUserId(userId);
        List<LikeResponse> likeResponses = new ArrayList<>();
        for (Like like : likes) {
            likeResponses.add(LikeResponse.builder()
                            .travelId(like.getTravel().getId())
                            .userId(userId)
                            .build());
        }
        return likeResponses;
    }

    @Override
    @Transactional
    public List<LikeResponse> getLikesUserIdForTravel(Long travelId) {
        List<Like> likes = likeRepository.findAllByTravelId(travelId);
        List<LikeResponse> likeResponses = new ArrayList<>();
        for (Like like : likes) {
            likeResponses.add(LikeResponse.builder()
                            .userId(like.getUser().getId())
                            .travelId(travelId)
                            .build());
        }
        return likeResponses;
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
