package com.kenblog.ken.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联表
 * @TableName sys_user_role
 */
@TableName(value ="sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    /**
     * 用户ID
     */
//    @TableId(type=IdType.INPUT)
    private Long user_id;

    /**
     * 角色ID
     */
    private Long role_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}