package com.kenblog.controller;

import com.kenblog.ken.annotation.SystemLog;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户",description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName="更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){return userService.register(user);}
}
