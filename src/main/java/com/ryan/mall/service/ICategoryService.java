package com.ryan.mall.service;

import com.ryan.mall.vo.CategoryVo;
import com.ryan.mall.vo.ResponseVo;

import java.util.List;

/**
 * @author Ryan Li
 * @date 2020/01/02
 */
public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();
}
