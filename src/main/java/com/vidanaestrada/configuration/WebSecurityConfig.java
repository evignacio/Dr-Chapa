package com.vidanaestrada.configuration;

import com.vidanaestrada.core.security.JWTAuthenticationFilter;
import com.vidanaestrada.core.security.JWTLoginFilter;
import com.vidanaestrada.core.security.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImp userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        httpSecurity.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/auth")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user")
                .permitAll()
                .and()
                .addFilterBefore(new JWTLoginFilter("/api/v1/auth", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
