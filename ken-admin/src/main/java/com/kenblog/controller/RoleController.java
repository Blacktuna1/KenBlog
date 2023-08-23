package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("list")
    public ResponseResult list(Integer pageNum,Integer pageSize,String roleName,String status){
        return roleService.getByRoleName(pageNum,pageSize,roleName,status);
    }
}
