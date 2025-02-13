package br.es.pews.back.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    
    public ModelMapper modelConfig() {
        return new ModelMapper();
    }
}
