package com.tianlan.tlproject.demos.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//测试控制器
@Controller()
@RequestMapping("/test")
public class TestController {



    //测试首页

    @GetMapping("/index")
    public String index() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/root/index");
        return "/root/index";
    }


    @GetMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_admin')")
    public String admin() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/root/index");
        return "hello admin";
    }

    @GetMapping("/user")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_user','ROLE_admin')")
    public String user() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/root/index");
        return "hello user";
    }


}
