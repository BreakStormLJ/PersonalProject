package com.liujin.test;

import com.liujin.dao.UserDao;
import com.liujin.dao.impl.UserDaoImpl;
import com.liujin.domain.User;
import org.junit.Test;

/**
 * @program: JavaWeb_Core
 * @description:
 * @author: liujin
 * @create: 2019-07-18 20:29
 **/
public class UserTest {
    @Test
    public void test01(){
        User user = new User();
        user.setUsername("yangyang");
        user.setPassword("123456");
        UserDao userDao = new UserDaoImpl();
        if (userDao.register(user)>0){
            System.out.println("注册成功！");
        }else{
            System.out.println("注册失败！");
        }
    }
}
