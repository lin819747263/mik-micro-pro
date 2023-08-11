package com.mik.user.service;

import com.mik.user.api.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void listByConditions() {

    }

    @Test
    void getUserById() {
        UserDTO userDTO = userService.getUserById(2L);
        System.out.println(userDTO);
    }
}
