package com.github.pknebel.imageliteapi.application.images;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @Autowired
    private ImagesMapper imagesMapper;

    @PostMapping
    public ResponseEntity<Void> save(
        @RequestParam("file") MultipartFile file,
        @RequestParam("name") String name,
        @RequestParam("tags") List<String> tags
        ) throws IOException{
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());

        ImageEntity image = imagesMapper.mapToImage(file, name, tags);
        ImageEntity savedImage = imageServiceImpl.save(image);
        URI imageURI = buildImageURL(savedImage);

        return ResponseEntity.created(imageURI).build();

    }
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id){
        var possibleImage = imageServiceImpl.findById(id);

        if (possibleImage.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(
        @RequestParam(required = false) String extension,
        @RequestParam(required = false) String query){

            var result = imageServiceImpl.search(ImageExtensionEnum.ofName(extension), query);

            var images = result.stream().map(image -> {
                var url = buildImageURL(image);
                return imagesMapper.imageToDto(image, url.toString());
            }).collect(Collectors.toList());

            return ResponseEntity.ok(images);

    }

    private URI buildImageURL(ImageEntity image){
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path(imagePath)
        .build()
        .toUri();

    }

}
