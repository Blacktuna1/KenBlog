package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.domain.vo.UserInfoVo;
import com.kenblog.ken.service.UserService;
import com.kenblog.ken.mapper.UserMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 1037859047
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-08-08 15:08:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseResult userInfo() {
        //获取security中的登录信息的id
        Long userId = SecurityUtils.getUserId();
        //通过id查询用户信息
        User user = getById(userId);
        //封装到vo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //返回结果
        return ResponseResult.okResult(vo);
    }
}




