package com.example.euphoriagifts2.config;

import com.example.euphoriagifts2.web.interseptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor) {
        this.statsInterceptor = statsInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
    }
}
