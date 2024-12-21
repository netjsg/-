package com.it.netjsg.bigworknew.service;

import com.it.netjsg.bigworknew.pojo.Article;
import com.it.netjsg.bigworknew.pojo.PageBean;

public interface ArticleService {
     void addArticle(Article article);
     PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
