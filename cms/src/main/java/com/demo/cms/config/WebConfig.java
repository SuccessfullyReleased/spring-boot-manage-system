package com.demo.cms.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 戴俊明
 * @version 1.0
 * @className WebConfig
 * @description 配置拦截器和资源地址
 * @date 2019/5/20 15:37
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * @param registry
     * @return void
     * @author 戴俊明
     * @description 配置资源
     * @date 2019/5/20 15:43
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**").addResourceLocations("file:" + ResourceConstants.AVATOR);
    }

}
