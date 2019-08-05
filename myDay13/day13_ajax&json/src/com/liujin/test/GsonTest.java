package com.liujin.test;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liujin.domain.Person;
import org.junit.Test;

import java.util.*;

public class GsonTest {
    //Java对象转为JSON字符串
    @Test
    public void test1() throws Exception {
        //1.创建Person对象
        Person p  = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");

        Gson gson = new Gson();
        String json = gson.toJson(p);

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

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getName().equals("gender")){
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                if (clazz == String.class){
                    return  true;
                }
                return false;
            }
        }).create();
        String json = gson.toJson(p);
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

        Gson gson = new GsonBuilder().setDateFormat("yyyy年MM月dd日").create();
        String json = gson.toJson(ps);
        System.out.println(json);
    }

    @Test
    public void test4() throws Exception {
        //1.创建map对象
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","张三");
        map.put("age",23);
        map.put("gender","男");

        Gson gson = new Gson();
        String json = gson.toJson(map);
        System.out.println(json);
    }

    //演示 JSON字符串转为Java对象
    @Test
    public void test5() throws Exception {
        //1.初始化JSON字符串
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";
        Gson gson = new Gson();
        Person person = gson.fromJson(json, Person.class);
        System.out.println(person);
    }

    @Test
    public void test6() throws Exception {
        String json = "[{\"gender\":\"男\",\"name\":\"张三\",\"age\":23},{\"gender\":\"男\",\"name\":\"张三\",\"age\":25}]";
        Gson gson = new Gson();

        List<Person> persons = gson.fromJson(json,new TypeToken<List<Person>>(){}.getType());

        System.out.println(persons);
    }
}
