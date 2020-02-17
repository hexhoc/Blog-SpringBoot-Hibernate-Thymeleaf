package com.hexhoc.springbootblog;

import com.hexhoc.springbootblog.constants.Constants;
import com.hexhoc.springbootblog.user.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootBlogWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // Add an interceptor to intercept the URL path prefixed with /admin
        registry.addInterceptor(userInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
