package com.mik.gateway.manager;

import com.mik.gateway.service.LoginUser;
import com.mik.gateway.service.UserDetailService;
import com.mik.gateway.token.SmsCodeToken;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Component
public class SmsCodeAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {

//    @Resource
    UserDetailService userDetailService;

    public SmsCodeAuthenticationManager(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (!SmsCodeToken.class.isAssignableFrom(authentication.getClass())){
            return Mono.just(authentication);
        }
        SmsCodeToken smsCodeToken = (SmsCodeToken)authentication;
        String username = authentication.getCredentials().toString();
        if("666666".equals(smsCodeToken.getCode())){
            smsCodeToken.setAuthenticated(true);
        }
        LoginUser userDetails = (LoginUser)retrieveUser(username).block();
        smsCodeToken.setPrincipal(userDetails);
        return Mono.just(smsCodeToken);
    }

    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        return userDetailService.findByUsername(username);
    }
}
