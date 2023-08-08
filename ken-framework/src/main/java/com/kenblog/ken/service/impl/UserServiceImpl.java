package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.service.UserService;
import com.kenblog.ken.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 1037859047
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-08-08 15:08:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




