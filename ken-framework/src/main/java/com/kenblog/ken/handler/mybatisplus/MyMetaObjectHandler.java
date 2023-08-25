package com.kenblog.ken.handler.mybatisplus;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kenblog.ken.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//mybatis的字段自动填充
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入更新，更新几个创建时间和更新时间，还有创建人
    // 获取当前登录id，然后填充到评论字段
    // 接口需要请求头时，拦截，如果没有请求头的话提示登录
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//表示是自己创建
        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
    }
}
