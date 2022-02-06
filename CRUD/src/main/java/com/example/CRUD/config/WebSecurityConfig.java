package com.example.CRUD.config;

import com.example.CRUD.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userServiceIml;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();

    }
    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/news/**").hasRole("USER")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceIml).passwordEncoder(bCryptPasswordEncoder());
    }
}
