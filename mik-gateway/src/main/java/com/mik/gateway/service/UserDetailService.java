package com.mik.gateway.service;

import com.mik.user.api.dto.UserListDTO;
import com.mik.user.api.user.UserRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailService implements ReactiveUserDetailsService {

    @Autowired
    UserRpc userRpc;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserListDTO userListDTO = userRpc.getUserByIdentify(username);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(userListDTO.getUsername());
        loginUser.setUserPwd(userListDTO.getPassword());
        return Mono.just(loginUser);
    }
}
