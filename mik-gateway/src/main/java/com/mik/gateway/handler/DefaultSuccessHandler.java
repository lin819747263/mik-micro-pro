package com.mik.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.mik.core.pojo.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class DefaultSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
//        Algorithm algorithm = Ed448.Algorithm.HMAC256("secret");
//        String token = JWT.create()
//                .withClaim("uid", "123456")
//                .withIssuedAt(new Date())
//                .withIssuer("rkproblem")
//                .sign(algorithm);
//        JWTVerifier verifier = JWT.require(algorithm)
//                .build();
//        DecodedJWT jwt = verifier.verify(token);
//        System.out.println(jwt.getClaims().entrySet().stream()
//                .map(n->n.getKey()+" = " + n.getValue().asString()).collect(Collectors.joining(", ")));


        Result<String> result = Result.success("874574748486486486");
        String body = JSONObject.toJSONString(result);

        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
