package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @GetMapping("/hotArticlelist")
    public ResponseResult hotArticlelist(){
        ResponseResult result = articleService.hotArticlelist();
        return result;
    }

}
