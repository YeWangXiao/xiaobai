package com.tianlan.tlproject.demos.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlan.tlproject.demos.Domain.UserInfo;
import com.tianlan.tlproject.demos.Mapper.UserInfoMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo>  {


}