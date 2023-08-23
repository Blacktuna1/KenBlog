package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-08-18 17:29:48
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult getByRoleName(Integer pageNum, Integer pageSize, String roleName, String status);
}
