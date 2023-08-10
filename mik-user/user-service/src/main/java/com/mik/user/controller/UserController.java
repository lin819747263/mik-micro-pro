package com.mik.user.controller;

import com.mik.core.pojo.PageInput;
import com.mik.core.pojo.PageResult;
import com.mik.core.pojo.Result;
import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.user.UserRpc;
import com.mik.user.dto.UserListDTO;
import com.mik.user.entity.User;
import com.mik.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements UserRpc {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallBack")
    public List<User> listUser(){
        return userService.list();
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
        userService.save(user);
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

    @GetMapping("/listByConditionPage")
    public Result<PageResult<UserListDTO>> listByConditionPage(Integer pageNum, Integer pageSize){
        PageInput input = new PageInput();
        input.setPageNum(pageNum);
        input.setPageSize(pageSize);
        return Result.success(userService.listByConditionPage(input));
    }

}
