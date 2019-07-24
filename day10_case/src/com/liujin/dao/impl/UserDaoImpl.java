package com.liujin.dao.impl;

import com.liujin.dao.UserDao;
import com.liujin.domain.Admin;
import com.liujin.domain.User;
import com.liujin.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @program: day10_case
 * @description: 数据库业务查询
 * @author: liujin
 * @create: 2019-07-23 15:05
 **/
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        try {
            List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Admin login(Admin admin) {
        String sql = "select * from admin where username = ? and password = ?";

        try {
            Admin list = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class),
                    admin.getUsername(), admin.getPassword());
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        try {
            int count = jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(),
                    user.getAddress(), user.getQq(), user.getEmail());
            return count;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
        //return 0;
    }

    @Override
    public void delUser(int id) {
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql);
    }
}
