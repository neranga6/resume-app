package com.webapp.neo.configurations;

import com.webapp.neo.model.PasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


        auth
                .inMemoryAuthentication()
                .withUser("we")
                .password(encoder.encode("we"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/css/**", "/images/**", "/js/**","/ws","/ws/**").permitAll()
                .antMatchers("/my-page","/qrcode/**","/","/chat/**","/mes/","/chat.sendMessage/**","/topic/public/**","/chat.addUser/**","/topic/public/**","/resume/**","/resume").permitAll()
                //.antMatchers("/**").authenticated() // Secured URLs
                .antMatchers( "/pdf","/pdf/**","/resume","/resume/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/submit-form")
                .defaultSuccessUrl("/pdf",true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }



}