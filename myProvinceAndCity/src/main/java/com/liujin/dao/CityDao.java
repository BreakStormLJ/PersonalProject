package com.liujin.dao;

import com.liujin.domain.City;
import com.liujin.domain.Province;

import java.util.List;

public interface CityDao {
    /**
     * 查询所有的省份信息
     * @return
     */
    public List<Province> findProvince();

    /**
     * 根据pid查询对应省份下的城市
     * @param pid
     * @return
     */
    public List<City> findCity(int pid);
}
