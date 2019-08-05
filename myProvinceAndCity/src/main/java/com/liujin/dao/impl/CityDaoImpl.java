package com.liujin.dao.impl;

import com.liujin.dao.CityDao;
import com.liujin.domain.City;
import com.liujin.domain.Province;
import com.liujin.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @program: myProvinceAndCity
 * @description:
 * @author: liujin
 * @create: 2019-08-01 11:38
 **/
public class CityDaoImpl implements CityDao {
    //private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     *
     * @return
     */
    @Override
    public List<Province> findProvince() {
        String sql = "select * from province";
        List<Province> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }

    @Override
    public List<City> findCity(int pid) {
        String sql = "select * from city where pid = ?";
        List<City> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<City>(City.class), pid);
        return list;
    }
}
