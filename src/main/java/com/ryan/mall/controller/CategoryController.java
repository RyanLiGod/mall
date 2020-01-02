package com.ryan.mall.controller;

import com.ryan.mall.service.impl.CategoryServiceImpl;
import com.ryan.mall.vo.CategoryVo;
import com.ryan.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ryan Li
 * @date 2020/01/02
 */
@RestController
@Slf4j
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll() {
        return categoryService.selectAll();
    }
}
