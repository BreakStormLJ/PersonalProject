package com.liujin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词汇过滤器
 */
//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象，增强getParameter方法
        /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            /*
                代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
                    参数：
                        1. proxy:代理对象
                        2. method：代理对象调用的方法，被封装为的对象
                        3. args:代理对象调用的方法时，传递的实际参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否getParameterMap\getParameterValues\getParameter方法
                if (method.getName().equals("getParameterMap")) {
                    //增强返回值
                    //获取返回值
                    Map<String,String[]> map = (Map<String,String[]>)method.invoke(req,args);
                    if (map!=null){
                        Set<String> keys = map.keySet();
                        for (String key : keys) {
                            String[] values = map.get(key);
                            for (int i = 0; i <values.length ; i++) {
                                for (String str : list) {
                                    if (values[i].contains(str)){
                                        String s = values[i].replaceAll(str,"***");
                                        values[i] = s;
                                    }
                                }
                            }
                        }
                    }
                    return map;
                }
                /*if (method.getName().equals("getParameter")){
                    HttpServletRequest request = (HttpServletRequest) proxy;
                    Map<String, String[]> map = request.getParameterMap();
                    return map.get(args[0])[0];
                }

                if (method.getName().equals("getParameterValues")){
                    HttpServletRequest request = (HttpServletRequest) proxy;
                    Map<String, String[]> map = request.getParameterMap();
                    return map.get(args[0]);
                }*/
                else if ("getParameterValues".equals(method.getName())){
                    HttpServletRequest pro = (HttpServletRequest) proxy;
                    Map<String, String[]> map = pro.getParameterMap();
                    return map.get(args[0]);
                }else if ("getParameter".equals(method.getName())){
                    HttpServletRequest pro = (HttpServletRequest) proxy;
                    Map<String, String[]> map = pro.getParameterMap();
                    String[] values = map.get(args[0]);
                    if (values!=null&&values.length>0){
                        return values[0];
                    }else {
                        return null;
                    }
                }

                //return null;
                return method.invoke(req, args);
            }
        });
        chain.doFilter(proxy_req, resp);

    }

    private List<String> list = new ArrayList<String>();  //敏感词汇集合

    public void init(FilterConfig config) throws ServletException {
        try {
            //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.将文件的每一行数据添加到list中
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
