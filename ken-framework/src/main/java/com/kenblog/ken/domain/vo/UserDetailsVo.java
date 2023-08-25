package com.kenblog.ken.domain.vo;

import com.kenblog.ken.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsVo {

    private Long[] roleIds;

    private List<Role> roles;

    private UserVo user;
}
