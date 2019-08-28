package com.liujin.service.impl;

import com.liujin.dao.UserDao;
import com.liujin.dao.impl.UserDaoImpl;
import com.liujin.domain.Admin;
import com.liujin.domain.PageBean;
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

    @Override
    public void delSelectedUser(String[] ids) {

        for (String id : ids) {
            userDao.delUser(Integer.parseInt(id));
        }
    }

    /**
     * 分页查询所有用户
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<>();

        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = userDao.findTotalCount();
        pb.setTotalCount(totalCount);

        //4.调用dao查询list集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage(start, rows);
        pb.setList(list);

        //计算总页码
        int totalPage = totalCount % rows == 0 ? totalCount / rows : ((totalCount / rows) + 1);
        pb.setTotalPage(totalPage);

        int beginPage = 0;
        int endPage = 0;

        if (totalPage < 5) {
            beginPage = 1;
            endPage = totalPage;
        } else {
            beginPage = currentPage - 2;
            endPage = currentPage + 2;

            if (beginPage <= 0) {
                beginPage = 1;
                endPage = 5;
            }

            if (endPage > totalPage) {
                endPage = totalPage;
                beginPage = endPage - 4;
            }
        }

        pb.setBeginPage(beginPage);
        pb.setEndPage(endPage);

        return pb;

    }


}
