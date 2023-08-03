package com.mik.gateway.convert;

import com.mik.gateway.token.SmsCodeToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class UserConvert extends ServerFormLoginAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        String username = exchange.getAttribute("username");
        String password = exchange.getAttribute("password");
        String type = exchange.getAttribute("type");
        Authentication token;
        if("1".equals(type)){
            token = new UsernamePasswordAuthenticationToken(username, password);
        }else {
            token = new SmsCodeToken(new ArrayList<>(), username, password);
        }

        return Mono.just(token);
    }
}
