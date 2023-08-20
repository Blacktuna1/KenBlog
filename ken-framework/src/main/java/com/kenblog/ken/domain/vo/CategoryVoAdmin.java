package com.kenblog.ken.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVoAdmin {
    private String description;
    private Long id;
    private String name;
}
