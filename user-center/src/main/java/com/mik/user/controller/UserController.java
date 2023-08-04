package com.mik.user.controller;

import com.mik.user.entity.User;
import com.mik.user.mapper.UserMapper;
import com.mik.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> listUser(){
        return userMapper.selectAll();
    }

    @GetMapping("/list")
    public List<User> listByConditions(){
        return userService.listByConditions();
    }

}
