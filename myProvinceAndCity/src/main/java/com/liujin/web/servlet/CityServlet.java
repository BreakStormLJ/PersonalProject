package com.liujin.web.servlet;

import com.liujin.domain.City;
import com.liujin.service.CityService;
import com.liujin.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cityServlet")
public class CityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String pid = request.getParameter("pid");
        System.out.println(pid);
        CityService service = new CityServiceImpl();
        String cityJson = service.getCityJson(pid);
        System.out.println(cityJson);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(cityJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
