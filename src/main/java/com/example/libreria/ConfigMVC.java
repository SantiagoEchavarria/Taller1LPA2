package com.example.libreria;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigMVC implements WebMvcConfigurer{
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("Inicio");
        registry.addViewController("/error_403").setViewName("error/error_403");
        registry.addViewController("/inicio").setViewName("mantenimiento/inicio");
        WebMvcConfigurer.super.addViewControllers(registry);
    }
}
