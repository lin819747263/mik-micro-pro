package com.mik.user.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Data
@Table("user")
public class User {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
