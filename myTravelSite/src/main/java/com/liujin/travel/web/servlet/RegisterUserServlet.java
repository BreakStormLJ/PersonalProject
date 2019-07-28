package com.liujin.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujin.travel.domain.ResultInfo;
import com.liujin.travel.domain.User;
import com.liujin.travel.service.UserService;
import com.liujin.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码效验
        //获取验证码数据
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info数据序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);
            return;
        }

        //1.获取数据（前端请求）
        Map<String, String[]> map = request.getParameterMap();

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        UserService service = new UserServiceImpl();
        String json = service.register(user);

        //4.响应结果,将json数据写回客户端
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
