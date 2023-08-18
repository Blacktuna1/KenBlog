package com.kenblog.ken.service;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.domain.entity.User;

public interface SystemLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
