package com.practice3.practice3.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapperconfig {
    @Bean
    ModelMapper getmapper(){
        return new ModelMapper();
    }
}
