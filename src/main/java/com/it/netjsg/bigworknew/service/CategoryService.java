package com.it.netjsg.bigworknew.service;

import com.it.netjsg.bigworknew.pojo.Category;

import java.util.List;

public interface CategoryService {
     void  addCategory(Category category);
     List<Category> getCategoryList(Integer id);
     Category getCategoryDetail(Integer id);

    void updateCategory(Category category);

    void deleteCategory(Integer id);
}
