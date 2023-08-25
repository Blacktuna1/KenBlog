package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kenblog.ken.domain.vo.CategoryVoFour;

/**
* @author 1037859047
* @description 针对表【sg_category(分类表)】的数据库操作Service
* @createDate 2023-08-02 02:38:16
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    ResponseResult getCategoryListAdmin();

    ResponseResult getListByName(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult getListById(Long id);

}
