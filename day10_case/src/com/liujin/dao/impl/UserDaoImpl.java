package com.liujin.dao.impl;

import com.liujin.dao.UserDao;
import com.liujin.domain.Admin;
import com.liujin.domain.User;
import com.liujin.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

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
     * @param condition
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        //String sql = "select count(*) from user";
        String sql = "select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        //根据用户传递过来的值,实现动态sql的拼接
        //Set<String> keys = condition.keySet();
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();

        //定义参数的集合,用来装需要添加到sql语句中的条件value
        ArrayList<Object> params = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            //先过滤掉非分页条件的参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            String value = entry.getValue()[0];

            //判断value是否有值
            if (value != null && !"".equals(value)){
                //sb.append(" and name like ? ");
                sb.append(" and "+ key +" like ? ");
                params.add("%" + value + "%"); // ?条件的值
            }
        }
        /*for (String key : keys) {  //name address email
            //先过滤掉非分页条件的参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            // Statement st;
            //PrepareStatement pstmt;

            //获取value
            String value = condition.get(key)[0];

            //判断value是否有值
            if (value != null && !"".equals(value)){
                //sb.append(" and name like ? ");
                sb.append(" and "+ key +" like ? ");
                params.add("%" + value + "%"); // ?条件的值
            }

        }*/
        System.out.println(sb.toString());
        System.out.println(params);

        sql = sb.toString();
        //末尾接一个可变参,可变参本质上是一个数组
        //return jdbcTemplate.queryForObject(sql,Integer.class);
        //{"李","北京"}-->[params]
        //["李","北京"]
        return jdbcTemplate.queryForObject(sql,Integer.class,params.toArray());
    }

    /**
     * 分页查询用户数据
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        //String sql = "select * from user limit ?,?";
        String sql = "select * from user where 1=1";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        //根据用户传递过来的值,实现动态sql的拼接
        Set<String> keys = condition.keySet();

        //定义参数的集合,用来装需要添加到sql语句中的条件value
        ArrayList<Object> params = new ArrayList<>();

        for (String key : keys) {  //name address email
            //先过滤掉非分页条件的参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            // Statement st;
            //PrepareStatement pstmt;

            //获取value
            String value = condition.get(key)[0];

            //判断value是否有值
            if (value != null && !"".equals(value)){
                //sb.append(" and name like ? ");
                sb.append(" and "+ key +" like ? ");
                params.add("%" + value + "%"); // ?条件的值
            }

        }
        //
        System.out.println(sb.toString());
        System.out.println(params);

        //添加分页查询
        sb.append(" limit ?,? ");

        //添加分页查询参数值
        params.add(start);
        params.add(rows);

        sql = sb.toString();
        //末尾接一个可变参,可变参本质上是一个数组
        //return jdbcTemplate.queryForObject(sql,Integer.class);
        //{"李","北京"}-->[params]
        //["李","北京"]
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}
