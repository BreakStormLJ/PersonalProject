package com.liujin.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujin.dao.CityDao;
import com.liujin.dao.impl.CityDaoImpl;
import com.liujin.domain.City;
import com.liujin.domain.Province;
import com.liujin.service.CityService;
import com.liujin.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @program: myProvinceAndCity
 * @description:
 * @author: liujin
 * @create: 2019-08-01 14:11
 **/
public class CityServiceImpl implements CityService {
    private CityDao dao = new CityDaoImpl();

    /**
     * 获取省份数据
     * @return
     */
    @Override
    public String getProvinceJson() {
        Jedis jedis = null;
        String province_json = null;
        try {
            jedis = JedisUtil.getJedis();
            province_json = jedis.get("province");
            if (province_json == null || province_json.length() == 0) {
                System.out.println("redis中没有省份数据，查询数据库。。。。。。。");
                List<Province> province = dao.findProvince();
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(province);
                jedis.set("province",json);
            }else {
                System.out.println("redis中有省份数据，查询缓存。。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return province_json;
    }

    /**
     * 获取城市数据
     * @param pid
     * @return
     */
    @Override
    public String getCityJson(String pid) {
        Jedis jedis = null;
        String city_json = null;
        try {
            jedis = JedisUtil.getJedis();
            city_json = jedis.hget("city",pid);
            if (city_json == null || city_json.length() == 0) {
                System.out.println("redis中没有城市数据，查询数据库。。。。。。。");
                List<City> city = dao.findCity(Integer.parseInt(pid));
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(city);
                jedis.hset("province",pid,json);
            }else {
                System.out.println("redis中有城市数据，查询缓存。。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return city_json;
    }


}
