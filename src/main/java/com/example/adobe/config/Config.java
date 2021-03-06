package com.example.adobe.config;

import com.example.adobe.dto.FlightDto;
import com.example.adobe.entity.flight.Flight;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import java.text.SimpleDateFormat;

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
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Flight, FlightDto> flightProperty = new PropertyMap<Flight, FlightDto>() {
            @Override
            protected void configure() {
                skip(source.getFlightType(), destination.getFlightType());
            }
        };
        modelMapper.addMappings(flightProperty);

        return modelMapper;
    }

    @Bean
    public SimpleDateFormat provideSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm");
    }
}
