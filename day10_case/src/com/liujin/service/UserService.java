package com.liujin.service;

import com.liujin.domain.Admin;
import com.liujin.domain.PageBean;
import com.liujin.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAll();

    /**
     *
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
     * 根据用户id删除用户
     * @param id
     */
    public void delUser(String id);

    /**
     * 根据ID查找用户
     * @param id
     * @return
     */
    public User findUserById(String id);

    /**
     * 修改用户数据
     * @param user
     */
    public void updateUser(User user);

    /**
     * 批量删除用户
     * @param ids
     */
    public void delSelectedUser(String[] ids);

    /**
     * 分页查询所有用户
     * 分页条件查询所有用户
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);
}
