package com.kenblog.ken.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ArticleDetailVO {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    private Long categoryId;

    private String content;

    /**
     * 所属分类id
     */
    private String categoryName;

    private String isComment;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     *
     */
    private Date createTime;
}
