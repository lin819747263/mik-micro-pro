package com.mik.admin.controller;

import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.user.UserRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserRpc userRpc;

    @GetMapping("test")
    public void test(){
        UserDTO dto = userRpc.getUserById(1L);
        System.out.println(dto);
    }


}
