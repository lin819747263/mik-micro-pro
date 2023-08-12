package com.mik.client.filter;


import com.mik.client.HttpServletUtil;
import com.mik.client.UserInfoToken;
import com.mik.core.constant.CommonConstant;
import com.mik.core.pojo.Result;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Order(90)
public class AuthorFilter extends OncePerRequestFilter {

    private Set<String> whiteList = new HashSet<>(){{
        add("/login");
    }};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(whiteList.contains(request.getRequestURI())){
            filterChain.doFilter(request, response);
        }


        String authHeader = request.getHeader(CommonConstant.AUTH_HEADER);
        if(StringUtils.isBlank(authHeader)){
            unAuth(response);
            return;
        }


        String username = Jwts.parser()
                .setSigningKey("test")
                .parseClaimsJws(authHeader)
                .getBody()
                .getSubject();


        String tokenId = Jwts.parser()
                .setSigningKey("test")
                .parseClaimsJws(authHeader)
                .getBody()
                .getId();


        UserInfoToken token = new UserInfoToken(new ArrayList<>(), username, tokenId);
        token.setDetails(null);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request,response);
    }

    private void unAuth(HttpServletResponse response) throws IOException {
        HttpServletUtil.writeData(response, Result.error("未授权"));
    }
}
