package com.ryan.mall.service.impl;

import com.ryan.mall.consts.MallConst;
import com.ryan.mall.dao.CategoryMapper;
import com.ryan.mall.pojo.Category;
import com.ryan.mall.service.ICategoryService;
import com.ryan.mall.vo.CategoryVo;
import com.ryan.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ryan Li
 * @date 2020/01/02
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();

        // 查出parent_id是0的category
//        List<CategoryVo> categoryVoList = new ArrayList<>();
//        for (Category category : categories) {
//            if (category.getParentId().equals(MallConst.ROOT_PARENT_ID)) {
//                CategoryVo categoryVo = new CategoryVo();
//                BeanUtils.copyProperties(category, categoryVo);
//                categoryVoList.add(categoryVo);
//            }
//        }

        // lambda + stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(this::categoryToCategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        findSubCategory(categoryVoList, categories);

        return ResponseVo.success(categoryVoList);
    }

    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories) {
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for (Category category : categories) {
                if (categoryVo.getId().equals(category.getParentId())) {
                    subCategoryVoList.add(categoryToCategoryVo(category));
                    findSubCategory(subCategoryVoList, categories);
                }
                // 按照SortOrder排序
                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                categoryVo.setSubCategories(subCategoryVoList);
            }
        }
    }

    private CategoryVo categoryToCategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
