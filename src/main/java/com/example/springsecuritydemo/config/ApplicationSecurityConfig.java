package com.example.springsecuritydemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationSecurityConfig.class);


    //Drawback : can not logout as authentication is provided with every request.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOG.info("Updating Security Configs");
        http
                .authorizeRequests()
                //permit few urls to all users
                .antMatchers("/", "index"/*,"/css/*","/js/*"*/)
                .permitAll()
                .anyRequest()       // any request must
                .authenticated()    // be authenticated
                .and()
                .httpBasic();       // basic authentication
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password("password")
                .roles("Student")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }
}
