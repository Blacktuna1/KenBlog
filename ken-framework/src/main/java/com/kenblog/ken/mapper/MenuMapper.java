package com.kenblog.ken.mapper;

import com.kenblog.ken.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2023-08-18 17:05:48
* @Entity com.kenblog.ken.domain.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}




