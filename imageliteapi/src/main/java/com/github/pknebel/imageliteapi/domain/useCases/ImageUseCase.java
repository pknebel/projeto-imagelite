package com.github.pknebel.imageliteapi.domain.useCases;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;

public interface ImageUseCase {

    ImageEntity save(ImageEntity image);

}