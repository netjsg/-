package com.it.netjsg.bigworknew.service.impl;

import com.it.netjsg.bigworknew.mapper.CategoryMapper;
import com.it.netjsg.bigworknew.pojo.Category;
import com.it.netjsg.bigworknew.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void addCategory(Category category) {

        categoryMapper.addCategory(category);
    }

    public List<Category> getCategoryList(Integer id) {
        return categoryMapper.getCategoryList(id);
    }

    @Override
    public Category getCategoryDetail(Integer id) {
       return categoryMapper.getCategoryDetail(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }
    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }
}
