package com.liujin.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: myTravelSite
 * @description:
 * 使用BaseServlet做请求分发
 * 所有Servlet继存自BaseServlet
 * 所有子Servlet中的方法名同请求的路径一致
 * @author: liujin
 * @create: 2019-08-08 16:55
 **/
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我被执行了。。。。。。");

        //完成方法分发
        //1.获取请求路径
        String uri = req.getRequestURI();
        System.out.println("请求路径：" + uri);

        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("方法名称"+methodName);

        //3.获取方法对象Method
        //谁调用我？我代表谁
        System.out.println(this);
        //String _methodName = req.getParameter(methodName);

        try {
            //获取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            //暴力反射 method.setAccessible(true);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
