package com.liujin.web.servlet;

import com.liujin.service.CityService;
import com.liujin.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityService service = new CityServiceImpl();
        String provinceJson = service.getProvinceJson();
        System.out.println(provinceJson);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(provinceJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
