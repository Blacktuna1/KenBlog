package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.domain.vo.UserInfoVo;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.exception.SystemException;
import com.kenblog.ken.service.UserService;
import com.kenblog.ken.mapper.UserMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //save
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getList(Integer pageNum, Integer pageSize, String userName, String phonenumber) {

    }
}




