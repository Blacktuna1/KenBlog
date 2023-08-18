package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface OssUploadService {
    ResponseResult uploadImg(MultipartFile img);
}
