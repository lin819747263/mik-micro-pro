package com.mik.user.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserListDTO {
    private Long userId;
    private String username;
    private String nickname;
    private String mobile;
    private String email;
    private String password ;
    private String avatar;
    private Integer sex;
    private Date birthday;
}
