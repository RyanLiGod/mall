package com.ryan.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author Ryan Li
 * @date 2019/12/11
 */
@Data
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;
}
