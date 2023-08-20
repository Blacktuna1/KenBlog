package com.kenblog.controller;


import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.TagListDto;
import com.kenblog.ken.domain.entity.Tag;
import com.kenblog.ken.domain.vo.PageVo;
import com.kenblog.ken.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable("id") Long id){
        return tagService.getTag(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag);
    }

    //    listAllTag
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }
}
