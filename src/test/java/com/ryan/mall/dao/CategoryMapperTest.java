package com.ryan.mall.dao;

import com.ryan.mall.MallApplicationTests;
import com.ryan.mall.pojo.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ryan Li
 * @date 2019/12/11
 */
public class CategoryMapperTest extends MallApplicationTests {

    @Autowired(required=false)
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
        Category category = categoryMapper.findById(100001);
        System.out.println(category.toString());
    }

    @Test
    public void queryById() {
        Category category = categoryMapper.queryById(100001);
        System.out.println(category.toString());
    }
}