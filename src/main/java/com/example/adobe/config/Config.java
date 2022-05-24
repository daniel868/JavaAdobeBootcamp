package com.example.adobe.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.modelmapper.ModelMapper;
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
        return new ModelMapper();
    }

    @Bean
    public SimpleDateFormat provideSimpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm");
    }
    @Bean
    public AmazonS3 provideS3Credentials() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIA43WMW2IGU7UNQDU7",
                "XkXeoqatV8M+dXFGgtbh4MaApRkp5MPQtIWHY6l6"
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_WEST_1)
                .build();
    }
}
