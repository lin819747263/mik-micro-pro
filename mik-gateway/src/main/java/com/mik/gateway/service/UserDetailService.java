package com.mik.gateway.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailService implements ReactiveUserDetailsService {

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(username);
        loginUser.setUserPwd("666666");
        return Mono.just(loginUser);
    }
}
