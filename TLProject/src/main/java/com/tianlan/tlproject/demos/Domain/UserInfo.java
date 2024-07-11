package com.tianlan.tlproject.demos.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo  {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String password;

    private Boolean enabled;

    private String emil;

    private String phone;

    private String address;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String gender;

    private Integer age;


}
