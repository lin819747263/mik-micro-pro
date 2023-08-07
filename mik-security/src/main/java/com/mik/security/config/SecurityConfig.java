package com.mik.security.config;

import com.mik.security.filter.SmsLoginFilter;
import com.mik.security.handler.FailureHandler;
import com.mik.security.handler.SuccessHandler;
import com.mik.security.provider.SmsProvider;
import com.mik.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    SmsProvider smsProvider;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Bean
    AuthenticationManager authenticationManager() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(encoder);

        ProviderManager pm = new ProviderManager(daoAuthenticationProvider, smsProvider);
        return pm;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        HttpSecurity httpConfig = http.authorizeHttpRequests(requestMatcherRegistry -> {
            requestMatcherRegistry.requestMatchers("/login","/sms/login").permitAll();
        }).csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new SmsLoginFilter("/sms/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager())
//                .authenticationProvider(smsProvider)
//                .authenticationProvider(daoAuthenticationProvider)
                .cors(AbstractHttpConfigurer::disable).formLogin(formLoginConfigurer -> formLoginConfigurer.usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler));
        return httpConfig.build();
    }

}
