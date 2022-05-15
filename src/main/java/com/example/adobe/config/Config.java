package com.example.adobe.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class Config {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulate() {
        Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
        factoryBean.setResources(new Resource[]{new ClassPathResource("aircraft-data.json")});

        return factoryBean;
    }

    @Bean
    public ModelMapper provideModelMapper() {
        return new ModelMapper();
    }
}
