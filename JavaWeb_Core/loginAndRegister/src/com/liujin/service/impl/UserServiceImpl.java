package com.liujin.service.impl;

import com.liujin.dao.UserDao;
import com.liujin.dao.impl.UserDaoImpl;
import com.liujin.domain.User;
import com.liujin.service.UserService;

/**
 * @program: JavaWeb_Core
 * @description:
 * @author: liujin
 * @create: 2019-07-18 19:25
 **/
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public int register(User user) {
        return userDao.register(user);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public User query(String username) {
        return userDao.query(username);
    }
}
