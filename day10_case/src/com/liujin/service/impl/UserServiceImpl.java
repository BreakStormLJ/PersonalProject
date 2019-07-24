package com.liujin.service.impl;

import com.liujin.dao.UserDao;
import com.liujin.dao.impl.UserDaoImpl;
import com.liujin.domain.Admin;
import com.liujin.domain.User;
import com.liujin.service.UserService;

import java.util.List;

/**
 * @program: day10_case
 * @description:
 * @author: liujin
 * @create: 2019-07-23 15:32
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Admin login(Admin admin) {
        return userDao.login(admin);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public void delUser(String id) {
        userDao.delUser(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
