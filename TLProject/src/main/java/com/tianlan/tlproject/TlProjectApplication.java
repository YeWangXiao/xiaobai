package com.tianlan.tlproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={"com"})
@MapperScan("com.tianlan.tlproject.demos.Mapper")
public class TlProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlProjectApplication.class, args);
    }

}
