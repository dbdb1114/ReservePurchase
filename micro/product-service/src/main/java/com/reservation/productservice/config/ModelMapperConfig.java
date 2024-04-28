package com.reservation.productservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.modelmapper.config.Configuration.AccessLevel;

@Component
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
        return mapper;
    }
}
