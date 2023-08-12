package com.mik.security.config;

import com.mik.client.filter.AuthorFilter;
import com.mik.security.filter.SmsLoginFilter;
import com.mik.security.filter.UsernamePasswordFilter;
import com.mik.security.handler.FailureHandler;
import com.mik.security.handler.SuccessHandler;
import com.mik.security.provider.SmsProvider;
import com.mik.security.provider.UsernameAndPasswordProvider;
import com.mik.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    FailureHandler failureHandler;
    @Autowired
    SuccessHandler successHandler;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    SmsLoginFilter smsLoginFilter;
    @Autowired
    UsernamePasswordFilter usernamePasswordFilter;
    @Autowired
    AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        HttpSecurity httpConfig = http.authorizeHttpRequests(requestMatcherRegistry -> {
            requestMatcherRegistry.requestMatchers("/login","/sms/login").permitAll()
            .anyRequest().authenticated();
        }).csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new AuthorFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(usernamePasswordFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager)
//                .authenticationProvider(smsProvider)
//                .authenticationProvider(daoAuthenticationProvider)
                .cors(AbstractHttpConfigurer::disable);
        return httpConfig.build();
    }

}
