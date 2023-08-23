package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.constants.SystemConstants;
import com.kenblog.ken.domain.entity.Menu;
import com.kenblog.ken.domain.vo.MenuTreeVo;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.service.MenuService;
import com.kenblog.ken.mapper.MenuMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 1037859047
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-08-18 17:05:48
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{
    @Resource
    MenuMapper menuMapper;

    @Override
    // 通过id查询用户权限
    public List<String> selectPermsByUserId(Long id) {
        //返回权限分为两种，一种是管理员，另一种是不是管理员。
        // 若是管理员，给全部权限，若不是管理员，则查询其拥有的权限，然后给予权限
        if (id == 1L){
            LambdaQueryWrapper<Menu> wrapper= new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if(SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public ResponseResult getByMenuName(String status, String menuName) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.hasText(status)&&!StringUtils.hasText(menuName)){
            return ResponseResult.okResult(list());
        }
        else if (!StringUtils.hasText(menuName)){
            wrapper.eq(Menu::getStatus, status);
            return ResponseResult.okResult(list(wrapper));
        }
        else {
            wrapper.eq(Menu::getStatus, status);
            wrapper.eq(Menu::getMenuName, menuName);
            return ResponseResult.okResult(list(wrapper));
        }
    }

    @Override
    public ResponseResult add(Menu menu) {
        try{
            save(menu);
            return ResponseResult.okResult();
        } catch (Exception e) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SAVE_ERROR);
        }
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (menu.getParentId().equals(menu.getId())){
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long menuId) {
        // 如果有子菜单，不能删除
            //具体是查询表里的parentsId是否有等于menuId
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,menuId);
        queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
        List<Menu> list = list(queryWrapper);
        if (!list.isEmpty()){
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
        menuMapper.deleteById(menuId);
        return ResponseResult.okResult();
        // 否则，根据id删除
    }

    @Override
    public ResponseResult getMenuTreeList() {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = menuMapper.selectAllRouterMenu();

        List<MenuTreeVo> menuTreeVos = builderMenuTreeVo(menus, 0L);
        return ResponseResult.okResult(menuTreeVos);
    }
    private List<MenuTreeVo> builderMenuTreeVo(List<Menu> menus, Long parentId) {

        List<MenuTreeVo> MenuTreeVos = menus.stream()
                .map(m -> new MenuTreeVo(null,m.getId(), m.getMenuName(), m.getParentId()))
                .collect(Collectors.toList());

        List<MenuTreeVo> options = MenuTreeVos.stream()
                .filter(o -> o.getParentId().equals(0L))
                .map(o -> o.setChildren(getChildListVo(MenuTreeVos, o)))
                .collect(Collectors.toList());
        return options;
    }

    private static List<MenuTreeVo> getChildListVo(List<MenuTreeVo> list, MenuTreeVo option) {
        List<MenuTreeVo> options = list.stream()
                .filter(o -> Objects.equals(o.getParentId(), option.getId()))
                .map(o -> o.setChildren(getChildListVo(list, o)))
                .collect(Collectors.toList());
        return options;

    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }
    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }



}




