package com.liujin.dao.impl;

import com.liujin.dao.UserDao;
import com.liujin.domain.User;
import com.liujin.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: JavaWeb_Core
 * @description:
 * @author: liujin
 * @create: 2019-07-18 18:52
 **/
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int register(User user) {
        String sql = "insert into user values(null,?,?)";
        int count = 0;
        try {
            count = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
            return count;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return count;
        }

    }

    @Override
    public User login(User user) {
        String sql = "select * from user where username = ? and password = ?";
        try {
            User user1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class)
                    , user.getUsername(), user.getPassword());
            return user1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User query(String username) {
        String sql = "select * from user where username = ?";
        try {
            User user1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class)
                    , username);
            return user1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
