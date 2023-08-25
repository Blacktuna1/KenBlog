package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.UserDto;
import com.kenblog.ken.domain.dto.UserUpdateDto;
import com.kenblog.ken.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 1037859047
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-08-08 15:08:07
*/
public interface UserService extends IService<User> {

     ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult getList(Integer pageNum, Integer pageSize, String userName, String phonenumber ,String status);

    ResponseResult addUser(UserDto userDto);

    ResponseResult deleteById(Long id);

    ResponseResult getUserDetails(Long id);

    ResponseResult updateUserDetailsInfo(UserUpdateDto userUpdateDto);
}
