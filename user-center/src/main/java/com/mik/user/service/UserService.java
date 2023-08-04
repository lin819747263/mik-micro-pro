package com.mik.user.service;

import com.mik.user.entity.User;
import com.mik.user.mapper.UserMapper;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public List<User> listByConditions(){
        QueryCondition condition =  QueryCondition.create(new QueryColumn("name"), "like", "J%");

        return userMapper.selectListByCondition(condition);
    }

}
