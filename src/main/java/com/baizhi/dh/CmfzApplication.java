package com.baizhi.dh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;
@MapperScan("com.baizhi.dh.dao")
@SpringBootApplication
public class CmfzApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class, args);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CmfzApplication.class);
    }
}
