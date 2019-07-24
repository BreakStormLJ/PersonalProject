package com.liujin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: day10_case
 * @description:
 * @author: liujin
 * @create: 2019-07-23 15:43
 **/
public class BeanFactory {

    public static Object getBean(String id) {
        try {
            Properties pro = new Properties();
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            pro.load(is);
            String className = pro.getProperty(id);

            return Class.forName(className).newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
