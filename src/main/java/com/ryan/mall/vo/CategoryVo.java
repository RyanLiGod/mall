package com.ryan.mall.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Ryan Li
 * @date 2020/01/02
 */
@Data
public class CategoryVo {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;
}
