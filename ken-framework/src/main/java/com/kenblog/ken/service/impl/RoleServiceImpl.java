package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.annotation.SystemLog;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Role;
import com.kenblog.ken.domain.entity.RoleMenu;
import com.kenblog.ken.domain.vo.PageVo;
import com.kenblog.ken.domain.vo.RoleStaVo;
import com.kenblog.ken.domain.vo.RoleVo;
import com.kenblog.ken.mapper.RoleMenuMapper;
import com.kenblog.ken.service.RoleMenuService;
import com.kenblog.ken.service.RoleService;
import com.kenblog.ken.mapper.RoleMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 1037859047
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-08-18 17:29:48
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleMenuMapper roleMenuMapper;
    @Resource
    RoleMenuService roleMenuService;
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 如果是管理员，就只返回admin
        // 如果不是管理员，则查询其拥有的权限
        if (id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    @SystemLog(businessName = "查询角色接口")
    public ResponseResult getByRoleName(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        // 添加角色名和状态的条件查询
        if (StringUtils.hasText(roleName)) {
            wrapper.eq(Role::getRoleName, roleName);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq(Role::getStatus, status);
        }

        // 分页查询
        Page<Role> page = new Page<>(pageNum, pageSize);
        Page<Role> rolePage = page(page, wrapper);

        // 构建分页结果对象
        PageVo pageVo = new PageVo(rolePage.getRecords(), rolePage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeStatus(RoleStaVo roleStaVo) {
//        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Role::getId, roleStaVo.getRoleId());
        Role role = getById(roleStaVo.getRoleId());
        role.setStatus(roleStaVo.getStatus());
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addRole(RoleVo roleVo) {
        // 添加新角色
        Role role = BeanCopyUtils.copyBean(roleVo, Role.class);
        save(role);
        // 添加角色对应的菜单
        Long roleId = role.getId();
        List<Long> menuIds = roleVo.getMenuIds();
        List<RoleMenu> roleMenu = menuIds.stream()
                .map(menu -> new RoleMenu(roleId, menu))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenu);
        return ResponseResult.okResult();
    }

}




