package com.transportexchangegroup.fm.config;

import com.transportexchangegroup.fm.transformer.DtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapperConfig {
    @Bean
    public DtoMapper getDtoMapper() {
        return Mappers.getMapper(DtoMapper.class);
    }
}