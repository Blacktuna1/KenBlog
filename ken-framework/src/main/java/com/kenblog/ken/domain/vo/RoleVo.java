package com.kenblog.ken.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String status;

    private List<Long> menuIds;

    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
