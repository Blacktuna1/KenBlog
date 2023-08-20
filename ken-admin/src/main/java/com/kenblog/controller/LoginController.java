package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.LoginUser;
import com.kenblog.ken.domain.entity.Menu;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.domain.vo.AdminUserInfoVo;
import com.kenblog.ken.domain.vo.RoutersVo;
import com.kenblog.ken.domain.vo.UserInfoVo;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.exception.SystemException;
import com.kenblog.ken.service.MenuService;
import com.kenblog.ken.service.RoleService;
import com.kenblog.ken.service.SystemLoginService;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private SystemLoginService bloggerService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return bloggerService.login(user);
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();

        // 根据id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());

        // 根据id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        /*
            这个vo分权限、角色和用户信息
         */
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }
    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        //通过Security获取UserId
        Long userId = SecurityUtils.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        bloggerService.logout();
        return ResponseResult.okResult();
    }

//    @PostMapping("/logout")
//    public ResponseResult logout(){
//        return bloggerService.logout();
//    }


}
