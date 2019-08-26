package com.liujin.service;

import com.liujin.domain.Admin;
import com.liujin.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public Admin login(Admin admin);

    public int addUser(User user);

    public void delUser(String id);

    public User findUserById(String id);

    public void updateUser(User user);

    public void delSelectedUser(String[] ids);
}
