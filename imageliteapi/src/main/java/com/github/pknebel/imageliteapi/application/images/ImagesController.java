package com.github.pknebel.imageliteapi.application.images;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService imageService;

    private final ImagesMapper imagesMapper;

    @PostMapping
    public ResponseEntity save(
        @RequestParam("file") MultipartFile file,
        @RequestParam("name") String name,
        @RequestParam("tags") List<String> tags
        ) throws IOException{
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());

        ImageEntity image = imagesMapper.mapToImage(file, name, tags);
        ImageEntity savedImage = imageService.save(image);
        URI imageURI = buildImageURL(savedImage);

        return ResponseEntity.created(imageURI).build();

    }

    private URI buildImageURL(ImageEntity image){
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path(imagePath)
        .build()
        .toUri();

    }

}
