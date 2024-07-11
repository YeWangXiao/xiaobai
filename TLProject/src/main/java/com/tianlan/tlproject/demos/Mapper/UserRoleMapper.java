package com.tianlan.tlproject.demos.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianlan.tlproject.demos.Domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
