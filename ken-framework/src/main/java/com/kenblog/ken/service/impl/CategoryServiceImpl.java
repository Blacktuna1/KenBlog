package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.Category;
import com.kenblog.ken.service.CategoryService;
import com.kenblog.ken.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2023-08-02 02:38:16
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




