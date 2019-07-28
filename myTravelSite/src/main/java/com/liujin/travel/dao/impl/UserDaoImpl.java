package com.liujin.travel.dao.impl;

import com.liujin.travel.dao.UserDao;
import com.liujin.travel.domain.User;
import com.liujin.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @program: myTravelSite
 * @description:
 * @author: liujin
 * @create: 2019-07-28 18:21
 **/
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        //return null;
        String sql = "select * from tab_user where username = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int save(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),
                user.getCode());
    }
}
