package com.liujin.web.servlet;

import com.liujin.domain.Admin;
import com.liujin.domain.User;
import com.liujin.service.UserService;
import com.liujin.service.impl.UserServiceImpl;
import com.liujin.utils.BeanFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取用户填写验证码
        String verifycode = request.getParameter("verifycode");

        //创建Session
        HttpSession session = request.getSession();
        //将之前存入的验证码信息获取出来（从session中获取程序生成的验证码），在CheckCodeServlet.java第43行44行
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");

        //判断如果用户输入验证码有误，则给用户提示信息：验证码输入有误
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            request.setAttribute("login_msg","验证码输入错误！");

            request.getRequestDispatcher("/login.jsp").forward(request,response);

            return;
        }


        Map<String, String[]> map = request.getParameterMap();
        Admin admin = new Admin();

        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //UserService service = new UserServiceImpl();
        UserService service = (UserService) BeanFactory.getBean("userService");
        Admin loginUser = service.login(admin);
        //System.out.println(loginUser);

        //判断是否登录
        if (loginUser!=null){
            //登录成功
            //request.setAttribute();
            //考虑到下一步是使用重定向将登录用户的数据发到index.jsp，选用之前创建的session存储数据
            session.setAttribute("user",loginUser);

            //重定向，跳转页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        }else {
            //登录失败
            request.setAttribute("login_msg","用户名或密码错误！");
            //消息转发
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
