package com.tianlan.tlproject.demos.Domain;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private String email;
    private String captcha;
}
