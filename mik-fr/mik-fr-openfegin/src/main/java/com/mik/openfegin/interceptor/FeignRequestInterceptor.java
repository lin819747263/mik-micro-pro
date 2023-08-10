package com.mik.openfegin.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRequestInterceptor implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate requestTemplate) {

    }
}
