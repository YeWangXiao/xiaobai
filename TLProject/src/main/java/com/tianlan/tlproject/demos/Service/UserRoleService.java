package com.tianlan.tlproject.demos.Service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlan.tlproject.demos.Domain.UserRole;
import com.tianlan.tlproject.demos.Mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {


    //根据用户id获取角色列表
    public List<String> getRolesByUserId(Integer userId) {
        ArrayList<String> roless= new ArrayList<>();
        //根据用户id查询用户所拥有的角色
        List<UserRole> userRoles = this.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, userId));
        //遍历用户所拥有的角色，添加角色前缀，角色前缀为ROLE_为必须
        userRoles.forEach(userRole -> {
            roless.add("ROLE_"+userRole.getRoleName());//添加角色前缀
        });
        return roless;
    }



}
