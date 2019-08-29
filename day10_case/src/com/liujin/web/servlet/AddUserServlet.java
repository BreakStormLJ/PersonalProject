package com.liujin.web.servlet;

import com.liujin.domain.User;
import com.liujin.service.UserService;
import com.liujin.utils.BeanFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = (UserService) BeanFactory.getBean("userService");
        int i = service.addUser(user);

        if (i>0){
            //添加成功，重定向，跳转到listServlet
            response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
        }else {
            //添加失败，数据回写
            request.setAttribute("user",user);
            request.getRequestDispatcher("/add.jsp").forward(request,response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
