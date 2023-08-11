package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.Comment;
import com.kenblog.ken.service.CommentService;
import com.kenblog.ken.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sg_comment(评论表)】的数据库操作Service实现
* @createDate 2023-08-11 17:12:27
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




