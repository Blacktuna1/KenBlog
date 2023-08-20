package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    OssUploadService ossUploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return ossUploadService.uploadImg(img);
    }
}
