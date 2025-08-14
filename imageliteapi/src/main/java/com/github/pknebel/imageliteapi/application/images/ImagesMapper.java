package com.github.pknebel.imageliteapi.application.images;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

@Component
public class ImagesMapper {

    public ImageEntity mapToImage(MultipartFile file, String name, List<String> tags) throws IOException{
        return ImageEntity.builder()
                    .name(name)
                    .tags(String.join(",", tags))
                    .size(file.getSize())
                    .extension(ImageExtensionEnum.valueOf(MediaType.valueOf(file.getContentType())))
                    .file(file.getBytes())
                    .build();

    }

    public ImageDTO imageToDto(ImageEntity image, String url){
        return ImageDTO.builder()
                    .url(url)
                    .extension(image.getExtension().name())
                    .name(image.getName())
                    .size(image.getSize())
                    .uploadedDate(image.getUploadedAt().toLocalDate())
                    .build();
    }

}
