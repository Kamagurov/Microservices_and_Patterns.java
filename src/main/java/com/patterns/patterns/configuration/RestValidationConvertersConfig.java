package com.patterns.patterns.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class RestValidationConvertersConfig {

    @Bean
    public MappingJackson2HttpMessageConverter jsonHttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(new MapperConfig().myObjMapper());
    }
}
