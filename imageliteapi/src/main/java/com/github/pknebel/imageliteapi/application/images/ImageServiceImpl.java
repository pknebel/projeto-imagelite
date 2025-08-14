package com.github.pknebel.imageliteapi.application.images;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;
import com.github.pknebel.imageliteapi.domain.useCases.ImageUseCase;
import com.github.pknebel.imageliteapi.infra.repositories.ImageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageUseCase{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public ImageEntity save(ImageEntity image) {
        return imageRepository.save(image);
    }

    @Override
    public Optional<ImageEntity> findById(String id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<ImageEntity> search(ImageExtensionEnum extension, String query) {
        return imageRepository.findByExtensionAndNameOrTagsLike(extension, query);
    }

    
}
