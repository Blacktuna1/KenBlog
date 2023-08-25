package com.kenblog.runner;

import com.kenblog.ken.domain.entity.Article;
import com.kenblog.ken.mapper.ArticleMapper;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ViewCountRunner implements CommandLineRunner {
    @Resource
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;


    // 查询博客浏览量 id对应的viewCount
    // 获取redis的浏览量
    // 更新浏览量
    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleService.list();
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(
                        Collectors.toMap(
                                article -> article.getId().toString(),
                                article -> article.getViewCount().intValue()
                        )
                );
        //更新到redis
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}
