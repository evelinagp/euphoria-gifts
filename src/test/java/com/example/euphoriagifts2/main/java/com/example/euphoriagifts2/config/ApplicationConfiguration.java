package com.example.euphoriagifts2.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Map;

@Configuration
public class ApplicationConfiguration {
    private final CloudinaryConfig cloudinaryConfig;

    public ApplicationConfiguration(CloudinaryConfig cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                Map.of(
                        "cloud_name",cloudinaryConfig.getCloudName(),
                        "api_key",cloudinaryConfig.getApiKey(),
                        "api_secret",cloudinaryConfig.getApiSecret()
                )
        );

    }
}

