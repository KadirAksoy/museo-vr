package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.exception.TravelImageNotFoundException;
import com.kadiraksoy.museo_vr.model.TravelImage;
import com.kadiraksoy.museo_vr.repository.TravelImageRepository;
import com.kadiraksoy.museo_vr.service.TravelImageService;
import com.kadiraksoy.museo_vr.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TravelImageServiceImpl implements TravelImageService {

    private final TravelImageRepository travelImageRepository;


    @Override
    public TravelImage saveTravelImage(TravelImageRequest travelImageRequest, MultipartFile file) throws IOException {

        byte [] image = ImageUtils.compressImage(file.getBytes());

        TravelImage travelImage = new TravelImage();
        travelImage.setName(file.getOriginalFilename());
        travelImage.setType(file.getContentType());
        travelImage.setImageData(image);
        travelImage.setContent(travelImageRequest.getContent());
        travelImage.setCreatedAt(LocalDateTime.now());
        travelImage.setUpdatedAt(LocalDateTime.now());

        return travelImageRepository.save(travelImage);
    }

    @Override
    public TravelImage updateTravelImage(Long id, MultipartFile file, String content) throws IOException {
        TravelImage updatedtravelImage = travelImageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image data not found with id: " + id));

        byte[] compressedImage = ImageUtils.compressImage(file.getBytes());
        updatedtravelImage.setName(file.getOriginalFilename());
        updatedtravelImage.setType(file.getContentType());
        updatedtravelImage.setImageData(compressedImage);
        updatedtravelImage.setContent(content);
        updatedtravelImage.setUpdatedAt(LocalDateTime.now());

        return travelImageRepository.save(updatedtravelImage);
    }

    @Override
    public byte[] getImage(Long id) {
        var compressedImage = travelImageRepository.findById(id)
                .orElseThrow(TravelImageNotFoundException::new);
        return ImageUtils.decompressImage(compressedImage.getImageData());
    }
}
