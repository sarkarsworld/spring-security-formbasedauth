package com.simplesolutions.config;

import com.simplesolutions.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // This is to disable CSRF security provided by Spring so that we can access the POST URI calls from non-browser clients like postman.
                .authorizeRequests()
                .antMatchers("/signin").permitAll()  // This URI is accessible to users with any role.
                .antMatchers("/public/**").hasRole("NORMAL")    // URI's starting with /public can be accessed by NORMAL users only.
                .antMatchers("/users/**").hasRole("ADMIN")   // URI's starting with /users can be accessed by ADMIN users only.

//                .antMatchers("/public/**").permitAll()      // for permitting a unified or generic URI patter.
//                .antMatchers("/home", "/login", "register").permitAll()  // for matching specific URI's
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/users/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
