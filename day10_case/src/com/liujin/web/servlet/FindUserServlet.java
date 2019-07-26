package com.liujin.web.servlet;

import com.liujin.domain.User;
import com.liujin.service.UserService;
import com.liujin.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        UserService service = (UserService) BeanFactory.getBean("userService");
        User user = service.findUserById(id);

        request.setAttribute("user",user);

        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
