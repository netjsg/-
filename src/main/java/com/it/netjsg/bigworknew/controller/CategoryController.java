package com.it.netjsg.bigworknew.controller;

import com.it.netjsg.bigworknew.pojo.Category;
import com.it.netjsg.bigworknew.pojo.Result;
import com.it.netjsg.bigworknew.pojo.User;
import com.it.netjsg.bigworknew.service.UserService;
import com.it.netjsg.bigworknew.service.impl.CategoryServiceImpl;
import com.it.netjsg.bigworknew.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private  CategoryServiceImpl categoryService;
    @Autowired
    private UserService userService;
    //新增文章分类
    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category){
        //获取用户信息
        Map<String,Object> map = ThreadLocalUtil.get();
        String username =(String)map.get("username");
        //获取用户
        User loginUser = userService.findUser(username);
        category.setCreateUser(loginUser.getId());
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryService.addCategory(category);
        return Result.success();
    }
    //获取文章分类列表
    @GetMapping
    public Result<List<Category>> getCategoryList(){
        //获取有用户信息
        Map<String,Object> map = ThreadLocalUtil.get();
        String username =(String)map.get("username");
        User loginUser = userService.findUser(username);


        return Result.success(categoryService.getCategoryList(loginUser.getId()));
    }
    //获取文章分类基本信息
    @GetMapping("detail")
    public Result <Category> getCategoryDetail(Integer id){

        return Result.success(categoryService.getCategoryDetail(id));
    }
    //更改文章分类
    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category){
        category.setUpdateTime(LocalDateTime.now());
        categoryService.updateCategory(category);
        return Result.success();
    }
    //删除文章分类
    @DeleteMapping
    public Result deleteCategory(Integer id){
        categoryService.deleteCategory(id);
        return Result.success();
    }




}
