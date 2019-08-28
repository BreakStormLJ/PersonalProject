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
        jdbcTemplate.update(sql,id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

    }

    @Override
    public void updateUser(User user) {
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());

    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user";

        return jdbcTemplate.queryForObject(sql,Integer.class);

    }

    /**
     * 分页查询用户数据
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows) {
        String sql = "select * from user limit ?,?";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);
    }


}
