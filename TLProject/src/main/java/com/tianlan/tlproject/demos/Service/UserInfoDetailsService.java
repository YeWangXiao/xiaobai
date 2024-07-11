package com.tianlan.tlproject.demos.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.tianlan.tlproject.demos.Domain.CustomUserDetails;
import com.tianlan.tlproject.demos.Domain.UserInfo;
import com.tianlan.tlproject.demos.Domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    private final UserInfoService userInfoService;

    @Autowired
    private UserRoleService userRoleService;

    public UserInfoDetailsService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查询用户信息
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, username));


        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // 根据角色获取对应的权限信息
        List<String> rolesByUserId = userRoleService.getRolesByUserId(userInfo.getUserId());


        // // 创建 CustomUserDetails 对象，并传递用户名、密码和权限信息
        Collection<GrantedAuthority> authorities = rolesByUserId.stream()
                .map(permission -> new SimpleGrantedAuthority(permission))
                .collect(Collectors.toList());

        // 创建 CustomUserDetails 对象，并传递用户名、密码和权限信息
        return new CustomUserDetails(userInfo.getUsername(), userInfo.getPassword(), authorities);
    }
}
