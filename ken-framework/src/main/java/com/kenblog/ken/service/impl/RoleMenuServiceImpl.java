package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.RoleMenu;
import com.kenblog.ken.service.RoleMenuService;
import com.kenblog.ken.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
* @createDate 2023-08-23 21:46:01
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

}




