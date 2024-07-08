package com.bookshop.book_shop_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMpperConfig {
    @Bean
    public ModelMapper modelMapperConfig(){
        return new ModelMapper();
    }
}
