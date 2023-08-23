package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Role;
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
    //这个接口写的不太好，id字段有点问题。
    public ResponseResult add(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteById(@PathVariable Long id){
        return roleService.deleteById(id);
    }



}
