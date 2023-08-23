package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Menu;
import com.kenblog.ken.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/list")
    public ResponseResult list(String status ,String menuName) {
        return menuService.getByMenuName(status,menuName);
    }

    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable("id") Long id ){
        return ResponseResult.okResult(menuService.getById(id));
    }

    @PostMapping
    public ResponseResult add(@RequestBody Menu menu){
        return menuService.add(menu);
    }

    @PutMapping
    public ResponseResult update(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("{menuId}")
    public ResponseResult delete(@PathVariable() Long menuId){
        return menuService.deleteMenu(menuId);
    }
}
