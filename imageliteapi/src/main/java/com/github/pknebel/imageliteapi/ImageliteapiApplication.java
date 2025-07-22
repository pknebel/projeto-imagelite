package com.github.pknebel.imageliteapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.github.pknebel.imageliteapi.domain.entities.ImageEntity;
import com.github.pknebel.imageliteapi.domain.enums.ImageExtensionEnum;
import com.github.pknebel.imageliteapi.infra.repositories.ImageRepository;

@SpringBootApplication
@EnableJpaAuditing
public class ImageliteapiApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ImageRepository imageRepository) {
		return args -> {
			ImageEntity image = ImageEntity.builder()
					.extension(ImageExtensionEnum.PNG)
					.name("example")
					.tags("example")
					.size(1024L)
					.build();

			imageRepository.save(image);
		};	
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ImageliteapiApplication.class, args);
	}

}
