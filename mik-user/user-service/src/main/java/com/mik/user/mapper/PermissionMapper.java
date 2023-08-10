package com.mik.user.mapper;

import com.mik.user.entity.Permission;
import com.mik.user.entity.Role;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
