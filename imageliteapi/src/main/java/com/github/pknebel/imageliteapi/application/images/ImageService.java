package com.github.pknebel.imageliteapi.application.images;

import org.springframework.stereotype.Service;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.useCases.ImageUseCase;
import com.github.pknebel.imageliteapi.infra.repositories.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageUseCase{

    private final ImageRepository imageRepository;

    @Override
    public ImageEntity save(ImageEntity image) {
        return imageRepository.save(image);
    }

    

}
