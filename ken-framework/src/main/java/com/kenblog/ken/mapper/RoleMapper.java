package com.kenblog.ken.mapper;

import com.kenblog.ken.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2023-08-18 17:29:48
* @Entity com.kenblog.ken.domain.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}




