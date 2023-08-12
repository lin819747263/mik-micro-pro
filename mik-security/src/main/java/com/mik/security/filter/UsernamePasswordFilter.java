package com.mik.security.filter;

import com.mik.security.handler.FailureHandler;
import com.mik.security.handler.SuccessHandler;
import com.mik.security.token.UsernamePasswordToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class UsernamePasswordFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    SuccessHandler successHandler;
    @Autowired
    FailureHandler failureHandler;

    public UsernamePasswordFilter(AuthenticationManager authenticationManager) {
        super("/login", authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String mobile = request.getParameter("username");
        String code = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(new ArrayList<>(), mobile, code);
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    public void afterPropertiesSet() {
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }
}
