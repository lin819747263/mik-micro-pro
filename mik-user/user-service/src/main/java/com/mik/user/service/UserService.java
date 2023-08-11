package com.mik.user.service;

import com.mik.core.pojo.PageInput;
import com.mik.core.pojo.PageResult;
import com.mik.user.api.dto.UserDTO;
import com.mik.user.api.dto.UserListDTO;
import com.mik.user.entity.User;
import com.mik.user.mapper.UserMapper;
import com.mik.user.utils.PageUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    @Value("${server.port}")
    private Integer port;


    public List<User> listByConditions(){
        QueryCondition condition =  QueryCondition.create(new QueryColumn("name"), "like", "J%");

        return userMapper.selectListByCondition(condition);
    }

    public PageResult<UserListDTO> listByConditionPage(PageInput input){
        Page<UserListDTO> paginate = Page.of(input.getPageNum(), input.getPageSize());
        QueryWrapper wrapper = QueryWrapper.create().select().from("user");
        Page<UserListDTO> userListDTOS = userMapper.paginateAs(paginate, wrapper, UserListDTO.class);
        return PageUtil.transform(userListDTOS);
    }

    public UserDTO getUserById(Long userId) {
        User user = userMapper.selectOneWithRelationsById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setAge(18);
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getUsername());
        userDTO.setPort(port);
        return userDTO;
    }

    public UserListDTO getUserByIdentify(String identify) {
        QueryCondition condition =  QueryCondition.create(new QueryColumn("mobile"), "=", identify)
                .or(QueryCondition.create(new QueryColumn("email"), "=", identify))
                .or(QueryCondition.create(new QueryColumn("username"), "=", identify));
        QueryWrapper wrapper = QueryWrapper.create().select().from("user").where(condition);
        return userMapper.selectOneByQueryAs(wrapper, UserListDTO.class);
    }
}
