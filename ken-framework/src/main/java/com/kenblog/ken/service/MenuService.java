package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.TreeMap;

/**
* @author 1037859047
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-08-18 17:05:48
*/
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult getByMenuName(String status, String menuName);

    ResponseResult add(Menu menu);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenu(Long menuId);
}
