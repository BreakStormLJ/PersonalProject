package com.liujin.dao;

import com.liujin.domain.Admin;
import com.liujin.domain.User;

import java.util.List;

public interface UserDao {


    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录功能
     * @param admin
     * @return
     */
    public Admin login(Admin admin);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 删除用户
     * @param id
     */
    public void delUser(int id);
}
