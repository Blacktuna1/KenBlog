package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.constants.SystemConstants;
import com.kenblog.ken.domain.dto.TagListDto;
import com.kenblog.ken.domain.entity.Article;
import com.kenblog.ken.domain.entity.Category;
import com.kenblog.ken.domain.entity.Tag;
import com.kenblog.ken.domain.vo.CategoryVoAdmin;
import com.kenblog.ken.domain.vo.ListAllTagVo;
import com.kenblog.ken.domain.vo.PageVo;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.exception.SystemException;
import com.kenblog.ken.service.TagService;
import com.kenblog.ken.mapper.TagMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author 1037859047
* @description 针对表【sg_tag(标签)】的数据库操作Service实现
* @createDate 2023-08-17 22:06:42
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        // 分页
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag tag) {
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId,id);
        remove(queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId,id);
        Tag tag = getOne(queryWrapper);
        TagListDto tagListDto = new TagListDto();
        tagListDto.setId(tag.getId());
        tagListDto.setName(tag.getName());
        tagListDto.setRemark(tag.getRemark());

        return ResponseResult.okResult(tagListDto);
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllTag() {
        //查询文章表，状态为已发布
        //获取特定status的article实体类
        List<Tag> list_temp = list();
        //封装vo
        List<ListAllTagVo> listAllTagVo = BeanCopyUtils.copyBeanList(list_temp, ListAllTagVo.class);
        return ResponseResult.okResult(listAllTagVo);
    }
}




