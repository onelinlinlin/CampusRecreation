package com.lzp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagesUploadConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/prc/headprc/**").addResourceLocations("file:D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\headprc\\");

        registry.addResourceHandler("/prc/movieprc/**").addResourceLocations("file:D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\movieprc\\");

        registry.addResourceHandler("/prc/musicprc/**").addResourceLocations("file:D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\musicprc\\");

        registry.addResourceHandler("/prc/bookprc/**").addResourceLocations("file:D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\bookprc\\");

        registry.addResourceHandler("/prc/activityprc/**").addResourceLocations("file:D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\activityprc\\");
    }
}
