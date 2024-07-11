package com.tianlan.tlproject.demos.Utils;

import com.tianlan.tlproject.demos.Domain.UserInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler)
            throws Exception {
        //判断用户是否登录，如果登录，则放行，否则跳转到登录页面
//        String username = (String) request.getSession().getAttribute("username");

        //通过security获取当前登录用户
/*        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null){
            Integer userId = ((UserInfo) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal()).getUserId();
            request.setAttribute("userId", userId);
        }*/

        //判断用户是否为管理员
 /*       if ( !"admin".equals(username)) {
            response.sendRedirect("/TLProject/api/test02");
            return false;
        }*/
        //放行
        return true;
    }


    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, java.lang.Exception ex) throws Exception {

    }

}
