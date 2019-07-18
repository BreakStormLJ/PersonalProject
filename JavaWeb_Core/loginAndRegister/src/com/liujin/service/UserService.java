package com.liujin.service;

import com.liujin.domain.User;

public interface UserService {
    public int register(User user);
    public User login(User user);
    public User query(String username);
}
