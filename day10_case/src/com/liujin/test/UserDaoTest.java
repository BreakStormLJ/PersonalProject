package com.liujin.test;

import com.liujin.dao.UserDao;
import com.liujin.dao.impl.UserDaoImpl;
import com.liujin.domain.Admin;
import com.liujin.domain.User;
import org.junit.Test;

import java.util.List;

/**
 * @program: day10_case
 * @description: 测试类
 * @author: liujin
 * @create: 2019-07-23 14:41
 **/
public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void test01(){
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test02(){
        Admin admin = new Admin();
        admin.setUsername("zhangsan");
        admin.setPassword("123456");
        Admin loginUser = userDao.login(admin);
        System.out.println(loginUser);
    }

    @Test
    public void test03(){
        User user = new User();
        user.setName("邓九");
        user.setGender("男");
        user.setAge(28);
        user.setAddress("四川");
        user.setQq("45679");
        user.setEmail("dj@itcast.cn");
        int i = userDao.addUser(user);
        if (i>0){
            System.out.println("注册成功！");
        }else {
            System.out.println("注册失败！");
        }
    }

    @Test
    public void test04(){
        Admin admin = new Admin();
        admin.setUsername("zhangsan");
        admin.setPassword("123456");
        Admin loginUser = userDao.login(admin);
        System.out.println(loginUser);
    }
}
