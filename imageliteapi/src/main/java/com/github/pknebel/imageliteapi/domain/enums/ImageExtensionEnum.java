package com.github.pknebel.imageliteapi.domain.enums;

import java.util.Arrays;

import org.springframework.http.MediaType;

import lombok.Getter;

public enum ImageExtensionEnum {
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF),
    JPEG(MediaType.IMAGE_JPEG);

    @Getter
    private MediaType mediaType;

    ImageExtensionEnum(MediaType mediaType){
        this.mediaType = mediaType;

    }

    public static ImageExtensionEnum valueOf(MediaType mediaType){
        return Arrays.stream(values())
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
        
    }

}
