package com.it.netjsg.bigworknew.mapper;

import com.it.netjsg.bigworknew.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //查找
    @Select("select * from user where username=#{username}")
    User findUser(String username);
    //注册
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void register(String username,String password);
    //修改用户信息
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
    //修改用户头像
    @Update("update user set user_pic=#{avatarUrl} where id=#{id}")
    void updateAvatar(Integer id, String avatarUrl);
    @Update("update user set password=#{newPassword} where id=#{id}")
    void updatePassword(Integer id, String newPassword);
}
