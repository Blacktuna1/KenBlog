package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.exception.SystemException;
import com.kenblog.ken.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService bloggerService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return bloggerService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return bloggerService.logout();
    }


}
