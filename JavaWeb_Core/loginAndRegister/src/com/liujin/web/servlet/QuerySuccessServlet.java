package com.liujin.web.servlet;

import com.liujin.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/querySuccessServlet")
public class QuerySuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getAttribute("user");

        if (user!=null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(user.getId()+"---"+user.getUsername()+"---"+user.getPassword()+"<br /><a href='/loginAndRegister/login.html'>前往登录页面</a>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
