package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.Article;
import com.kenblog.ken.domain.vo.HotArticleVo;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.mapper.ArticleMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        //必须是正式文章
        queryWrapper.eq(Article::getStatus,0);
        //按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //只要十条数据
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

//        List<HotArticleVo> articleVos =  new ArrayList<>();
//        //bean拷贝
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(articles, vo);
//        }

        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles,HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }
}




