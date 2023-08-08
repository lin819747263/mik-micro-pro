package com.mik.user.api.user;

import com.mik.user.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "127.0.0.1:8080")
public interface UserRpc {

    @GetMapping("/user/getUserById")
    UserDTO getUserById(@RequestParam(value = "userId") Long userId);
}
