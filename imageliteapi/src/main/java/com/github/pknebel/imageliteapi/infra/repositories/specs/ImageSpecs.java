package com.github.pknebel.imageliteapi.infra.repositories.specs;

import org.springframework.data.jpa.domain.Specification;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

public class ImageSpecs {

    private ImageSpecs(){}

    public static Specification<ImageEntity> extensionEqual(ImageExtensionEnum extension){
        return (root, q, cb) -> cb.equal(root.get("extension"), extension);
    }

    public static Specification<ImageEntity> nameLike(String query){
        return(root, q, cb) -> cb.like(cb.upper(root.get("name")), "%" + query.toUpperCase() + "%");
    }

    public static Specification<ImageEntity> tagsLike(String query){
        return (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + query.toUpperCase() + "%");
    }
}
