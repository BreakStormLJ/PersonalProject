package com.liujin.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujin.travel.dao.UserDao;
import com.liujin.travel.dao.impl.UserDaoImpl;
import com.liujin.travel.domain.Category;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @program: myTravelSite
 * @description:
 * @author: liujin
 * @create: 2019-08-01 15:59
 **/
public class TravelTest {
    @Test
    public void test01(){
        UserDao userDao = new UserDaoImpl();

        Jedis jedis = null;
        String category_json = null;
        try {
            jedis = new Jedis();
            category_json = jedis.get("category");
            if (category_json == null || category_json.length() == 0){
                System.out.println("redis中没有导航栏数据，查询数据库。。。。");
                List<Category> allCategory = userDao.findAllCategory();
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(allCategory);
                jedis.set("category",json);
            }else {
                System.out.println("redis中有导航栏数据，查询缓存。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        System.out.println(category_json);


    }
}
