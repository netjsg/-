package com.it.netjsg.bigworknew.service.impl;

import com.it.netjsg.bigworknew.mapper.UserMapper;
import com.it.netjsg.bigworknew.pojo.User;
import com.it.netjsg.bigworknew.service.UserService;
import com.it.netjsg.bigworknew.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUser(String username) {
        return userMapper.findUser(username);
    }
    @Override
    public void register(String username, String password) {
        userMapper.register(username, Md5Util.getMD5String(password));
    }

    @Override
    public void update(User user) {
        //更新时间
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(Integer id, String avatarUrl) {
        userMapper.updateAvatar( id, avatarUrl);
    }

    @Override
    public void updatePassword(Integer id, String newPassword) {
        userMapper.updatePassword(id,Md5Util.getMD5String(newPassword));
    }


}
