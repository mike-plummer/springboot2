package com.objectpartners.plummer.springboot2.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .antMatcher("**/*")
                .authorizeRequests()
                .anyRequest()
                .permitAll()
    }
}
