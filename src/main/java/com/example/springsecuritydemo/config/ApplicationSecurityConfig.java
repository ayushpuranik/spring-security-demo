package com.example.springsecuritydemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationSecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOG.info("Updating Security Configs");
        http
                .authorizeRequests()
                .anyRequest()       // any request must
                .authenticated()    // be authenticated
                .and()
                .httpBasic();       // basic authentication
    }
}
