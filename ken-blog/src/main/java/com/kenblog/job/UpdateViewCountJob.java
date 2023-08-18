package com.kenblog.job;


import com.kenblog.ken.domain.entity.Article;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount() {
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        // 获取这个Map，更新到表中数据
        // 用entyrSet转成list，然后通过
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        articleService.updateBatchById(articles);
    }

}
