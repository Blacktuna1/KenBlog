package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 1037859047
* @description 针对表【sg_link(友链)】的数据库操作Service
* @createDate 2023-08-08 00:18:42
*/
public interface LinkService extends IService<Link> {

    ResponseResult<String> getAllLink();
}
