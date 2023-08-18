package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.OssUploadService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
@Api(tags = "加载",description = "加载相关接口")

@RestController
public class UploadController {

    @Resource
    private OssUploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
