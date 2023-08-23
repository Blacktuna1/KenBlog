package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.AddArticleDto;
import com.kenblog.ken.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kenblog.ken.domain.vo.ArticleVo;

/**
* @author 1037859047
* @description 针对表【sg_article(文章表)】的数据库操作Service
* @createDate 2023-07-30 00:48:44
*/
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticlelist();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult getArticleDetailAdmin(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult getArticleListByTitleSummary(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult updateArticle(AddArticleDto addArticleDto);

    ResponseResult deleteArticleById(Long id);
}
