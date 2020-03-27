package cn.crabapples.spring.system.common.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TODO springSecurity配置类
 *
 * @author Mr.He
 * 2020/3/4 0:54
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@EnableWebSecurity
public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不需要验证的地址
                .antMatchers("/").permitAll()
                //需要有demo角色才能访问的地址
        .antMatchers("/index").hasRole("demo")
        ;
        //开启登录跳转功能(默认如果没有登录就会跳转到/login)
        http.formLogin().successForwardUrl("/");
        //开启登录跳转功能(默认如果没有登录就会跳转到/logout)
        http.logout().logoutSuccessUrl("/");
        System.err.println(http);
//        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("111").roles("demo");
    }
}
