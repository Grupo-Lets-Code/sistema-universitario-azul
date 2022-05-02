package com.sistema.universitario.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*registry.addViewController("/").setViewName("cursos");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");*/
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");


    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CustomInterceptor());
//    }
}
