package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.constants.SystemConstants;
import com.kenblog.ken.domain.entity.Article;
import com.kenblog.ken.domain.entity.Category;
import com.kenblog.ken.domain.vo.CategoryVo;
import com.kenblog.ken.domain.vo.CategoryVoAdmin;
import com.kenblog.ken.domain.vo.CategoryVoFour;
import com.kenblog.ken.domain.vo.PageVo;
import com.kenblog.ken.service.ArticleService;
import com.kenblog.ken.service.CategoryService;
import com.kenblog.ken.mapper.CategoryMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author 1037859047
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2023-08-02 02:38:16
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    public ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发布
        //获取特定status的article实体类
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category. getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);


        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public ResponseResult getCategoryListAdmin() {
        //查询文章表，状态为已发布
        //获取特定status的article实体类
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        List<Category> list = list(queryWrapper);
        //封装vo
        List<CategoryVoAdmin> CategoryVoAdmin = BeanCopyUtils.copyBeanList(list, CategoryVoAdmin.class);


        return ResponseResult.okResult(CategoryVoAdmin);
    }

    @Override
    public ResponseResult getListByName(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name))
            wrapper.like(Category::getName,name);
        if (StringUtils.hasText(status))
            wrapper.eq(Category::getStatus,status);
        Page<Category> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        page(page,wrapper);
        List<Category> records = page.getRecords();
        List<CategoryVoFour> categoryVoFours = records.stream()
                .map(record -> new CategoryVoFour(record.getDescription(), record.getId(), record.getName(), record.getStatus()))
                .collect(Collectors.toList());
        long total = page.getTotal();
        PageVo pageVo = new PageVo(categoryVoFours, total);
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getListById(Long id) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getId,id);
        List<Category> list = list(wrapper);
        List<CategoryVoFour> categoryVoFours = list.stream()
                .map(record -> new CategoryVoFour(record.getDescription(), record.getId(), record.getName(), record.getStatus()))
                .collect(Collectors.toList());
        return ResponseResult.okResult(categoryVoFours);
    }


}




