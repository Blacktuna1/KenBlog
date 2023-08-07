package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.Link;
import com.kenblog.ken.service.LinkService;
import com.kenblog.ken.mapper.LinkMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sg_link(友链)】的数据库操作Service实现
* @createDate 2023-08-08 00:18:42
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

}




