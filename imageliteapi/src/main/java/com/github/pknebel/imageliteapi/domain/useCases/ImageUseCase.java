package com.github.pknebel.imageliteapi.domain.useCases;

import java.util.Optional;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;

public interface ImageUseCase {

    ImageEntity save(ImageEntity image);

    Optional<ImageEntity> findById(String id);

}