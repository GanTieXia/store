//package com.gantiexia.webconfig.filter;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * spring-security,待完善
// *
// * @author GanTieXia
// * @date 2021/11/12 17:36
// */
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().loginProcessingUrl("/store/loginPage").loginProcessingUrl("/login/loginSystem"); //使用表单认证方式
//    }
//}
