package com.fhlzmy.web.config;

import com.fhlzmy.web.inteceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 卧槽啊， 这个配置类只能有一个，按顺序加载第一个。其余的不行!
 *
 * 这个@EnableAspectJAutoProxy   允许开启AOP切面...
 */
@EnableAspectJAutoProxy
@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //////拦截所有请求
                .excludePathPatterns("login.do");////除了登录的请求
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }


    /**
     *
     * @return
     */
    @Bean(name = "requestContextListener")
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
