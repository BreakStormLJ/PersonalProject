package com.liujin.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liujin.domain.Person;
import org.junit.Test;

import java.util.*;

public class FastJsonTest {
    //Java对象转为JSON字符串
    @Test
    public void test1() throws Exception {
        //1.创建Person对象
        Person p  = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");

        String json = JSONObject.toJSONString(p);
        System.out.println(json);
    }


    @Test
    public void test2() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        String json = JSONObject.toJSONString(p);
        System.out.println(json);
    }



    @Test
    public void test3() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(23);
        p1.setGender("男");
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(23);
        p2.setGender("男");
        p2.setBirthday(new Date());


        //创建List集合
        List<Person> ps = new ArrayList<Person>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);

        String json = JSONArray.toJSONString(ps);
        System.out.println(json);

    }

    @Test
    public void test4() throws Exception {
        //1.创建map对象
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","张三");
        map.put("age",23);
        map.put("gender","男");

        String json = JSONObject.toJSONString(map);
        System.out.println(json);
    }

    //演示 JSON字符串转为Java对象
    @Test
    public void test5() throws Exception {
        //1.初始化JSON字符串
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";
        Person person = JSONObject.parseObject(json, Person.class);
        System.out.println(person);
    }

    @Test
    public void test6() throws Exception {
        String json = "[{\"gender\":\"男\",\"name\":\"张三\",\"age\":23},{\"gender\":\"男\",\"name\":\"张三\",\"age\":25}]";
        List<Person> persons = JSONArray.parseArray(json, Person.class);
        System.out.println(persons);
    }
}
