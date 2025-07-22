package com.github.pknebel.imageliteapi.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {

}