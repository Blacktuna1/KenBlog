package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.ArticleTag;
import com.kenblog.ken.service.ArticleTagService;
import com.kenblog.ken.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sg_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2023-08-20 22:38:00
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




