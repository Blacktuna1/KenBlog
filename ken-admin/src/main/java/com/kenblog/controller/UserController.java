package com.kenblog.controller;

import com.kenblog.ken.annotation.SystemLog;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.UserDto;
import com.kenblog.ken.domain.dto.UserUpdateDto;
import com.kenblog.ken.service.OssUploadService;
import com.kenblog.ken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    OssUploadService ossUploadService;

    @Autowired
    UserService userService;
    @PostMapping("upload")
    public ResponseResult uploadImg(MultipartFile img){
        return ossUploadService.uploadImg(img);
    }


    @GetMapping("/system/user/list")
    public ResponseResult getList(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        return userService.getList(pageNum,pageSize,userName,phonenumber,status);
    }
    @PostMapping("/system/user")
    public ResponseResult addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @DeleteMapping("/system/user/{id}")
    public ResponseResult deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }

    @GetMapping("/system/user/{id}")
    public ResponseResult getUserDetails(@PathVariable Long id){
        return userService.getUserDetails(id);
    }
    @PutMapping("/system/user")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody UserUpdateDto userUpdateDto){
        return userService.updateUserDetailsInfo(userUpdateDto);
    }
}
