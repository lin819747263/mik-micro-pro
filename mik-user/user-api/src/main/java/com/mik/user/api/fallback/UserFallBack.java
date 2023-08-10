package com.mik.user.api.fallback;

import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.user.UserRpc;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserRpc {

    @Override
    public UserDTO getUserById(Long userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("默认数据");
        userDTO.setEmail("666@qq.com");
        return userDTO;
    }
}
