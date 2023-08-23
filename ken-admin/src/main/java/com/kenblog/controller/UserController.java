package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.OssUploadService;
import com.kenblog.ken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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


    @GetMapping("list")
    public ResponseResult getList(Integer pageNum,Integer pageSize,String userName,String phonenumber){
        return userService.getList(pageNum,pageSize,userName,phonenumber);
    }
}
