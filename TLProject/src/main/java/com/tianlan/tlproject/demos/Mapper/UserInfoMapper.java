package com.tianlan.tlproject.demos.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianlan.tlproject.demos.Domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public  interface UserInfoMapper extends BaseMapper<UserInfo>{
}
