package com.liujin.dao.impl;

import com.liujin.dao.PersonDao;
import com.liujin.domain.Admin;
import com.liujin.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: myDay13
 * @description:
 * @author: liujin
 * @create: 2019-07-29 19:57
 **/
public class PersonDaoImpl implements PersonDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Admin findPersonByUsername(String username) {
        String sql = "select * from admin where username = ?";
        try {
            Admin admin = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), username);
            return admin;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
