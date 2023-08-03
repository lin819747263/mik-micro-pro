package com.mik.gateway.manager;

import com.mik.gateway.service.LoginUser;
import com.mik.gateway.service.UserDetailService;
import com.mik.gateway.token.SmsCodeToken;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Component
public class UsernamePassroedAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {

//    @Resource
    UserDetailService userDetailService;

    public UsernamePassroedAuthenticationManager(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (!UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return Mono.just(authentication);
        }
        UsernamePasswordAuthenticationToken smsCodeToken = (UsernamePasswordAuthenticationToken)authentication;
        String username = authentication.getCredentials().toString();
//        LoginUser loginUser = (LoginUser)smsCodeToken.getPrincipal();
        if(!"888888".equals(smsCodeToken.getPrincipal())){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        smsCodeToken.setAuthenticated(true);
        LoginUser userDetails = (LoginUser)retrieveUser(username).block();
        smsCodeToken.setDetails(userDetails);
        return Mono.just(smsCodeToken);
    }

    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        return userDetailService.findByUsername(username);
    }
}
