package com.sej.firstboard.config;



import javax.annotation.Resource;

import com.sej.firstboard.service.UserService;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name="com.sej.firstboard.service.UserService")
    private UserService userService;

    @Override public void configure(WebSecurity web) throws Exception { 
        web.ignoring() 
            .antMatchers("/resources/**") 
            .antMatchers("/css/**") 
            .antMatchers("/vendor/**") 
            .antMatchers("/js/**") 
            .antMatchers("/favicon*/**") 
            .antMatchers("/img/**"); 
    }


    //http 관련 인증 설정 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
                .antMatchers("/login", "/signup", "/", "/list").permitAll() //누구나 접근 가능 
                //.antMatchers("/insert", "/update", "/fileDown", "/comment/**").hasRole("USER") //USE, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") //ADMIN만 접근 가능 
                .anyRequest().authenticated()
            .and()
                .csrf().ignoringAntMatchers("/comment/**")
            .and()
                .formLogin() //로그인 설정
                    .usernameParameter("id")
                    .loginPage("/login") //로그인 페이지 경로 
                    .loginProcessingUrl("/loginProc") 
                    .defaultSuccessUrl("/") //로그인 성공 시 연결되는 주소
                    .permitAll()
            .and()
                .logout() //로그아웃 관련 설정 
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 경로
                    .logoutSuccessUrl("/login") //로그아웃 시 연결되는 경로
                    .invalidateHttpSession(true) //로그아웃 시 저장해 둔 션 날리기 
        ; 
    }


    //로그인 시 필요한 정보를 가져옴 
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) 
            .passwordEncoder(new BCryptPasswordEncoder()); 
    }
}

