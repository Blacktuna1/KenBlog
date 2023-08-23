package com.kenblog.controller;


import com.kenblog.ken.annotation.SystemLog;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.AddArticleDto;
import com.kenblog.ken.domain.entity.Article;
import com.kenblog.ken.domain.vo.ArticleVo;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Resource
    ArticleService articleService;

    @Resource
    public MenuService menuService;
    @PostMapping
    @SystemLog(businessName = "添加新博文")
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("list")
    public ResponseResult list(Integer pageNum,Integer pageSize,String title,String summary){
        return articleService.getArticleListByTitleSummary(pageNum,pageSize,title,summary);
    }

    @GetMapping("{id}")
    public ResponseResult getArticleByIdAdmin(@PathVariable Long id){
        return articleService.getArticleDetailAdmin(id);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteArticleById(@PathVariable Long id){
        return articleService.deleteArticleById(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody AddArticleDto addArticleDto){
        return articleService.updateArticle(addArticleDto);
    }


}
