package com.zhan.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"com.zhan.mail.*"})
@MapperScan(basePackages = "com.zhan.mail.mapper")
public class MainConfig {

}
