package com.journal.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan("com.journal.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    //配置静态资源直接访问
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/page/**")
                    .addResourceLocations("/page/");
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("/images/");
            registry.addResourceHandler("/assets/**")
                    .addResourceLocations("/assets/");
            registry.addResourceHandler("/personalHtml/**")
                    .addResourceLocations("/personalHtml/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/page/submit.html");
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/personalHtml/personalHome.html");
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/personalHtml/personalSetting.html");
    }


}
