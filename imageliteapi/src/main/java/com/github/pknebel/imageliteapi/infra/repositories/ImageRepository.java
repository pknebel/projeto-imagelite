package com.github.pknebel.imageliteapi.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

import static com.github.pknebel.imageliteapi.infra.repositories.specs.ImageSpecs.extensionEqual;
import static com.github.pknebel.imageliteapi.infra.repositories.specs.ImageSpecs.nameLike;
import static com.github.pknebel.imageliteapi.infra.repositories.specs.ImageSpecs.tagsLike;

import static com.github.pknebel.imageliteapi.infra.repositories.specs.GenericSpecs.conjunction;;

/*
 * SELECT * FROM IMAGE WHERE 1 = 1 AND EXTENSION = extension AND (NAME LIKE query OR TAGS LIKE query)
 */

public interface ImageRepository extends JpaRepository<ImageEntity, String>, JpaSpecificationExecutor<ImageEntity> {

    default List<ImageEntity> findByExtensionAndNameOrTagsLike(ImageExtensionEnum extension, String query){
        Specification<ImageEntity> conjunction = conjunction();
        Specification<ImageEntity> spec = Specification.where(conjunction);

        if(extension != null){
            spec = spec.and(extensionEqual(extension));
        }

        if(StringUtils.hasText(query)){
            //AND (NAME LIKE query OR TAGS LIKE query)
            spec = spec.and(Specification.anyOf(nameLike(query), tagsLike(query)));
        }
        return findAll(spec);
    }
}