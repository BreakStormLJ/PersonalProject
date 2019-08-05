package com.liujin.travel.dao.impl;

import com.liujin.travel.dao.UserDao;
import com.liujin.travel.domain.Category;
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
    public List<User> findByUsername(String username) {
        //return null;
        String sql = "select * from tab_user where username = ?";
        try {
            List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int save(User user) {
        String sql = "insert into tab_user values(null,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),
                user.getCode());
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "select  * from tab_user where username = ? and password = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
    }

    @Override
    public List<Category> findAllCategory() {
        String sql = "SELECT * FROM tab_category ORDER BY cid;";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
        //return null;
    }


}
