package com.mik.user.api.user;

import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.dto.UserListDTO;
import com.mik.user.api.fallback.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserRpc {

    @GetMapping("/user/getUserById")
    UserDTO getUserById(@RequestParam(value = "userId") Long userId);

    @GetMapping("/user/getUserByIdentify")
    UserListDTO getUserByIdentify(@RequestParam(value = "identify") String identify);

}
