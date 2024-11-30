package org.springframework.security.config.annotation.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

//@Configuration
public class WebSecurityConfigurerAdapter implements
        WebSecurityConfigurer<WebSecurity> {
    @Override
    public void init(WebSecurity builder) throws Exception {

    }

    @Override
    public void configure(WebSecurity builder) throws Exception {

    }
}
