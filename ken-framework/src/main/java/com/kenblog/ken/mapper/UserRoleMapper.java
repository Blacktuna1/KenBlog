package com.kenblog.ken.mapper;

import com.kenblog.ken.domain.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 1037859047
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
* @createDate 2023-08-24 17:05:51
* @Entity com.kenblog.ken.domain.entity.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    Long[] selectRoleIdByUserId(Long id);
}




