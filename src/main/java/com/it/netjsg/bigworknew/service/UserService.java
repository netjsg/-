package com.it.netjsg.bigworknew.service;

import com.it.netjsg.bigworknew.pojo.User;

public interface UserService {
    User findUser(String username);
    void register(String username,String password);

    void update(User user);

    void updateAvatar(Integer id, String avatarUrl);

    void updatePassword(Integer id, String newPassword);
}
