package com.github.pknebel.imageliteapi.application.images;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.useCases.ImageUseCase;
import com.github.pknebel.imageliteapi.infra.repositories.ImageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageUseCase{

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public ImageEntity save(ImageEntity image) {
        return imageRepository.save(image);
    }

    @Override
    public Optional<ImageEntity> findById(String id) {
        return imageRepository.findById(id);
    }
    

}
