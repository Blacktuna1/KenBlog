package com.kenblog.ken.mapper;

import com.kenblog.ken.domain.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sg_article_tag(文章标签关联表)】的数据库操作Mapper
* @createDate 2023-08-20 22:38:00
* @Entity com.kenblog.ken.domain.entity.ArticleTag
*/
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<Long> getTagsByArticleId(Long articleId);

    void deleteByArticleId(Long id);
}




