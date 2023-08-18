package com.kenblog.controller;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.constants.SystemConstants;
import com.kenblog.ken.domain.dto.addCommentDto;
import com.kenblog.ken.domain.entity.Comment;
import com.kenblog.ken.service.CommentService;
import com.kenblog.ken.utils.BeanCopyUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")

public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageSize,Integer pageNum){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }

    @PostMapping
    @ApiOperation(value = "addComment",notes = "添加一个评论")
    public ResponseResult addComment(@RequestBody addCommentDto comment){
        Comment comment1 = BeanCopyUtils.copyBean(comment, Comment.class);
        return commentService.addComment(comment1);
    }
}
