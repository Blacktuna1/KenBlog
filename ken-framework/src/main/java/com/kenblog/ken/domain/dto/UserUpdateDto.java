package com.kenblog.ken.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private Long[] roleIds;

    private Long id;

    private String nickName;

    private String status;

    private String email;

    private String phonenumber;

    private String sex;
}
