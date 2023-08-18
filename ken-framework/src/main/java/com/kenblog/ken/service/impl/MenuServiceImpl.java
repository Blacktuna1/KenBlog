package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.Menu;
import com.kenblog.ken.service.MenuService;
import com.kenblog.ken.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-08-18 17:05:48
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public List<String> selectPermsByUserId(Long id) {
        return null;
    }
}




