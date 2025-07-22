package com.github.pknebel.imageliteapi.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_IMAGES")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "extension", nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageExtensionEnum extension;

    @Column(name = "uploaded_at", nullable = false)
    @CreatedDate
    private LocalDateTime uploadedAt;

    @Column(name = "tags")
    private String tags;

    @Column(name = "file", nullable = false)
    @Lob
    private byte[] file;

}
