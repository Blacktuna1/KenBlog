package com.kenblog.controller;

import com.kenblog.ken.annotation.SystemLog;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Api(tags = "文章",description = "文章相关接口")

public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticlelist(){
        ResponseResult result = articleService.hotArticlelist();
        return result;
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @PutMapping("updateViewCount/{id}")
    @SystemLog(businessName = "更新文章浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }


}
