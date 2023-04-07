package com.zjnbit.bbs.api.framework.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import com.zjnbit.bbs.api.framework.interceptor.WebRequestLogInterceptor;
import com.zjnbit.framework.web.constant.AppConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(AppConst.CROS_PAHT_PATTERN)
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods(AppConst.CROSS_ALLOWED_METHODS)
                .allowedHeaders("*")
                .exposedHeaders("*");
        StringBuilder sb = new StringBuilder("加载跨域设置,Path:").append(AppConst.CROS_PAHT_PATTERN).append("Method:");
        Arrays.stream(AppConst.CROSS_ALLOWED_METHODS).forEach(sb::append);
        log.info(sb.toString());
    }

    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(this.webRequestLogInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");

    }

    @Bean
    @ConditionalOnMissingBean(WebRequestLogInterceptor.class)
    public WebRequestLogInterceptor webRequestLogInterceptor() {
        return new WebRequestLogInterceptor();
    }
}
