package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.Role;
import com.kenblog.ken.service.RoleService;
import com.kenblog.ken.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-08-18 17:29:48
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        return null;
    }
}




