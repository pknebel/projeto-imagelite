package com.github.pknebel.imageliteapi.domain.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;

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
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "extension")
    @Enumerated(EnumType.STRING)
    private ImageExtensionEnum extension;

    @Column(name = "uploaded_at")
    @CreatedDate
    private LocalDateTime uploadedAt;

    @Column(name = "tags")
    private String tags;

    @Column(name = "file")
    @Lob
    private byte[] file;

}
