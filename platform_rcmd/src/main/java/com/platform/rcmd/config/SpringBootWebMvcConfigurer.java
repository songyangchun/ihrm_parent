package com.platform.rcmd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

       //自定义静态资源映射,使得 upload 下的静态资源可以通过该映射地址被访问到
       // 其他静态资源
        registry.addResourceHandler("/files/**").addResourceLocations("file:C:\\Users\\SYC\\Documents\\workspace\\spot_img\\");
    }

}
