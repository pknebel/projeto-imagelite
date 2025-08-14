package com.github.pknebel.imageliteapi.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

/*
 * SELECT * FROM IMAGE WHERE 1 = 1 AND EXTENSION = extension AND (NAME LIKE query OR TAGS LIKE query)
 */

public interface ImageRepository extends JpaRepository<ImageEntity, String>, JpaSpecificationExecutor<ImageEntity> {

    default List<ImageEntity> findByExtensionAndNameOrTagsLike(ImageExtensionEnum extension, String query){

        Specification<ImageEntity> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<ImageEntity> spec = Specification.where(conjunction);

        if(extension != null){
            //AND EXTENSION = extension
            Specification<ImageEntity> extensionEqual = (root, q, cb) -> cb.equal(root.get("extension"), extension);
            spec = spec.and(extensionEqual);
        }

        if(StringUtils.hasText(query)){
            //AND (NAME LIKE query OR TAGS LIKE query)
            Specification<ImageEntity> nameLike = (root, q, cb) -> cb.like(cb.upper(root.get("name")), "%" + query.toUpperCase() + "%");
            Specification<ImageEntity> tagsLike = (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + query.toUpperCase() + "%");
            Specification<ImageEntity> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);

            spec = spec.and(nameOrTagsLike);
        }
        return findAll(spec);
    }

}