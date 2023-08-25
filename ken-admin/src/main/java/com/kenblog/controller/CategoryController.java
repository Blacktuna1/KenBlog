package com.kenblog.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.Category;
import com.kenblog.ken.domain.vo.CategoryVoFour;
import com.kenblog.ken.domain.vo.ExcelCategoryVo;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.service.CategoryService;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("listAllCategory")
    public ResponseResult listAllCategory(){
        return categoryService.getCategoryListAdmin();
    }

    @GetMapping("list")
    public ResponseResult getListByName(Integer pageSize,Integer pageNum,String name,String status){
        return categoryService.getListByName(pageNum,pageSize,name,status);
    }
    @GetMapping("{id}")
    public ResponseResult getListById(@PathVariable Long id){
        return categoryService.getListById(id);
    }
    @PutMapping
    public ResponseResult edit(@RequestBody Category category){
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }
    @PostMapping
    public ResponseResult add(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.okResult();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseResult remove(@PathVariable(value = "id")Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel
                    .write(response.getOutputStream(), ExcelCategoryVo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }}


