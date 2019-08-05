package com.liujin.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujin.domain.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Msg msg = new Msg();
        if("tom".equals(username)){
            //用户名不可用
            msg.setStatus(0);
            msg.setMsg("用户名不可用");
        }else{
            //用户名可用
            msg.setStatus(1);
            msg.setMsg("用户名可用");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
