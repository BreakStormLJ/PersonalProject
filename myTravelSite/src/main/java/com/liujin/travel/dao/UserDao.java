package com.liujin.travel.dao;

import com.liujin.travel.domain.Category;
import com.liujin.travel.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 以用户名为条件进行查询
     * @param username
     * @return
     */
    public List<User> findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int save(User user);

    /**
     * 获取登录用户数据
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassword(String username,String password);

    /**
     * 获取导航栏数据
     * @return
     */
    public List<Category> findAllCategory();

    /**
     * 修改指定用户激活状态
     * @param user
     */
    public void updateStatus(User user);

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    public User findByCode(String code);
}
