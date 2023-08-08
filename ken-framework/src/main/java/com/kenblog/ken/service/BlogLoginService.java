package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}
