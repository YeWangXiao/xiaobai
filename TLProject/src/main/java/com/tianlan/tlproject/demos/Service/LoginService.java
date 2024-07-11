package com.tianlan.tlproject.demos.Service;
import com.tianlan.tlproject.demos.Utils.CaptchaGenerator;
import com.tianlan.tlproject.demos.DTO.ResultDTO;
import com.tianlan.tlproject.demos.Domain.LoginUser;
import com.tianlan.tlproject.demos.Domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder; // 自动注入PasswordEncoder

    @Autowired
    private UserInfoDetailsService userDetailsService; // 自动注入UserDetailsService

    @Autowired
    private UserInfoService userInfoService; // 自动注入UserInfoService

    @Autowired
    private CaptchaGenerator  captchaGenerator;

    public ResultDTO register(HttpServletRequest request, LoginUser user) {
        // 接收参数
        ResultDTO resultDTO = new ResultDTO();


        //得到用户名和密码
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String captcha = user.getCaptcha();

        //验证验证码
        // 调用CaptchaGenerator的verifyCaptcha方法，检查传入的request和captcha是否匹配
        if (captchaGenerator.verifyCaptcha(request, captcha) == false) {
            // 如果验证码错误，设置resultDTO的message属性为"验证码错误"，resultCode属性为400，并返回resultDTO
            resultDTO.setMessage("验证码错误");
            resultDTO.setResultCode(400);
            return resultDTO;
        }


        // 验证用户名是否存在
        try {
            if (userDetailsService.loadUserByUsername(username) != null) {
                resultDTO.setResultCode(400);
                resultDTO.setMessage("用户名已存在");
                return resultDTO;
            }
        } catch (UsernameNotFoundException e) {
            // 用户不存在,则注册用户
            //设置用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setEmil(email);
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setUpdateTime(LocalDateTime.now());
            //加密密码
            userInfo.setPassword(passwordEncoder.encode(password));
            //保存用户信息
            userInfoService.save(userInfo);

            // 设置用户通过
            resultDTO.setResultCode(200);
            resultDTO.setMessage("success");
        }

        return resultDTO;
    }


    public  ResultDTO login(HttpServletRequest request, LoginUser user) {
        // 接收参数
        ResultDTO resultDTO = new ResultDTO();


        //得到用户名和密码
        String username = user.getUsername();
        String password = user.getPassword();
        String captcha = user.getCaptcha();

        //验证验证码
        if (captchaGenerator.verifyCaptcha(request, captcha) == false) {
            resultDTO.setMessage("验证码错误");
            resultDTO.setResultCode(400);
            return resultDTO;
        }

        // 根据用户名加载用户信息
        UserDetails userDetails = null;

        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            resultDTO.setResultCode(404);
            resultDTO.setMessage("用户不存在请先注册");
            return resultDTO;
        }

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            // 密码匹配，验证通过和角色授权
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword()
                    , userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //设置用户通过
            resultDTO.setResultCode(200);
            resultDTO.setMessage("success");
        } else if (userDetails == null) {
            // 用户不存在
            resultDTO.setResultCode(404);
            resultDTO.setMessage("用户不存在请先注册");
        } else {
            // 密码不匹配，验证失败
            resultDTO.setResultCode(401);
            resultDTO.setMessage("密码错误");
        }
        return resultDTO;
    }
}
