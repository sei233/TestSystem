package com.boot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * 这里是springmvc的配置，所有springmvc的相关配置，都可以通过重写父类WebMvcConfigurerAdapter来达到
 */

@Configuration
@ComponentScan("com.boot")
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(1000000);
    }

    //配置静态资源转发
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/web/");
    }

}