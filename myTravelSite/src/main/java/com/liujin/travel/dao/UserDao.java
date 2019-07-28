package com.liujin.travel.dao;

import com.liujin.travel.domain.User;

public interface UserDao {
    /**
     * 以用户名为条件进行查询
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int save(User user);
}
