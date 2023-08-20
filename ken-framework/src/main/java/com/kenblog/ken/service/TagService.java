package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.TagListDto;
import com.kenblog.ken.domain.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kenblog.ken.domain.vo.PageVo;

import java.util.List;

/**
* @author 1037859047
* @description 针对表【sg_tag(标签)】的数据库操作Service
* @createDate 2023-08-17 22:06:42
*/
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(Tag tag);

    ResponseResult listAllTag();
}
