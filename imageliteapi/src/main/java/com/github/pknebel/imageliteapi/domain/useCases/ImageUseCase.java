package com.github.pknebel.imageliteapi.domain.useCases;

import java.util.List;
import java.util.Optional;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

public interface ImageUseCase {

    ImageEntity save(ImageEntity image);

    Optional<ImageEntity> findById(String id);

    List<ImageEntity> search(ImageExtensionEnum extension, String query);


}