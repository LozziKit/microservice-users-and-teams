package io.lozzikit.sdk.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import io.lozzikit.sdk.security.AuthenticationInterceptor;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry
            .addInterceptor(authenticationInterceptor)
            .addPathPatterns("/**");
    }
}