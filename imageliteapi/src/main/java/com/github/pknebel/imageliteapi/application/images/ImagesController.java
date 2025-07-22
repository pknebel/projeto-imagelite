package com.github.pknebel.imageliteapi.application.images;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/v1/images")
@Slf4j
public class ImagesController {

    @PostMapping
    public ResponseEntity save( @RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("tags") List<String> tags) {
        log.info("Saving image with name: {}, size: {}", file.getName(), file.getSize());
        log.info("File name: {}", file.getOriginalFilename());
        log.info("Image name: {}", name);
        log.info("Tags: {}", tags);
        return ResponseEntity.ok().build();

    }

}
