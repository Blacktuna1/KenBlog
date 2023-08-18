package com.kenblog.controller;


import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list() {
        return ResponseResult.okResult(tagService.list());
    }
}
