package com.ryan.mall.service.impl;

import com.ryan.mall.MallApplicationTests;
import com.ryan.mall.enums.ResponseEnum;
import com.ryan.mall.vo.CategoryVo;
import com.ryan.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ryan Li
 * @date 2020/01/02
 */
public class CategoryServiceImplTest extends MallApplicationTests {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void selectAll() {
        ResponseVo<List<CategoryVo>> responseVoList = categoryService.selectAll();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVoList.getStatus());
    }
}