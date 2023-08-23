package com.kenblog.ken.domain.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色信息表
 * @TableName sys_role
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleStaVo implements Serializable {

    private Long roleId;

    private String status;

    private static final long serialVersionUID = 1L;
}