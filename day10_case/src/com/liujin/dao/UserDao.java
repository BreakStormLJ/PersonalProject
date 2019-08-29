package com.liujin.dao;

import com.liujin.domain.Admin;
import com.liujin.domain.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过Id查找用户
     * @param id
     * @return
     */
    public User findUserById(int id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public void updateUser(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * 分页条件查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);


}
