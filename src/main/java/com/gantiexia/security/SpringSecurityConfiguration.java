package com.gantiexia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author GanTieXia
 * @date 2021/11/15 14:08
 */
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 创建一个持久化用户登录标记的Repository对象
     * 此操作用redis不稳定，所以用JDBC。JdbcTokenRepositoryImpl是访问数据库的连接对象，需要提供数据库连接
     *
     * 所有被@Bean注解描述的方法，其参数，都是从Spring容器中获取。如果没有，抛出异常，如果对象不唯一，抛出异常
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        // 设置启动的时候，创建表格。只有数据库中不存在的时候才创建
        // 这里建表语句放进了sql文件，所以不需要执行
        // 正常操作执行以后就删除这个建表语句
        //jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }


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
                .usernameParameter("idNumber") // 登录表单的账号name属性值，默认username。可自行通过此语句配置
                .passwordParameter("password") // 登录表单的密码name属性值，默认password。可自行通过此语句配置
                .loginPage("/store/loginPage") // 当用户未登录的时候默认跳转到登录界面
                .loginProcessingUrl("/loginSys") // 对应登录表单上的action属性值
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

        // remember-Me操作
        http.rememberMe()
                //.rememberMeParameter("") // 记住我参数名，默认是remember-me
                .tokenValiditySeconds(60*60) // 设置记住我的时间，单位是秒，默认是7天
                .tokenRepository(persistentTokenRepository)
                .userDetailsService(userLoginService)
        ;

        // 允许内部加载frame
        http.headers().frameOptions().sameOrigin();

        // 关闭csrf安全协议（跨站请求伪造），就是别的网站非法获取我们的cookie
        http.csrf().disable();
    }

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;
    @Autowired
    private UserLoginServiceImpl userLoginService;

}
