package com.mik.security.config;

import com.mik.security.provider.SmsProvider;
import com.mik.security.provider.UsernameAndPasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

@Configuration
public class AuthenticationManagerConfig {
        @Autowired
        SmsProvider smsProvider;
        @Autowired
        UsernameAndPasswordProvider usernameAndPasswordProvider;


        @Bean
        AuthenticationManager authenticationManager() {
                ProviderManager pm = new ProviderManager(usernameAndPasswordProvider, smsProvider);
                return pm;
        }

}
