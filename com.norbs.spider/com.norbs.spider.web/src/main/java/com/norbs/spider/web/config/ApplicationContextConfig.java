package com.norbs.spider.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Clase que equivale al archivo applicationContext de la configuracion xml.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.norbs.spider.web.config",
"com.norbs.spider.web.controller", "com.norbs.spider.service.base",
"com.norbs.spider.service.libros", "com.norbs.spider.service.usuarios",
"com.norbs.spider.dao.base", "com.norbs.spider.dao.libros" })
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "viewResolver")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /*
     * Configura los archivos contenidos en la carpeta "assets" (CSS, Javascriptm etc)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }
}