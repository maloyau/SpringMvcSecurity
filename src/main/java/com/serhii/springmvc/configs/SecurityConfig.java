package com.serhii.springmvc.configs;

import com.serhii.springmvc.entities.CustomUserDetails;
import com.serhii.springmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(s -> new CustomUserDetails(userRepository.findUserByUsername(s)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .and()
                    .formLogin()
                    .defaultSuccessUrl("/",false);
    }
}
