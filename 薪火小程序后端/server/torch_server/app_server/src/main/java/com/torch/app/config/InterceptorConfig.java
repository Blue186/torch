package com.torch.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private Interceptor interceptor;

    @Autowired
    public InterceptorConfig(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     *    添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] path = {
                "/user/**",
                "/activity/**",
                "/signUp/**",
                "/impressions/**",
                "/file/**",
                "/contactUs/**",
                "/activityChild/**",
                "/article/**",
                "/comments/**"
        };
        String[] excludePath = {
                "/user/login",
                "/swagger-ui.html"
        };
        registry.addInterceptor(interceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }

    /**
     *    这里是静态资源映射，将static对应的地址映射到file下面，因为静态文件的具体位置并不相同
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        配置图片路径映射
//        String path = System.getProperty("user.dir")+"/torch_server/app_server/src/main/resources/static/";
        String path = System.getProperty("user.dir")+"/static/";
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+path);
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
