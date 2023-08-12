package com.mik.security.provider;

import com.mik.security.UserInfo;
import com.mik.security.service.UserDetailService;
import com.mik.security.token.UsernamePasswordToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernameAndPasswordProvider implements AuthenticationProvider {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authentication;
        String mobile = token.getPrincipal().toString();
        String code = token.getCredentials().toString();

        UserInfo userInfo = (UserInfo) userDetailService.loadUserByUsername(mobile);

        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)){
            throw new UsernameNotFoundException("用户名或者密码不存在");
        }
        if(!encoder.matches("123456", userInfo.getPassword())){
            throw new BadCredentialsException("用户名或者密码错误");
        }
        token.setDetails(userInfo);

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordToken.class);
    }
}
