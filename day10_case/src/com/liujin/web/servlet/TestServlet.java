package com.liujin.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //测试 getParameter("")
        /*String msg1 = request.getParameter("msg1");
        String msg2 = request.getParameter("msg2");
        System.out.println(msg1+"，"+msg2);*/


        //测试 getParameterMap("")
        /*Map<String, String[]> map = request.getParameterMap();
        String msg1 = map.get("msg1")[0];
        String msg2 = map.get("msg2")[0];
        System.out.println(msg1+"，"+msg2);*/

        //测试 getParameterValues("")
        String msg1 = (String) request.getParameterValues("msg1")[0];
        String msg2 = (String) request.getParameterValues("msg2")[0];
        System.out.println(msg1+"，"+msg2);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
