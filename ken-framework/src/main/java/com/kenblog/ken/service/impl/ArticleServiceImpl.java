package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.Article;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sg_article(文章表)】的数据库操作Service实现
* @createDate 2023-07-30 00:48:44
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Override
    public ResponseResult hotArticlelist() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        return null;
    }
}




