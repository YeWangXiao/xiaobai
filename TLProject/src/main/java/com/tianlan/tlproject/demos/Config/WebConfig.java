package com.tianlan.tlproject.demos.Config;
import com.tianlan.tlproject.demos.Utils.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // 添加登录拦截器，并设置拦截路径和排除路径
//重写addInterceptors方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加一个LoginInterceptor到registry
        registry.addInterceptor(new LoginInterceptor())
                //设置路径模式以匹配所有路径
                .addPathPatterns("/**")
                .excludePathPatterns("/**/api/**")
                .excludePathPatterns("/**/root/**")
                .excludePathPatterns("/**/js/**", "/**/css/**", "/**/img/**", "/**/fonts/**");
    }



}
