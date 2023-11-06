package com.simplesolutions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").hasRole("NORMAL")    // URI's starting with /public can be accessed by NORMAL users only.
                .antMatchers("/users/**").hasRole("ADMIN")   // URI's starting with /users can be accessed by ADMIN users only.

//                .antMatchers("/public/**").permitAll()      // for permitting a unified or generic URI patter.
//                .antMatchers("/home", "/login", "register").permitAll()  // for matching specific URI's
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().withUser("Admin").password(this.passwordEncoder().encode("Admin")).roles("ADMIN");
        auth
                .inMemoryAuthentication().withUser("Normal").password(this.passwordEncoder().encode("Normal")).roles("NORMAL");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
