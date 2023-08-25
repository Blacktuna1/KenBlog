package com.kenblog.ken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.dto.UserDto;
import com.kenblog.ken.domain.dto.UserUpdateDto;
import com.kenblog.ken.domain.entity.Role;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.domain.entity.UserRole;
import com.kenblog.ken.domain.vo.PageVo;
import com.kenblog.ken.domain.vo.UserDetailsVo;
import com.kenblog.ken.domain.vo.UserInfoVo;
import com.kenblog.ken.domain.vo.UserVo;
import com.kenblog.ken.enums.AppHttpCodeEnum;
import com.kenblog.ken.exception.SystemException;
import com.kenblog.ken.mapper.UserRoleMapper;
import com.kenblog.ken.service.RoleService;
import com.kenblog.ken.service.UserRoleService;
import com.kenblog.ken.service.UserService;
import com.kenblog.ken.mapper.UserMapper;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.SecurityUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;
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
    @Transactional
    public ResponseResult getList(Integer pageNum, Integer pageSize, String userName, String phonenumber,String status) {
        // 查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(userName)){
            wrapper.eq(User::getUserName,userName);
        }
        if (StringUtils.hasText(phonenumber)){
            wrapper.eq(User::getPhonenumber,phonenumber);
        }
        if (StringUtils.hasText(status)){
            wrapper.eq(User::getStatus,status);
        }
        // 分页
        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    @Transactional
    public ResponseResult addUser(UserDto userDto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        // 密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        // 用户名不为空，不重复
        if (!StringUtils.hasText(user.getUserName())){
            return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        else{
            wrapper.eq(User::getUserName, user.getUserName());
            if (userMapper.selectOne(wrapper)!=null){
                return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
            }
        }
        // 电话不重复
        if (StringUtils.hasText(user.getPhonenumber())){
            wrapper.eq(User::getPhonenumber, user.getPhonenumber());
            if (userMapper.selectOne(wrapper)!=null){
                return ResponseResult.errorResult(AppHttpCodeEnum.PHONENUMBER_EXIST);
            }
        }
        // 邮箱不重复
        if (StringUtils.hasText(user.getEmail())){
            wrapper.eq(User::getEmail, user.getEmail());
            if (userMapper.selectOne(wrapper)!=null){
                return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
            }
        }
        // save
        save(user);
        List<UserRole> sysUserRoles = Arrays.stream(user.getRoleIds())
                .map(roleId -> new UserRole(user.getId(), roleId)).collect(Collectors.toList());
        userRoleService.saveBatch(sysUserRoles);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteById(Long id) {
        // 删除用户
        userMapper.deleteById(id);
        // 删除用户对应的权限
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUser_id,id);
        userRoleMapper.delete(wrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserDetails(Long id) {
        // 根据id查询user、roles、rolesId
        User user = getById(id);
        List<Role> roles = roleService.list();
        Long[] roleIds = userRoleMapper.selectRoleIdByUserId(id);
        UserVo userVo = BeanCopyUtils.copyBean(user, UserVo.class);
        UserDetailsVo userDetailsVo = new UserDetailsVo(roleIds, roles, userVo);
        return ResponseResult.okResult(userDetailsVo);
    }

    @Override
    @Transactional
    public ResponseResult updateUserDetailsInfo(UserUpdateDto userUpdateDto) {
        User user = BeanCopyUtils.copyBean(userUpdateDto, User.class);
        updateById(user);
        Long[] roleIds = userUpdateDto.getRoleIds();
        List<UserRole> userRoles = Arrays.stream(roleIds)
                .map(roleId -> new UserRole(userUpdateDto.getId(), roleId))
                .collect(Collectors.toList());
        // 将新的用户角色关联添加到数据库或者进行其他相关操作
        //先删除
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUser_id,userUpdateDto.getId());
        userRoleService.remove(wrapper);
        //再添加
        userRoleService.saveBatch(userRoles);
        return null;
    }
}




