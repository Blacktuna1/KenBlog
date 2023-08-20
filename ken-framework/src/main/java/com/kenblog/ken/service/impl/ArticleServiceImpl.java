package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.constants.SystemConstants;
import com.kenblog.ken.domain.dto.AddArticleDto;
import com.kenblog.ken.domain.entity.Article;
import com.kenblog.ken.domain.entity.ArticleTag;
import com.kenblog.ken.domain.entity.Category;
import com.kenblog.ken.domain.vo.ArticleDetailVO;
import com.kenblog.ken.domain.vo.ArticleListVo;
import com.kenblog.ken.domain.vo.HotArticleVo;
import com.kenblog.ken.domain.vo.PageVo;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.mapper.ArticleMapper;
import com.kenblog.ken.service.ArticleTagService;
import com.kenblog.ken.service.CategoryService;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 1037859047
* @description 针对表【sg_article(文章表)】的数据库操作Service实现
* @createDate 2023-07-30 00:48:44
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    RedisCache redisCache;
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

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //首先，参数中分为带分类id和不带分类id的，
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果 有categoryId 就要 查询时 要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式分布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        //封装查询结果成vo
        List<Article> articles = page.getRecords();

        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换成Vo
        ArticleDetailVO articleDetailVO = BeanCopyUtils.copyBean(article, ArticleDetailVO.class);

        //根据分类id查询分类名
        Long categoryId = articleDetailVO.getCategoryId();
        Category category = categoryService.getById(categoryService.getById(categoryId));
        if (category!=null){
            articleDetailVO.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVO);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult add(AddArticleDto articleDto) {
        // 对于博客的添加，不仅要添加博文内容，还要添加博客对应的标签
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        //根据文章的id和tap更新article_tap表
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(),tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();    }
}




