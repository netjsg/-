package com.it.netjsg.bigworknew.controller;

import com.it.netjsg.bigworknew.pojo.Article;
import com.it.netjsg.bigworknew.pojo.PageBean;
import com.it.netjsg.bigworknew.pojo.Result;
import com.it.netjsg.bigworknew.pojo.User;
import com.it.netjsg.bigworknew.service.ArticleService;
import com.it.netjsg.bigworknew.service.UserService;
import com.it.netjsg.bigworknew.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    //导入业务层接口
    @Autowired
    private ArticleService articleService;
    //引入用户Service
    @Autowired
    private UserService userService;

    //添加文章
    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        //添加时间和创建人
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        //获取用户信息
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findUser(username);
        article.setCreateUser(user.getId());
        articleService.addArticle(article);
        return Result.success();
    }

    //获取自己的文章的文章
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

}
