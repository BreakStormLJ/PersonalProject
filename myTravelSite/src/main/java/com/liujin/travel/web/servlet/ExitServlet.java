package com.liujin.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //清除浏览器持久化的cookie -- 回写一个同名的cookie,设置maxAge为0即可
        Cookie cookie = new Cookie("autologin",null);

        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());

        response.addCookie(cookie);

        //2.跳转页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
