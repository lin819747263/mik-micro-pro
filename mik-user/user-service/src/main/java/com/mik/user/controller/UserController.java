package com.mik.user.controller;

import com.mik.core.exception.ServiceException;
import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.user.UserRpc;
import com.mik.user.entity.User;
import com.mik.user.mapper.UserMapper;
import com.mik.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements UserRpc {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> listUser(){
        return userMapper.selectAll();
    }

    @GetMapping("/save")
    public void save(Long index){
        User user = new User();
        user.setUsername("u" + index);
        user.setNickname("n" + index);
        user.setMobile("111111");
        user.setEmail("e" + index + "@gaml.com");
        user.setSex(1);
        user.setAvatar("http://dsfsdfsd.jpg");
        user.setBirthday(new Date());
        userMapper.insert(user);
    }

    @GetMapping("/list")
    public List<User> listByConditions(){
        return userService.listByConditions();
    }

    @GetMapping("/getUserById")
    @Override
    public UserDTO getUserById(Long userId) {
        return userService.getUserById(userId);
    }
}
