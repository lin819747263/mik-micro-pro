package com.mik.admin.controller;

import com.mik.core.pojo.Result;
import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.user.UserRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/u")
public class TestController {

    @Autowired
    UserRpc userRpc;

    @GetMapping("/test")
    public Result<UserDTO> test(){
        UserDTO dto = userRpc.getUserById(1L);
        return Result.success(dto);
//        System.out.println(dto);
    }


}
