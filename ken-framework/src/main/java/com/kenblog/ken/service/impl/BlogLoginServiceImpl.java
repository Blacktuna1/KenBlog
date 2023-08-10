package com.kenblog.ken.service.impl;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.LoginUser;
import com.kenblog.ken.domain.entity.User;
import com.kenblog.ken.domain.vo.BlogUserLoginVo;
import com.kenblog.ken.domain.vo.UserInfoVo;
import com.kenblog.ken.service.BlogLoginService;
import com.kenblog.ken.utils.BeanCopyUtils;
import com.kenblog.ken.utils.JwtUtil;
import com.kenblog.ken.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证成功
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //把用户信息存入redis
        redisCache.setCacheObject("blogLogin:"+userId,loginUser);

        //把token盒userinfo封装、返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.okResult(vo);
    }
}
