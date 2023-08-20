package com.kenblog.controller;


import com.kenblog.ken.annotation.SystemLog;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.AddArticleDto;
import com.kenblog.ken.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping
    @SystemLog(businessName = "添加新博文")
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }
}
