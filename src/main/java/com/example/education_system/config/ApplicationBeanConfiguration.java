package com.example.education_system.config;

import com.example.education_system.util.FileUtil;
import com.example.education_system.util.impl.FileUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtilImpl fileUtil(){
        return  new FileUtilImpl();
    }
}
