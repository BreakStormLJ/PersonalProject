package com.liujin.dao;

import com.liujin.domain.User;

public interface UserDao {
    public int register(User user);
    public User login(User user);
    public User query(String username);
}
