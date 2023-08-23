package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.vo.RoleStaVo;
import com.kenblog.ken.domain.vo.RoleVo;
import com.kenblog.ken.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("list")
    public ResponseResult list(Integer pageNum,Integer pageSize,String roleName,String status){
        return roleService.getByRoleName(pageNum,pageSize,roleName,status);
    }
    @PutMapping("changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleStaVo roleStaVo){
        return roleService.changeStatus(roleStaVo);
    }

    @PostMapping
    public ResponseResult add(@RequestBody RoleVo roleVo){
        return roleService.addRole(roleVo);
    }

}
