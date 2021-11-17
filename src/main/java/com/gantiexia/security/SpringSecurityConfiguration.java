package com.gantiexia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author GanTieXia
 * @date 2021/11/15 14:08
 */
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * SpringSecurity拦截过滤器
     *
     * 引入自定义加密解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置登录请求相关内容
        http.formLogin()
                .usernameParameter("idNumber")
                .passwordParameter("password")
                .loginPage("/store/loginPage") // 当用户未登录的时候默认跳转到登录界面
                .loginProcessingUrl("/loginSys")
                .defaultSuccessUrl("/store/homepage",true) // 登录成功后，响应重定向的位置

        ;

        // 需要认证的请求地址
        http.authorizeRequests()
                .antMatchers("/store/loginPage").permitAll() // 放开登录页面
                .antMatchers("/login/getNextIdNumber","/login/checkCode","/login/layupload","/login/register").permitAll() // 放开注册页面的账号生成、邮件发送、上传头像、注册功能
                .antMatchers("/**/**.js","/layui/**","/picture/**").permitAll() // 静态资源文件可访问
                .anyRequest().authenticated() // 任何请求都需认证以后才能访问
        ;

        // 配置退出登录的请求
        http.logout()
                .invalidateHttpSession(true) // 回收HttpSession对象，回收之前调用HttpSession.invalidate(),默认true
                .clearAuthentication(true) // 退出之前清空Security记录的用户登录标记，默认true
                // .addLogoutHandler() // 增加退出处理器
                .logoutSuccessUrl("/store/loginPage") // 退出后回到登录界面
                .logoutUrl("/logout");

        // 允许内部加载frame
        http.headers().frameOptions().sameOrigin();

        // 关闭csrf安全协议，保证完整流程的使用
        http.csrf().disable();
    }

}
