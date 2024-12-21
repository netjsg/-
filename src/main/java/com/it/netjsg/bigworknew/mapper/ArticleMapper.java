package com.it.netjsg.bigworknew.mapper;

import com.it.netjsg.bigworknew.pojo.Article;
import com.it.netjsg.bigworknew.pojo.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //添加文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
                   "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void addArticle(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);


}
