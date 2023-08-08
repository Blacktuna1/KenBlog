package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult<String> getAllLinks(){
        return linkService.getAllLink();
    }


}
