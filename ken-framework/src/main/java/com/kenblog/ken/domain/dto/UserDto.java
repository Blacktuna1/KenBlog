package com.kenblog.ken.domain.dto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @TableName sys_user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    private Long[] roleIds;

    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private String type;

    private String status;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;


}