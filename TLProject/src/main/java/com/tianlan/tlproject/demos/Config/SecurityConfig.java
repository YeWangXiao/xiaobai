package com.tianlan.tlproject.demos.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity//开启Spring Security的功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
//prePostEnabled属性决定Spring Security在接口前注解是否可用@PreAuthorize,@PostAuthorize等注解,设置为true,会拦截加了这些注解的接口,设置为false,则不会拦截
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有请求都允许访问
                .antMatchers().permitAll()
                //设置静态资源和登录控制器下的接口可以直接访问
                .antMatchers("/", "/**/api/**", "/**/captcha/**"
                        , "/css/**", "/js/**", "/img/**", "/fonts/**", "/images/**", "/vendor/**")//放行静态资源
                .permitAll()
                //设置角色权限接口测试
                .antMatchers("/**/user").hasAnyRole("user","admin")
                .antMatchers("/**/admin").hasRole("admin")
                //而其他的请求都需要认证
                .anyRequest()
                .authenticated()
                .and()

                //修改Spring Security默认的登陆界面
                .formLogin()
                .loginPage("/api/login")//自定义登陆页面
                .defaultSuccessUrl("/test/index")//登陆成功后跳转到首页
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")//注销登录地址
                .logoutSuccessUrl("/api/login") // 注销成功后跳转的页面
                .invalidateHttpSession(true) // 注销时使 HttpSession 无效
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/**/loginfun", "/**/registerfun");//忽略登陆和注册接口



    }


    //后续可以通过数据库用户登录
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("admin")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("user");

    }
*/


    /**
     * 指定加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

}
