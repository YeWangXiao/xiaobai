package com.tianlan.tlproject.demos.Controller;


import com.tianlan.tlproject.demos.DTO.ResultDTO;
import com.tianlan.tlproject.demos.Domain.LoginUser;
import com.tianlan.tlproject.demos.Service.LoginService;
import com.tianlan.tlproject.demos.Service.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

// 登录控制器
@Controller
@RequestMapping("/api")
public class LoginController {


    @Autowired
    private PasswordEncoder passwordEncoder; // 自动注入PasswordEncoder

    @Autowired
    private UserInfoDetailsService userInfoDetailsService; // 自动注入UserDetailsService

    @Autowired
    private LoginService loginService; // 自动注入LoginService

    // 登录页面
    @GetMapping("/login")
    public String login() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/root/login");
        return "/root/login";
    }

    //测试登录接口
    @PostMapping("/loginfun")
    @ResponseBody
    public ResultDTO loginfun(HttpServletRequest request, @RequestBody LoginUser user) {
        return loginService.login(request, user);
    }


    // 注销接口
    @GetMapping("/logout")
    public String logout() {
//        ModelAndView modelAndView = new ModelAndView();

        //获取当前用户的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // 注销当前用户
            SecurityContextHolder.clearContext();
        }
        // 跳转到登录页面
//        modelAndView.setViewName("/root/login");
        return "/root/login";
    }


    // 注册页面
    @GetMapping("/register")
    public String register() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/root/register");
        return "/root/register";
    }

    // 注册接口
    @PostMapping("/registerfun")
    @ResponseBody
    public ResultDTO registerfun(HttpServletRequest request, @RequestBody LoginUser user) {
        return loginService.register(request, user);
    }


}
