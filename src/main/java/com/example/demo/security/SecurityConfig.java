package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("shahfahed").password(passwordEncoder().encode("1234")).roles("ADMIN").authorities("ACCESS_ADMIN","ACCESS_USER")
                .and()
                .withUser("user").password(passwordEncoder().encode("5678")).roles("USER").authorities("ACCESS_USER");
    }

    /*
    configure(HttpSecurity http) this method is necessary when @Configuration and @EnableWebSecurity annotated with this class, otherwise it won't work.
    case:2 if Default User Credentials in application.properties file will not work, because default spring login not work.
    case:3 '/' must be concatenated as a suffix with routes address otherwise won't work

     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/admin").hasAuthority("ACCESS_ADMIN")
                .antMatchers("/user").hasAuthority("ACCESS_USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
