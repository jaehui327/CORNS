package com.w6w.corns.config;

import com.w6w.corns.jwt.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;
    @Value("${custom.path.upload-img}")
    private String uploadImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("file:///" + uploadImagesPath + "/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .maxAge(3000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //메인페이지에서 api 호출도 풀기
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns("/**") // temp
                .excludePathPatterns("/swagger/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/rank/hof")
                .excludePathPatterns("/user/join")
                .excludePathPatterns("/user/email-check/{email}")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/auth/{socialType}/callback")
                .excludePathPatterns("/resources/**");
    }
}
