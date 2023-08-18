package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.Tag;
import com.kenblog.ken.service.TagService;
import com.kenblog.ken.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sg_tag(标签)】的数据库操作Service实现
* @createDate 2023-08-17 22:06:42
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




